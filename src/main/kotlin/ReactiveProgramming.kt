import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

fun main() {
    ReactiveProgramming()
}

// Observable
// Operator
// Observer
class ReactiveProgramming {
    val podcast = Podcast

    init {
        val bobby = podcast.topic
            .filter {
                println(System.currentTimeMillis())
                it is Deddy
            }
            .debounce(1, TimeUnit.SECONDS)
            .doOnNext {
                println(System.currentTimeMillis())
                println("Bobby Podcast Topic is : $it")
            }

        podcast.publishTopic(AndroidDeveloper.JetpackCompose("florena"))
        podcast.publishTopic(AndroidDeveloper.JetpackCompose("cheet"))
        podcast.publishTopic(Deddy.CloseTheDoor)
    }
}

object Podcast {
    private val _publisher = PublishSubject.create<Topic>()
    val topic: Observable<Topic>
        get() = _publisher.publish().refCount().hide()

    fun publishTopic(topic: Topic) {
        _publisher.onNext(topic)
    }
}

sealed class Topic

// GoPay
sealed class AndroidDeveloper : Topic() {
    data class JetpackCompose(
        val author: String
    ) : AndroidDeveloper()
}

// Deddy
sealed class Deddy : Topic() {
    object CloseTheDoor : Deddy()
}

