import kotlinx.html.*
import kotlinx.html.stream.appendHTML

fun main() {
    val uiHook: FrameworkHook = HtmlHook()
    FeedView(uiHook)
}

interface FrameworkHook {
    fun display(feed: Feed)
}

class HtmlHook : FrameworkHook {
    override fun display(feed: Feed) {
        System.out.appendHTML().html {
            body {
                h1 {
                    +"Feed Id: ${feed.id}"
                }

                p {
                    +"Feed Description : ${feed.description}"
                }

                div {
                    +"Date : ${feed.date}"
                    p {
                        +"Url : ${feed.url}"
                    }
                }
            }
        }
    }
}

class FeedView(
    private val uiHook: FrameworkHook
) : FeedContract.View {

    private val repository: FeedRepository = FakeFeedRepositoryImpl()
    private val presenter: FeedContract.Presenter = PresenterImpl(this, repository)

    init {
        presenter.execute()
    }

    override fun feedsIsLoaded(feeds: List<Feed>) {
        feeds.forEach(uiHook::display)
    }
}

interface FeedContract {
    interface Presenter {
        fun execute()
    }

    interface View {
        fun feedsIsLoaded(feeds: List<Feed>)
    }
}

class PresenterImpl(
    private val view: FeedContract.View,
    private val repository: FeedRepository
) : FeedContract.Presenter {
    override fun execute() {
        view.feedsIsLoaded(repository.feeds())
    }
}

interface FeedRepository {
    fun feeds(): List<Feed>
}

data class Feed(
    val id: String,
    val url: String,
    val description: String,
    val date: Long
)

// Fake
class FakeFeedRepositoryImpl : FeedRepository {
    override fun feeds(): List<Feed> {
        return FeedFactory.create()
    }
}

// Stub
object FeedFactory {
    fun create(): List<Feed> {
        return listOf(
            Feed(
                id = "12-abc",
                url = "https://www.google.com",
                description = """
                    lorem ipsum dolom sir amet
                """.trimIndent(),
                date = System.currentTimeMillis()
            )
        )
    }
}