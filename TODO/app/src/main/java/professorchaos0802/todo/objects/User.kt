package professorchaos0802.todo.objects

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude

/**
 * Data class outline all the things that are tracked by the user
 *
 * @param username - [String]: User's unique tag
 * @param img - [String]: url pointing to the user's profile image
 * @param theme - [String]: String denoting which color theme the user is currently using
 * @param font - [String]: font of the app chosen by the user
 * @param hasCompletedSetup - [Boolean]: boolean showing if the user has completed the first-time setup
 * @param isVisible - [Boolean]: boolean indicating of other user's of the app can share lists with this user
 *      false -> Private
 *      true -> Public
 */
data class User(
    var username: String = "JDoe",
    var img: String = "",
    var theme: String = "Blue",
    var font: String = "",
    var hasCompletedSetup: Boolean = false,
    var isVisible: Boolean? = null
) {

    @get:Exclude
    var id = "" // Firestore ID

    companion object{
        const val COLLECTION_PATH = "users"

        /**
         * Converts a firestore DocumentSnapshot into a list object
         */
        fun from(snapshot: DocumentSnapshot): User{
            val u = snapshot.toObject(User::class.java)!!
            u.id = snapshot.id

            return u
        }
    }
}