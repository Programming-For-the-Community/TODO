package professorchaos0802.todo.objects

class MyList(
    var owner: String = "",
    var timestamp: String = "",
    var title: String = "",
    var canEdit: ArrayList<String> = ArrayList<String>(),
    var canView: ArrayList<String> = ArrayList<String>(),
    var items: ArrayList<Item> = ArrayList<Item>(),
){
    companion object{
        const val COLLECTION_PATH = "lists"
    }

    fun getItemText(): ArrayList<String>{
        var itemText = ArrayList<String>()

        for(item in items){
            itemText.add(item.text)
        }

        return itemText
    }
}