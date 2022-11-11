package professorchaos0802.todo.navigation

sealed class TodoViews(val route: String) {
    object Splash:TodoViews("splash_screen")
    object UserNameSetup:TodoViews("user_name_setup")
    object Customization:TodoViews("customization_view")
    object ProfileImage:TodoViews("profile_image")
    object Home:TodoViews("home_view")
    object Profile:TodoViews("profile_view")
    object List:TodoViews("list_view")
}