package professorchaos0802.todo.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import professorchaos0802.todo.objects.MyItem

class ItemViewModel: ViewModel() {

    val itemsEvent: MutableLiveData<List<MyItem>> = MutableLiveData<List<MyItem>>()
    val myItems: LiveData<List<MyItem>> = itemsEvent
    var items = mutableStateOf(listOf<MyItem>())

    val currentListItemsEvent: MutableLiveData<List<MyItem>> = MutableLiveData<List<MyItem>>()
    val currListItems: LiveData<List<MyItem>> = currentListItemsEvent
    var currentListItems = mutableStateOf(listOf<MyItem>())

    val currentItemEvent: MutableLiveData<MyItem?> = MutableLiveData<MyItem?>()
    val currItem: LiveData<MyItem?> = currentItemEvent
    var currentItem = mutableStateOf<MyItem?>(null)

    val itemTextEvent: MutableLiveData<String> = MutableLiveData<String>()
    val myItemText: LiveData<String> = itemTextEvent
    var itemText = mutableStateOf("")

    /**
     * Updates the current list of items locally, without needing Wifi
     *
     * @param item - [MyItem]: item to update in the list
     */
    fun updateCurrentListItem(item: MyItem){
        val index = currentListItems.value.indexOfFirst{ it == item}
        currentListItems.value[index].listId = item.listId
        currentListItems.value[index].done = item.done
        currentListItems.value[index].text = item.text
        currentListItems.value[index].owner = item.owner
    }

    /**
     * Adds a new item to the current list of items locally without needing Wifi
     *
     * @param item - [MyItem]: item to add to the list
     */
    fun addItem(item: MyItem){
        val newList = currentListItems.value.toMutableList()
        newList.add(item)

        currentListItems.value = newList
    }

}