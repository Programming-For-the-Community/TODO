package professorchaos0802.todo.objects

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude

class MyItem(
    var owner: String = "",
    var listId: String = "",
    var text: String = "",
    var done: Boolean = false
) {

    @get:Exclude
    var id = "" // Firestore ID

    companion object{
        const val COLLECTION_PATH = "items"

        /**
         * Converts a firestore DocumentSnapshot into an Item object
         */
        fun from(snapshot: DocumentSnapshot): MyItem{
//            val i = MyItem(
//                owner = snapshot.get("owner") as String,
//                listId = snapshot.get("listId") as String,
//                text = snapshot.get("text") as String,
//                done = snapshot.get("done") as Boolean
//            )
            val i = snapshot.toObject(MyItem::class.java)!!

            i.id = snapshot.id

            return i
        }
    }
}