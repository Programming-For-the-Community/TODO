package professorchaos0802.todo.objects

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude

data class Item(
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
            val i = snapshot.toObject(Item::class.java)!!
            i.id = snapshot.id

            return i
        }
    }
}