package professorchaos0802.todo

import android.view.WindowManager

object Constants {
    const val SETUP = "SETUP"
    const val HOME = "HOME"
    const val LIST = "LIST"
    const val ITEM = "ITEM"
    const val PROFILE = "PROFILE"
    const val USER_UPDATE = "USER_UPDATE"
    const val PERMISSIONS = "PERMISSIONS"
    const val THEME_KEY = "theme"

    // Listener IDs
    const val listListenerId = "TodoLists"
    const val itemListenerId = "TodoItems"
    const val usernamesListenerId = "Usernames"
    const val publicUserListenerId = "PublicUsers"

    // Username Collection Path
    const val USERNAME_COLLECTION_PATH = "usernames"
    const val ALL_USERNAMES_ID = "OKCoWRg2u7ZUhcSr58TZ"

    var windowManager: WindowManager? = null
}