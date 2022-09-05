package professorchaos0802.todo.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList

class ListViewModel: ViewModel() {
    lateinit var itemRef: CollectionReference
    lateinit var listRef: CollectionReference

    var subscriptions = HashMap<String, ListenerRegistration>()
    var currentList = MyList()
    var lists = ArrayList<MyList>()

    private var currentItemIndex = 0

    /**
     * Updates the list to the list passed into the method
     */
    fun updateList(l: MyList){
        currentList = l
    }

    /**
     * Add a new list to the local list and to the database
     */
    fun addNewList(list: MyList){
        currentList = list
        lists.add(list)
        listRef.add(list)
    }

    /**
     * Returns the item found at the currentItemIndex
     */
    fun getCurrentItem() = currentList.items[currentItemIndex]

    fun addListListener(fragmentName: String, observer: () -> Unit){
        listRef = Firebase.firestore.collection(MyList.COLLECTION_PATH)

        val subscription = listRef.
                addSnapshotListener{snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                    e?.let{
                        Log.d(Constants.HOME, "ERROR: $e")
                        return@addSnapshotListener
                    }
                    Log.d(Constants.HOME, "Snapshot Length: ${snapshot?.size()}")
                    lists.clear()
                    snapshot?.documents?.forEach {
                        lists.add(MyList.from(it))
                    }
                    Log.d(Constants.HOME, "Lists Length: ${lists.size}")
                }
                observer()

        subscriptions[fragmentName] = subscription
    }

    /**
     * Subscribes a listener to the item list from the specified list
     */
    fun addItemListener(fragmentName: String, observer: () -> Unit){
        var listID = currentList.id
        itemRef = Firebase.firestore.collection(MyList.COLLECTION_PATH).document(listID).collection(
            Item.COLLECTION_PATH)

        val subscription = itemRef
            .addSnapshotListener{ snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let{
                    Log.d(Constants.LIST, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.LIST, "Snapshot Length: ${snapshot?.size()}")
                currentList.items.clear()
                snapshot?.documents?.forEach{
                    currentList.items.add(Item.from(it))
                }
                Log.d(Constants.LIST, "Number of Items: ${currentList.items.size}")
                observer()
            }

        subscriptions[fragmentName] = subscription
    }

    /**
     * Removes the listener from fragment passed in
     */
    fun removeListener(fragmentName: String){
        subscriptions[fragmentName]?.remove() // Tell Firebase to stop listening
        subscriptions.remove(fragmentName) // Remove listener from HashMap
    }

    /**
     * Deletes the current item from the firestore database
     */
    fun deleteCurrentItem(){
        var currItem = currentList.items[currentItemIndex]

        if(currentList.items[currentItemIndex].id == ""){
            currentList.items.remove(currItem)
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
        currentList.items.add(i)
        itemRef.add(i)
    }
}