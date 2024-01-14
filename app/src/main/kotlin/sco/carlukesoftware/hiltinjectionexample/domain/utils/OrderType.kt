package sco.carlukesoftware.hiltinjectionexample.domain.utils

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}
