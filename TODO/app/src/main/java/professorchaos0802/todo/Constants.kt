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
    const val USERNAME_KEY = "username"
    const val IMAGE_KEY = "image"
    const val SETUP_KEY = "setup"

    // Listener IDs
    const val listListenerId = "TodoLists"
    const val itemListenerId = "TodoItems"

    var windowManager: WindowManager? = null
}