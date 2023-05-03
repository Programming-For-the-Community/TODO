package professorchaos0802.todo.utilities

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.MyItem
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.objects.User

/**
 * Utility that connects the Firebase.firestore backend to the UI frontend. Deals with all the Firebase
 * interactions while the [ListViewModel] and [UserViewModel] hold all the data
 */
object FirebaseUtility {
    var firebaseAnalytics = Firebase.analytics

    private lateinit var userRef: DocumentReference
    private val listRef = Firebase.firestore.collection(MyList.COLLECTION_PATH)
    private val publicUserRef = Firebase.firestore.collection(User.COLLECTION_PATH)
    private val itemRef = Firebase.firestore.collection(MyItem.COLLECTION_PATH)
    private val usernameRef = Firebase.firestore.collection(Constants.USERNAME_COLLECTION_PATH).document(Constants.ALL_USERNAMES_ID)
    lateinit var currentListRef: DocumentReference
    lateinit var currentItemRef: DocumentReference

    var subscriptions = HashMap<String, ListenerRegistration>()

    /**
     * If the current User exists in firestore, this method gets that user, otherwise it creates a new user
     *
     * @param userModel - [UserViewModel]: stores all data about the current user
     * @param observer - [Unit]: Lambda that runs after the user has been verified or created
     */
    fun getOrMakeUser(
        userModel: UserViewModel,
        observer: () -> Unit
    ) {
        userRef = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if (userModel.user != null) {
            // get
            observer()
        } else {
            // make
            userRef.get().addOnSuccessListener {
                if (it.exists()) {
                    userModel.user = it.toObject(User::class.java)
                    userModel.nameEvent.postValue(userModel.user!!.username)
                    userModel.themeEvent.postValue(userModel.user!!.theme)
                    userModel.imageEvent.postValue(userModel.user!!.img)
                } else {
                    userModel.user = User(username = Firebase.auth.currentUser!!.displayName!!)
                    userModel.nameEvent.postValue(userModel.user!!.username)
                    userModel.themeEvent.postValue(userModel.user!!.theme)
                    userModel.imageEvent.postValue(userModel.user!!.img)
                    userRef.set(userModel.user!!)
                }
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP){
                    param(FirebaseAnalytics.Param.ITEM_NAME, "New User: ${userModel.user!!.username}")
                }
                observer()
            }
        }

    }

    /**
     * Subscribes a listener to all the usernames in the app
     *
     * @return allUsernames - [MutableList<String>]: All the usernames in the app
     */
    fun addUsernamesListener(): MutableList<String> {
        val allUsernames = mutableListOf<String>()
        val subscription = usernameRef
            .addSnapshotListener { snapshot, e ->
                e?.let {
                    Log.d(Constants.SETUP, "ERROR: $e")
                    return@addSnapshotListener
                }
                snapshot?.let {
                    val usernames: List<String> = it["allUsernames"] as List<String>
                    allUsernames.addAll(usernames)
                }
                Log.d(Constants.SETUP, "All Usernames: ${allUsernames.size}")
            }

        subscriptions[Constants.usernamesListenerId] = subscription

        return allUsernames
    }

    /**
     * Subscribes a listener to all the public users in the app
     *
     * @return allPublicUsers - [MutableList<String>]: All public usernames in the app
     */
    fun addPublicUsersListener(publicUserEvent: MutableLiveData<List<User>>) {
        val subscription = publicUserRef
            .whereEqualTo("visible", true)
            .addSnapshotListener { snapshot, e ->
                e?.let {
                    Log.d(Constants.SHARE, "ERROR: $e")
                    return@addSnapshotListener
                }
                val allPublicUsers = mutableListOf<User>()
                snapshot?.documents?.forEach {
                    allPublicUsers.add(User.from(it))
                }
                publicUserEvent.postValue(allPublicUsers)
                Log.d(Constants.SHARE, "All Public Users: ${allPublicUsers.size}")
            }

        subscriptions[Constants.publicUserListenerId] = subscription
    }

    /**
     * Adds a listener to all of the lists within Firebase
     *
     * @param listenerIdentifier - [String]: key to identify the specific listener
     * @param listEvent - [MutableLiveData]: live list seen in UI
     */
    fun addListListener(listEvent: MutableLiveData<List<MyList>>) {

        val subscription = listRef
            .orderBy(MyList.CREATED_KEY, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let {
                    Log.d(Constants.HOME, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.HOME, "List Snapshot Length: ${snapshot?.size()}")
                val allLists = mutableListOf<MyList>()
                snapshot?.documents?.forEach {
                    allLists.add(MyList.from(it))
                }
                listEvent.postValue(allLists)
                Log.d(Constants.HOME, "Lists Length: ${allLists.size}")
            }

        subscriptions[Constants.listListenerId] = subscription
    }

    /**
     * Adds a listener to the current list being modified
     *
     * @param list - [MyList]: List to add listener to
     * @param currentListEvent - [MutableLiveData]: live data being observed in the UI
     */
    fun addCurrentListListener(list: MyList, currentListEvent: MutableLiveData<MyList?>) {
        currentListRef = listRef.document(list.id)

        val subscription = currentListRef
            .addSnapshotListener { snapshot, e ->
                e?.let {
                    Log.d(Constants.LIST, "ERROR: $e")
                    return@addSnapshotListener
                }
                var myList: MyList? = null
                snapshot?.let { myList = MyList.from(it) }
                Log.d(Constants.LIST, "Current List: ${myList?.title}")
                currentListEvent.postValue(myList)
            }

        subscriptions[list.id] = subscription
    }

    /**
     * Subscribes a listener to the item list from the specified list
     *
     * @param itemEvent - [MutableLiveData]: live data storing the items from the listener
     */
    fun addItemListener(itemEvent: MutableLiveData<List<MyItem>>) {
        val subscription = itemRef
            .addSnapshotListener { snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let {
                    Log.e(Constants.ITEM, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.ITEM, "Item Snapshot Length: ${snapshot?.size()}")
                val allItems = mutableListOf<MyItem>()
                snapshot?.documents?.forEach {
                    allItems.add(MyItem.from(it))
                }
                itemEvent.postValue(allItems)
                Log.d(Constants.ITEM, "Number of Items: ${allItems.size}")
            }

        subscriptions[Constants.itemListenerId] = subscription
    }

    /**
     * Subscribes a listener to the subset of items associated with a particular list
     *
     * @param list - [MyList]: list we want the items for
     * @param currentListItemsEvent - [MutableState]: live data storing the items associated with the given list
     */
    fun addCurrentListItemListener(
        list: MyList,
        currentListItemsEvent: MutableLiveData<List<MyItem>>
    ) {
        val subscription = itemRef
            .whereEqualTo("listId", list.id)
            .addSnapshotListener { snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let {
                    Log.e(Constants.ITEM, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.ITEM, "Item Snapshot Length: ${snapshot?.size()}")
                val currListItems = mutableListOf<MyItem>()
                snapshot?.documents?.forEach {
                    currListItems.add(MyItem.from(it))
                }
                currentListItemsEvent.postValue(currListItems)
                Log.d(Constants.ITEM, "Number of Items: ${currListItems.size}")
            }

        subscriptions[list.id + Constants.itemListenerId] = subscription
    }

    /**
     * Add a new list to the local list and to the database
     *
     * @param list - [MyList]: list to be added to firebase
     * @param listEvent - [MutableLiveData]: mutable live data to be updated when the
     * list is added to Firebase
     */
    fun addNewList(
        list: MyList,
        listEvent: MutableLiveData<MyList?>,
        currListItems: MutableLiveData<List<MyItem>>,
        onNavigateToList: () -> Unit
    ) {
        listRef.add(list)
            .addOnSuccessListener {
                val task = it.get()
                task.addOnSuccessListener { newList ->
                    val currList = MyList.from(newList)
                    addCurrentListListener(currList, listEvent)
                    addCurrentListItemListener(currList, currListItems)

                    onNavigateToList()
                }
            }
    }

    /**
     * Adds an item to both the local list and to the firestore database
     *
     * @param item - [MyItem]: item to be added to firebase
     */
    fun addItem(i: MyItem) {
        itemRef.add(i)
    }

    /**
     * Updates the currently logged in user
     *
     * @param userModel - [UserViewModel]: Holds the most current data to pass to Firebase
     */
    fun updateUser(userModel: UserViewModel) {
        userRef = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)
        userModel.user!!.theme = userModel.userTheme.value
        userModel.user!!.img = userModel.userImage.value

        if (userModel.user != null) {
            with(userModel.user!!) {
                userRef.set(this)
            }
        }
    }

    /**
     * Update the username of the currently logged in user
     *
     * @param userModel - [UserViewModel]: Holds the most current data to pass to Firebase
     */
    fun updateName(userModel: UserViewModel) {
        userRef = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if (userModel.user != null) {
            with(userModel.user!!) {
                username = userModel.userName.value
                userRef.set(this)
            }
        }
    }

    /**
     * Updates the list of all usernames in the app
     *
     * @param usernames - [MutableList]: new list to be pushed to Firebase
     */
    fun updateUsernames(usernames: MutableList<String>){
        usernameRef.set(usernames.toList())
    }

    /**
     * Updates the current list in Firebase
     *
     * @param currentList - [MyList]: List to be updated
     * @param currentTitle - [String]: new title to be passed to the list
     */
    fun updateCurrentList(currentList: MyList, currentTitle: String) {
        currentListRef = listRef.document(currentList.id)

        with(currentList) {
            this.title = currentTitle
            currentListRef.set(this)
        }
    }

    /**
     * Updates the given list in Firebase
     *
     * @param list - [MyList]: List to be updated
     */
    fun updateList(list: MyList) {
        currentListRef = listRef.document(list.id)

        with(list) {
            currentListRef.set(this)
        }
    }

    /**
     * Updates the give item in Firebase
     *
     * @param item - [MyItem]: item to be updated
     * @param text - [String]: new text of the item
     * @param isDone - [Boolean]: completion state of the item
     */
    fun updateItem(item: MyItem, text: String, isDone: Boolean) {
        currentItemRef = itemRef.document(item.id)

        with(item) {
            this.text = text
            this.done = isDone
            currentItemRef.set(this)
        }
    }

    /**
     * Deletes a list from the local list and to the database
     *
     * @param list - [MyList]: List to be deleted from Firebase
     */
    fun deleteList(list: MyList) {
        listRef.document(list.id).delete()
    }

    /**
     * Deletes the current item from the firestore database
     *
     * @param item - [MyItem]: Item to be deleted
     */
    fun deleteItem(item: MyItem) {
        itemRef.document(item.id).delete()
    }

    /**
     * Removes the listener from fragment passed in
     *
     * @param listenerIdentifier - [String]: Listener to be removed
     */
    fun removeListener(listenerIdentifier: String) {
        subscriptions[listenerIdentifier]?.remove() // Tell Firebase to stop listening
        subscriptions.remove(listenerIdentifier) // Remove listener from HashMap
    }

}