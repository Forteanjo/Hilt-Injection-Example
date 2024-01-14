package sco.carlukesoftware.hiltinjectionexample.domain.usecases

import sco.carlukesoftware.hiltinjectionexample.domain.entity.InvalidLogEntryException
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.domain.repository.LoggingRepository
import javax.inject.Inject

class AddLogEntry @Inject constructor(private val repository: LoggingRepository) {

    suspend operator fun invoke(logEntry: LogEntry) {
        if (logEntry.msg.isBlank()) {
            throw InvalidLogEntryException("Log Entry message must not be blank")
        }

        repository.addLogEntry(logEntry = logEntry)
    }
}

