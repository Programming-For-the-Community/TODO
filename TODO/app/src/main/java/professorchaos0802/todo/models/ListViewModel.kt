package professorchaos0802.todo.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

//    private val listsToDeleteEvent: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
//    val deleteLists: LiveData<Boolean> = listsToDeleteEvent
//    var hasListsToDelete = mutableStateOf(false)

//    var listsToDelete = mutableListOf<MyList>()

    /**
     * Updates the current list locally without needing wifi
     *
     * @param list - [MyList]: list to set as the current list
     */
    fun updateCurrentList(list: MyList){
        currentListEvent.value = list
    }

    /**
     * Adds a new list locally without using Wifi
     *
     * @param list - [MyList]: list to add
     */
    fun addList(list: MyList){
        val allLists = lists.value.toMutableList()
        allLists.add(list)
        listEvent.value = allLists
    }

}