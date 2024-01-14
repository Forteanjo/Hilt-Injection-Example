package sco.carlukesoftware.hiltinjectionexample.domain.usecases

data class LoggingUseCase(
    val getAllLogEntries: GetAllLogEntries,
    val addLogEntry: AddLogEntry,
    val addLogEntries: AddLogEntries,
    val getLogEntryById: GetLogEntryById,
    val deleteLogEntry: DeleteLogEntry,
    val deleteAllLogEntries: DeleteAllLogEntries,
    val updateLogEntry: UpdateLogEntry
)
