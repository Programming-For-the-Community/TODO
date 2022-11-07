package professorchaos0802.todo.objects

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp

class MyList(
    var owner: String = "",
    var title: String = ""
){

    var canEdit = mutableListOf<String>()
    var canView = mutableListOf<String>()
    var items = mutableListOf<Item>()

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
}