package professorchaos0802.todo.objects

import androidx.compose.runtime.mutableStateOf
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

    @get:Exclude
    var mutableItemText = mutableStateOf("")

    companion object{
        const val COLLECTION_PATH = "items"

        /**
         * Converts a firestore DocumentSnapshot into an Item object
         */
        fun from(snapshot: DocumentSnapshot): MyItem{
            val i = snapshot.toObject(MyItem::class.java)!!

            i.id = snapshot.id
            i.mutableItemText.value = i.text

            return i
        }
    }
}