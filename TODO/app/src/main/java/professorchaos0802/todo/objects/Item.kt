package professorchaos0802.todo.objects

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude

class Item(
    var owner: String = "",
    var text: String = "",
    var isDone: Boolean = false
) {

    @get:Exclude
    var id = "" // Firestore ID

    companion object{
        const val COLLECTION_PATH = "items"

        /**
         * Converts a firestore DocumentSnapshot into an Item object
         */
        fun from(snapshot: DocumentSnapshot): Item{
            val i = Item(snapshot.get("owner") as String,
                snapshot.get("text") as String,
                snapshot.get("isDone") as Boolean)

            i.id = snapshot.id

            return i
        }
    }
}