package professorchaos0802.todo.utilities

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.objects.User

/**
 * Utility that connects the Firebase.firestore backend to the UI frontend. Deals with all the Firebase
 * interactions while the [ListViewModel] and [UserViewModel] hold all the data
 */
object FirebaseUtility {
    private lateinit var userRef: DocumentReference
    private val listRef = Firebase.firestore.collection(MyList.COLLECTION_PATH)
    private val itemRef = Firebase.firestore.collection(Item.COLLECTION_PATH)
    lateinit var currentListRef: DocumentReference
    lateinit var currentItemRef: DocumentReference

    var subscriptions = HashMap<String, ListenerRegistration>()

    /**
     * If the current User exists in firestore, this method gets that user, otherwise it creates a new user
     *
     * @param sharedPreferences - [SharedPreferences]: local app storage to grab the most recent user theme color from
     * @param userModel - [UserViewModel]: stores all data about the current user
     * @param observer - [Unit]: Lambda that runs after the user has been verified or created
     */
    fun getOrMakeUser(sharedPreferences: SharedPreferences, userModel: UserViewModel, observer: () -> Unit) {
        userRef = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if(userModel.user != null){
            // get
            observer()
        }else{
            // make
            userRef.get().addOnSuccessListener {
                if(it.exists()){
                    userModel.user = it.toObject(User::class.java)
                    userModel.nameEvent.value = userModel.user!!.username
                    userModel.themeEvent.value = userModel.user!!.theme
                    userModel.imageEvent.value = userModel.user!!.img
                }else{
                    userModel.user = User(username= Firebase.auth.currentUser!!.displayName!!)
                    userModel.nameEvent.value = userModel.user!!.username
                    userModel.themeEvent.value = userModel.user!!.theme
                    userModel.imageEvent.value = userModel.user!!.img
                    userRef.set(userModel.user!!)
                }
                sharedPreferences.edit().putString(Constants.THEME_KEY, userModel.userTheme.value).apply()

                observer()
            }
        }
    }

    /**
     * Adds a listener to all of the lists within Firebase
     *
     * @param listenerIdentifier - [String]: key to identify the specific listener
     * @param listEvent - [MutableLiveData]: live list seen in UI
     */
    fun addListListener(listEvent: MutableLiveData<List<MyList>>){

        val subscription = listRef
            .orderBy(MyList.CREATED_KEY, Query.Direction.DESCENDING)
            .addSnapshotListener{ snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let{
                    Log.d(Constants.HOME, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.HOME, "List Snapshot Length: ${snapshot?.size()}")
                val allLists = mutableListOf<MyList>()
                snapshot?.documents?.forEach {
                    allLists.add(MyList.from(it))
                }
                listEvent.value = allLists
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
    fun addCurrentListListener(list: MyList, currentListEvent: MutableLiveData<MyList?>){
        currentListRef = listRef.document(list.id)

        val subscription = currentListRef
            .addSnapshotListener{ snapshot, e ->
                e?.let{
                    Log.d(Constants.LIST, "ERROR: $e")
                    return@addSnapshotListener
                }
                var myList: MyList? = null
                snapshot?.let{ myList = MyList.from(it) }
                Log.d(Constants.LIST, "Current List: ${myList?.title}")
                currentListEvent.value = myList
            }

        subscriptions[list.id] = subscription
    }

    /**
     * Subscribes a listener to the item list from the specified list
     *
     * @param itemEvent - [MutableLiveData]: live data storing the items from the listener
     */
    fun addItemListener(itemEvent: MutableLiveData<List<Item>>){
        val subscription = itemRef
            .addSnapshotListener{ snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let{
                    Log.e(Constants.ITEM, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.ITEM, "Item Snapshot Length: ${snapshot?.size()}")
                val allItems = mutableListOf<Item>()
                snapshot?.documents?.forEach{
                    allItems.add(Item.from(it))
                }
                itemEvent.value = allItems
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
    fun addCurrentListItemListener(list: MyList, currentListItemsEvent: MutableLiveData<List<Item>>){
        val subscription = itemRef
            .whereEqualTo("listId", list.id)
            .addSnapshotListener{ snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let{
                    Log.e(Constants.ITEM, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.ITEM, "Item Snapshot Length: ${snapshot?.size()}")
                val currListItems = mutableListOf<Item>()
                snapshot?.documents?.forEach{
                    currListItems.add(Item.from(it))
                }
                currentListItemsEvent.value = currListItems
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
    fun addNewList(list: MyList, listEvent: MutableLiveData<MyList?>, currListItems: MutableLiveData<List<Item>>){
        listRef.add(list)
            .addOnSuccessListener {
                val task = it.get()
                task.addOnSuccessListener { newList ->
                    val currList = MyList.from(newList)
                    addCurrentListListener(currList, listEvent)
                    addCurrentListItemListener(currList, currListItems)
                }
            }
    }

    /**
     * Adds an item to both the local list and to the firestore database
     *
     * @param item - [Item]: item to be added to firebase
     */
    fun addItem(i: Item){
        itemRef.add(i)
    }

    /**
     * Updates the currently logged in user
     *
     * @param userModel - [UserViewModel]: Holds the most current data to pass to Firebase
     */
    fun updateUser(userModel: UserViewModel){
        userRef = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)
        userModel.user!!.theme = userModel.userTheme.value
        userModel.user!!.img = userModel.userImage.value

        if(userModel.user != null){
            with(userModel.user!!){
                userRef.set(this)
            }
        }
    }

    /**
     * Update the username of the currently logged in user
     *
     * @param userModel - [UserViewModel]: Holds the most current data to pass to Firebase
     */
    fun updateName(userModel: UserViewModel){
        userRef = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if(userModel.user != null){
            with(userModel.user!!){
                username = userModel.userName.value
                userRef.set(this)
            }
        }
    }

    /**
     * Updates the current list in Firebase
     *
     * @param currentList - [MyList]: List to be updated
     * @param currentTitle - [String]: new title to be passed to the list
     */
    fun updateCurrentList(currentList: MyList, currentTitle: String){
        currentListRef = listRef.document(currentList.id)

        with(currentList){
            this.title = currentTitle
            currentListRef.set(this)
        }
    }

    /**
     * Updates the give item in Firebase
     *
     * @param item - [Item]: item to be updated
     * @param text - [String]: new text of the item
     * @param isDone - [Boolean]: completion state of the item
     */
    fun updateItem(item: Item, text: String, isDone: Boolean) {
        currentItemRef = itemRef.document(item.id)

        with(item){
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
    fun deleteList(list: MyList){
        listRef.document(list.id).delete()
    }

    /**
     * Deletes the current item from the firestore database
     *
     * @param item - [Item]: Item to be deleted
     */
    fun deleteItem(item: Item){
        itemRef.document(item.id).delete()
    }

    /**
     * Removes the listener from fragment passed in
     *
     * @param listenerIdentifier - [String]: Listener to be removed
     */
    fun removeListener(listenerIdentifier: String){
        subscriptions[listenerIdentifier]?.remove() // Tell Firebase to stop listening
        subscriptions.remove(listenerIdentifier) // Remove listener from HashMap
    }

}