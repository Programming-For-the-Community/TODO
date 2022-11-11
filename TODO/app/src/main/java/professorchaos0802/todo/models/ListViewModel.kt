package professorchaos0802.todo.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList

class ListViewModel: ViewModel() {
    lateinit var itemRef: CollectionReference
    lateinit var listRef: CollectionReference

    var subscriptions = HashMap<String, ListenerRegistration>()

    private val listEvent: MutableLiveData<List<MyList>> = MutableLiveData<List<MyList>>()
    val myLists: LiveData<List<MyList>> = listEvent
    var lists = mutableStateOf(listOf<MyList>())

    private val currentListEvent: MutableLiveData<MyList> = MutableLiveData<MyList>()
    val currList: LiveData<MyList> = currentListEvent
    var currentList = mutableStateOf(MyList())

    private val itemEvent: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    val currItems: LiveData<List<Item>> = itemEvent
    var currentItems = mutableStateOf(listOf<Item>())

//    private val listsToDeleteEvent: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
//    val deleteLists: LiveData<Boolean> = listsToDeleteEvent
//    var hasListsToDelete = mutableStateOf(false)

//    var listsToDelete = mutableListOf<MyList>()
    private var currentItemIndex = 0

    /**
     * Add a new list to the local list and to the database
     */
    fun addNewList(list: MyList){
        currentListEvent.value = list
        listRef.add(list)
    }

    /**
     * Deletes a list from the local list and to the database
     */
    fun deleteList(list: MyList){
        listRef.document(list.id).delete()
    }

    fun addListListener(listenerIdentifier: String){
        listRef = Firebase.firestore.collection(MyList.COLLECTION_PATH)

        val subscription = listRef
            .orderBy(MyList.CREATED_KEY, Query.Direction.DESCENDING)
            .addSnapshotListener{snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
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

        subscriptions[listenerIdentifier] = subscription
    }

    /**
     * Subscribes a listener to the item list from the specified list
     */
    fun addItemListener(listenerIdentifier: String){
        val listID = currentList.value.id
        itemRef = Firebase.firestore.collection(MyList.COLLECTION_PATH).document(listID).collection(
            Item.COLLECTION_PATH)

        val subscription = itemRef
            .addSnapshotListener{ snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let{
                    Log.d(Constants.LIST, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.LIST, "Item Snapshot Length: ${snapshot?.size()} for list ${currentList.value.title}")
                val allItems = mutableListOf<Item>()
                snapshot?.documents?.forEach{
                    allItems.add(Item.from(it))
                }
                itemEvent.value = allItems
                Log.d(Constants.LIST, "Number of Items: ${allItems.size}")
            }

        subscriptions[listenerIdentifier] = subscription
    }

    /**
     * Removes the listener from fragment passed in
     */
    fun removeListener(listenerIdentifier: String){
        subscriptions[listenerIdentifier]?.remove() // Tell Firebase to stop listening
        subscriptions.remove(listenerIdentifier) // Remove listener from HashMap
    }

    /**
     * Deletes the current item from the firestore database
     */
    fun deleteCurrentItem(){
        var currItem = currentList.value.items[currentItemIndex]

        if(currentList.value.items[currentItemIndex].id == ""){
            currentList.value.items.remove(currItem)
        }else {
            itemRef.document(currItem.id).delete()
        }
    }

    /**
     * Updates the indes of the current item
     */
    fun updateCurrentItem(pos: Int) {currentItemIndex = pos}

    /**
     * Adds an item to both the local list and to the firestore database
     */
    fun addItem(i: Item){
        currentList.value.items.add(i)
        itemRef.add(i)
    }
}