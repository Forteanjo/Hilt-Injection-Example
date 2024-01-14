package sco.carlukesoftware.hiltinjectionexample.domain.usecases

import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.domain.repository.LoggingRepository
import javax.inject.Inject

class GetLogEntryById @Inject constructor(private val loggingRepository: LoggingRepository) {
    suspend operator fun invoke(id: Long): LogEntry? {
        return loggingRepository.getLogEntryById(id = id)
    }
}