package professorchaos0802.todo.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import professorchaos0802.todo.objects.Item

class ItemViewModel: ViewModel() {
    var items = mutableStateOf(listOf<Item>())

    val currentListItemsEvent: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    val myCurrentListItems: LiveData<List<Item>> = currentListItemsEvent
    var currentListItems = mutableStateOf(listOf<Item>())

    val currentItemEvent: MutableLiveData<Item?> = MutableLiveData<Item?>()
    val currItem: LiveData<Item?> = currentItemEvent
    var currentItem = mutableStateOf<Item?>(null)

    var itemText = mutableStateOf("")
    var itemIsDone = mutableStateOf(false)

}