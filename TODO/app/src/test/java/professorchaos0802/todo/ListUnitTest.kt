package professorchaos0802.todo

import org.junit.Test

import org.junit.Assert.*
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList

/**
 * Tests the List Object
 */
class ListUnitTest {
    @Test
    fun list_creation(){
        val list = MyList()
        val list1 = MyList("cmhahm", "mm/dd/yyyy hh:mm:ss", "MyTodoList", arrayListOf<String>("a", "b", "c"), arrayListOf<String>("d", "e", "f"), arrayListOf<Item>(Item(), Item(), Item()))

        // Test default creation
        assertEquals("", list.owner)
        assertEquals("", list.title)
        assertEquals("", list.timestamp)
        assertEquals(ArrayList<String>(), list.canEdit)
        assertEquals(ArrayList<String>(), list.canView)
        assertEquals(ArrayList<Item>(), list.items)

        // Test collection path
        assertEquals("lists", MyList.COLLECTION_PATH)

        // Test custom creation
        assertEquals("cmhahm", list1.owner)
        assertEquals("mm/dd/yyyy hh:mm:ss", list1.timestamp)
        assertEquals("MyTodoList", list1.title)
        assertEquals(arrayListOf<String>("a", "b", "c"), list1.canEdit)
        assertEquals(arrayListOf<String>("d", "e", "f"), list1.canView)
        assertEquals(arrayListOf<Item>(Item(), Item(), Item()), list1.items)
    }

    @Test
    fun list_edit_owner_timestamp_title(){
        var list = MyList()

        // Verify default creation
        assertEquals("", list.owner)
        assertEquals("", list.title)
        assertEquals("", list.timestamp)

        val owner = "cmhahm"
        val title = "Charlie's Todo List"
        val timestamp = "08/14/2022 10:26:45 PM"

        list.owner = owner
        list.title = title
        list.timestamp = timestamp

        // Test changes
        assertEquals(owner, list.owner)
        assertEquals(title, list.title)
        assertEquals(timestamp, list.timestamp)

    }

    @Test
    fun list_canEdit_add_delete(){
        var list = MyList()

        var letter = "A"
        var letters = ArrayList<String>()

        while (letter != "Z"){
            list.canEdit.add(letter)
            letters.add(letter)

            letter = (letter[0]+1).toString()
        }

        assertEquals(letters, list.canEdit)

        while (letter != "A"){
            list.canEdit.remove(letter)
            letters.remove(letter)

            letter = (letter[0]-1).toString()
        }

        assertEquals(letters, list.canEdit)

    }

    @Test
    fun list_canView_add_delete(){
        var list = MyList()

        var letter = "A"
        var letters = ArrayList<String>()

        while (letter != "Z"){
            list.canView.add(letter)
            letters.add(letter)

            letter = (letter[0]+1).toString()
        }

        assertEquals(letters, list.canView)

        while (letter != "A"){
            list.canView.remove(letter)
            letters.remove(letter)

            letter = (letter[0]-1).toString()
        }

        assertEquals(letters, list.canView)

    }

    @Test
    fun list_items_add_delete(){
        var list = MyList()

        var item1 = Item(text = "Hello")
        var item2 = Item(text = "Goodbye")
        var item3 = Item(text = "Hola")
        var item4 = Item(text = "Adios")
        var item5 = Item(text = "Sleepy")

        var items = arrayListOf(item1, item2, item3, item4, item5)
        var itemsText = ArrayList<String>()

        for(i in 0..4){
            list.items.add(items[i])
            itemsText.add(items[i].text)

            assertEquals(itemsText, list.getItemText())
        }

        for(i in 0..4){
            list.items.remove(items[i])
            itemsText.remove(items[i].text)

            assertEquals(itemsText, list.getItemText())
        }

    }
}