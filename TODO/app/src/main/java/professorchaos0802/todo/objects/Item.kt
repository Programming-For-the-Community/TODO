package professorchaos0802.todo.objects

data class Item(
    var owner: String = "",
    var text: String = "",
    var isDone: Boolean = false
) {
    companion object{
        const val COLLECTION_PATH = "items"
    }
}