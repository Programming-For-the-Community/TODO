package professorchaos0802.todo.navigation

sealed class TodoViews(val route: String) {
    object Splash:TodoViews("splash_screen")
    object Home:TodoViews("home_view")
}