package professorchaos0802.todo.objects

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude

class MyList(
    var owner: String = "",
    var timestamp: String = "",
    var title: String = "",
    var canEdit: ArrayList<String> = ArrayList<String>(),
    var canView: ArrayList<String> = ArrayList<String>(),
    var items: ArrayList<Item> = ArrayList<Item>(),
){

    @get:Exclude
    var id = "" // Firestore ID

    companion object{
        const val COLLECTION_PATH = "lists"

        /**
         * Converts a firestore DocumentSnapshot into a list object
         */
        fun from(snapshot: DocumentSnapshot): MyList{
            val l = snapshot.toObject(MyList::class.java)!!
            l.id = snapshot.id

            return l
        }
    }

    /**
     * Returns the text of all the items within the list
     */
    fun getItemText(): ArrayList<String>{
        var itemText = ArrayList<String>()

        for(item in items){
            itemText.add(item.text)
        }

        return itemText
    }
}