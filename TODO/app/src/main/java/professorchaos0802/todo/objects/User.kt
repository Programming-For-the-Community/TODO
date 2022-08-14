package professorchaos0802.todo.objects

/**
 * Data class outline all the things that are tracked by the user
 *
 * username - User's unique tag
 * img - url pointing to the user's profile image
 * theme - String denoting which color theme the user is currently using
 * font - font of the app chosen by the user
 * hasCompletedSetup - boolean showing if the user has completed the first-time setup
 * isVisible - boolean indicating of other user's of the app can share lists with this user
 *      false -> Private
 *      true -> Public
 */
data class User(
    var username: String = "JDoe",
    var img: String = "",
    var theme: String = "Blue",
    var font: String = "",
    var hasCompletedSetup: Boolean = false,
    var isVisible: Boolean = false
) {
    companion object{
        const val COLLECTION_PATH = "users"
    }
}