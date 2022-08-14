package professorchaos0802.todo

import org.junit.Test

import org.junit.Assert.*
import professorchaos0802.todo.objects.User

/**
 * Tests the creation of a User object and the ability to edit user fields
 */
class UserUnitTest {
    @Test
    fun user_creation() {
        val user1 = User()
        val user2 = User("cmhahm", "http://www.fakeurl.com", "Red", "Helvetica", true, true)

        // Test User creation without custom fields
        assertEquals("JDoe", user1.username)
        assertEquals("", user1.img)
        assertEquals("Blue", user1.theme)
        assertEquals("", user1.font)
        assertEquals(false, user1.hasCompletedSetup)
        assertEquals(false, user1.isVisible)

        // Test User creation using custom fields
        assertEquals("cmhahm", user2.username)
        assertEquals("http://www.fakeurl.com", user2.img)
        assertEquals("Red", user2.theme)
        assertEquals("Helvetica", user2.font)
        assertEquals(true, user2.hasCompletedSetup)
        assertEquals(true, user2.isVisible)

        // Test Companion Object Fiels
        assertEquals("users", User.COLLECTION_PATH)

    }

    @Test
    fun user_edit(){
        val user = User()

        // Verify user creation
        assertEquals("JDoe", user.username)
        assertEquals("", user.img)
        assertEquals("Blue", user.theme)
        assertEquals("", user.font)
        assertEquals(false, user.hasCompletedSetup)
        assertEquals(false, user.isVisible)

        // Change User fields
        user.username = "cmhahm"
        user.theme = "Red"
        user.font = "Helvetica"
        user.img = "http://www.fakeurl.com"
        user.hasCompletedSetup = true
        user.isVisible = true

        // Test User fields changed
        assertEquals("cmhahm", user.username)
        assertEquals("http://www.fakeurl.com", user.img)
        assertEquals("Red", user.theme)
        assertEquals("Helvetica", user.font)
        assertEquals(true, user.hasCompletedSetup)
        assertEquals(true, user.isVisible)
    }
}