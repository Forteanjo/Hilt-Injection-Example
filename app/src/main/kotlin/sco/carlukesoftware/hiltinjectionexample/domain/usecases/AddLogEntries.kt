package sco.carlukesoftware.hiltinjectionexample.domain.usecases

import sco.carlukesoftware.hiltinjectionexample.domain.entity.InvalidLogEntryException
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntriesList
import sco.carlukesoftware.hiltinjectionexample.domain.repository.LoggingRepository
import javax.inject.Inject

class AddLogEntries @Inject constructor(private val repository: LoggingRepository) {

    suspend operator fun invoke(logEntries: LogEntriesList) {
        if (logEntries.isNotEmpty()) {
            logEntries.forEach { logEntry ->
                if (logEntry.msg.isBlank()) {
                    throw InvalidLogEntryException("Log Entry message must not be blank")
                }
            }

            repository.addLogEntries(logEntries = logEntries)
        }
    }
}