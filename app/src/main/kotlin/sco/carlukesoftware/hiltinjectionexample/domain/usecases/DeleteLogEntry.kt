package sco.carlukesoftware.hiltinjectionexample.domain.usecases

import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.domain.repository.LoggingRepository
import javax.inject.Inject

class DeleteLogEntry @Inject constructor(private val repository: LoggingRepository) {

    suspend operator fun invoke(logEntry: LogEntry) {
        repository.deleteLogEntry(logEntry = logEntry)
    }
}
