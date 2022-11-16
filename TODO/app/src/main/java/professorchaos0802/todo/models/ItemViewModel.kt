package professorchaos0802.todo.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import professorchaos0802.todo.objects.Item

class ItemViewModel: ViewModel() {
    val itemEvent: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    val myItems: LiveData<List<Item>> = itemEvent
    var items = mutableStateOf(listOf<Item>())
}