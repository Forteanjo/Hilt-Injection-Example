package sco.carlukesoftware.hiltinjectionexample.ui.events

import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.domain.utils.LogEntryOrder

sealed class LogEntryEvent {
    data class Order(val logEntryOrder: LogEntryOrder): LogEntryEvent()

    data class AddLogEntry(val logEntry: LogEntry): LogEntryEvent()
    data class DeleteLogEntry(val logEntry: LogEntry): LogEntryEvent()

    data object DeleteAllLogEntries: LogEntryEvent()

}