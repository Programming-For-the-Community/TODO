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

    var subscriptions = HashMap<String, ListenerRegistration>()
    var list = MyList()

    private var currentItemIndex = 0

    /**
     * Updates the list to the list passed into the method
     */
    fun updateList(l: MyList){
        list = l
    }

    /**
     * Returns the item found at the currentItemIndex
     */
    fun getCurrentItem() = list.items[currentItemIndex]

    /**
     * Subscribes a listener to the item list from the specified list
     */
    fun addItemListener(fragmentName: String, observer: () -> Unit){
        var listID = list.id
        itemRef = Firebase.firestore.collection(MyList.COLLECTION_PATH).document(listID).collection(
            Item.COLLECTION_PATH)

        val subscription = itemRef
            .addSnapshotListener{ snapshot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                e?.let{
                    Log.d(Constants.LIST, "ERROR: $e")
                    return@addSnapshotListener
                }
                Log.d(Constants.LIST, "Snapshot Length: ${snapshot?.size()}")
                list.items.clear()
                snapshot?.documents?.forEach{
                    list.items.add(Item.from(it))
                }
                Log.d(Constants.LIST, "Clothing Length: ${list.items.size}")
                observer()
            }
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
        var currItem = list.items[currentItemIndex]

        if(list.items[currentItemIndex].id == ""){
            list.items.remove(currItem)
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
        list.items.add(i)
        itemRef.add(i)
    }
}