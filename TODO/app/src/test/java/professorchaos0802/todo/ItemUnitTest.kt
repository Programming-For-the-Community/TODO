package professorchaos0802.todo

import org.junit.Test

import org.junit.Assert.*
import professorchaos0802.todo.objects.Item

/**
 * Tests the creation of an Item object and the ability to edit item fields
 */
class ItemUnitTest {
    @Test
    fun item_creation(){
        var item = Item()
        var item1 = Item("cmhahm", "Write Item object", true)

        // Test default creation
        assertEquals("", item.owner)
        assertEquals("", item.text)
        assertEquals(false, item.isDone)

        // Test Collection Path
        assertEquals("items", Item.COLLECTION_PATH)

        // Test custom creation
        assertEquals("cmhahm", item1.owner)
        assertEquals("Write Item object", item1.text)
        assertEquals(true, item1.isDone)
    }

    @Test
    fun item_editing(){
        var item = Item()

        // Verify creation
        assertEquals("", item.owner)
        assertEquals("", item.text)
        assertEquals(false, item.isDone)

        item.owner = "cmhahm"
        item.text = "Take a nap"
        item.isDone = true

        // Test changing values
        assertEquals("cmhahm", item.owner)
        assertEquals("Take a nap", item.text)
        assertEquals(true, item.isDone)
    }
}