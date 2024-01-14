package sco.carlukesoftware.hiltinjectionexample.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntriesList
import sco.carlukesoftware.hiltinjectionexample.domain.repository.LoggingRepository
import sco.carlukesoftware.hiltinjectionexample.domain.utils.LogEntryOrder
import sco.carlukesoftware.hiltinjectionexample.domain.utils.OrderType

class GetAllLogEntries constructor(private val repository: LoggingRepository) {
    operator fun invoke(
        logEntryOrder: LogEntryOrder = LogEntryOrder.TimeStamp(OrderType.Descending)
    ): Flow<LogEntriesList> {
        return repository.getAllLogEntries().map { logEntries ->
            when (logEntryOrder.orderType) {
                is OrderType.Ascending -> {
                    when (logEntryOrder) {
                        is LogEntryOrder.Message -> logEntries.sortedBy { it.msg.lowercase() }
                        is LogEntryOrder.TimeStamp -> logEntries.sortedBy { it.timestamp }
                    }
                }

                is OrderType.Descending -> {
                    when (logEntryOrder) {
                        is LogEntryOrder.Message -> logEntries.sortedByDescending { it.msg.lowercase() }
                        is LogEntryOrder.TimeStamp -> logEntries.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
 }
