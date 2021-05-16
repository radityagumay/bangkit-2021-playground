import org.junit.Test
import kotlin.test.assertNotNull

class FeedFactoryTest {

    @Test
    fun verifyFeedFactory() {
        val feed = FeedFactory.create()
        assertNotNull(feed)
    }
}