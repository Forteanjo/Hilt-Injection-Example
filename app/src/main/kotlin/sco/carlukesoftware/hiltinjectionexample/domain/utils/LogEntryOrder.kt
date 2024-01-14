package sco.carlukesoftware.hiltinjectionexample.domain.utils

sealed class LogEntryOrder(val orderType: OrderType) {
    class Message(orderType: OrderType): LogEntryOrder(orderType)
    class TimeStamp(orderType: OrderType): LogEntryOrder(orderType)

    fun copy(orderType: OrderType): LogEntryOrder {
        return when(this) {
            is Message -> Message(orderType)
            is TimeStamp -> Message(orderType)
        }
    }
}
