import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

fun main() {
    ReactiveProgramming()
}

// Observable
// Operator
// Observer
class ReactiveProgramming {
    val kafka = Kafka

    init {
        kafka.topic
            .filter { it is Food }
            .doOnNext {
                println("Food Topic is : $it")
            }
            .subscribe()

        kafka.publishTopic(Food.Merchant(1, "kfc"))
        kafka.publishTopic(Food.Merchant(2, "kfc-depok"))
        kafka.publishTopic(Pay.Payment(1, Pay.PaymentType.CASH, "Rp.2500"))
    }
}

object Kafka {
    private val _publisher = ReplaySubject.create<Topic>()
    val topic: Observable<Topic>
        get() = _publisher.publish().refCount().hide()

    fun publishTopic(topic: Topic) {
        _publisher.onNext(topic)
    }
}

sealed class Topic

// GoPay
sealed class Pay : Topic() {
    enum class PaymentType {
        CASH,
        CREDIT_CARD,
        PAYLATER,
        GOPAY
    }

    data class Payment(
        val id: Int,
        val type: PaymentType,
        val amount: String
    ) : Pay()
}

// Food
sealed class Food : Topic() {
    data class Merchant(
        val id: Int,
        val name: String
    ) : Food()
}

