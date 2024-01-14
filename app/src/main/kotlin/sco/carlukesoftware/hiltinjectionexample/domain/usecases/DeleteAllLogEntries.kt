package sco.carlukesoftware.hiltinjectionexample.domain.usecases

import sco.carlukesoftware.hiltinjectionexample.domain.repository.LoggingRepository
import javax.inject.Inject

class DeleteAllLogEntries @Inject constructor(private val repository: LoggingRepository) {

    suspend operator fun invoke() {
        repository.deleteAllLogEntries()
    }
}