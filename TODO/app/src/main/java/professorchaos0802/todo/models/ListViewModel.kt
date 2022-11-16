package professorchaos0802.todo.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList

/**
 * [ViewModel] holding all the data of a user's lists
 */
class ListViewModel: ViewModel() {
    val listEvent: MutableLiveData<List<MyList>> = MutableLiveData<List<MyList>>()
    val myLists: LiveData<List<MyList>> = listEvent
    var lists = mutableStateOf(listOf<MyList>())

    val currentListEvent: MutableLiveData<MyList?> = MutableLiveData<MyList?>()
    val currList: LiveData<MyList?> = currentListEvent
    var currentList = mutableStateOf<MyList?>(null)

    val currentListTitleEvent: MutableLiveData<String> = MutableLiveData<String>()
    val currListTitle: LiveData<String> = currentListTitleEvent
    var currentListTitle = mutableStateOf("")

    val itemEvent: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    val currItems: LiveData<List<Item>> = itemEvent
    var currentItems = mutableStateOf(listOf<Item>())

//    private val listsToDeleteEvent: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
//    val deleteLists: LiveData<Boolean> = listsToDeleteEvent
//    var hasListsToDelete = mutableStateOf(false)

//    var listsToDelete = mutableListOf<MyList>()

}