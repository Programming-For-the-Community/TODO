package professorchaos0802.todo.objects

import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp

class MyList(
    var owner: String = "",
    var title: String = ""
){

    var canEdit = mutableStateListOf<String>()
    var canView = mutableStateListOf<String>()
    var items = mutableStateListOf<Item>()

    @get:Exclude
    var id = "" // Firestore ID

    @ServerTimestamp
    var created: Timestamp? = null

    companion object{
        const val COLLECTION_PATH = "lists"
        const val CREATED_KEY = "created"

        /**
         * Converts a firestore DocumentSnapshot into a list object
         */
        fun from(snapshot: DocumentSnapshot): MyList{
            val l = snapshot.toObject(MyList::class.java)!!
            l.id = snapshot.id

            return l
        }
    }

//    /**
//     * Returns the text of all the items within the list
//     */
//    fun getItemText(): ArrayList<String>{
//        var itemText = ArrayList<String>()
//
//        for(item in items){
//            itemText.add(item.text)
//        }
//
//        return itemText
//    }
}