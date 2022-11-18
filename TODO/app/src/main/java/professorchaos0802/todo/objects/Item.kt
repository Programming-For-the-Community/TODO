package professorchaos0802.todo.objects

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude

class Item(
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
        fun from(snapshot: DocumentSnapshot): Item{
            val i = Item(
                owner = snapshot.get("owner") as String,
                listId = snapshot.get("listId") as String,
                text = snapshot.get("text") as String,
                done = snapshot.get("done") as Boolean
            )

            i.id = snapshot.id

            return i
        }
    }
}