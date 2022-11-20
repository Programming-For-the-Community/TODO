package professorchaos0802.todo

import org.junit.Assert.assertEquals
import org.junit.Test
import professorchaos0802.todo.objects.MyItem

/**
 * Tests the creation of an Item object and the ability to edit item fields
 */
class ItemUnitTest {
    @Test
    fun item_creation(){
        var item = MyItem()
        var item1 = MyItem("cmhahm", "","Write Item object", true)

        // Test default creation
        assertEquals("", item.owner)
        assertEquals("", item.text)
        assertEquals(false, item.done)

        // Test Collection Path
        assertEquals("items", MyItem.COLLECTION_PATH)

        // Test custom creation
        assertEquals("cmhahm", item1.owner)
        assertEquals("Write Item object", item1.text)
        assertEquals(true, item1.done)
    }

    @Test
    fun item_editing(){
        var item = MyItem()

        // Verify creation
        assertEquals("", item.owner)
        assertEquals("", item.text)
        assertEquals(false, item.done)

        item.owner = "cmhahm"
        item.text = "Take a nap"
        item.done = true

        // Test changing values
        assertEquals("cmhahm", item.owner)
        assertEquals("Take a nap", item.text)
        assertEquals(true, item.done)
    }
}