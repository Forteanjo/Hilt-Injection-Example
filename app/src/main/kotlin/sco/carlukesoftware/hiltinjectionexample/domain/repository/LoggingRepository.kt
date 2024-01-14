package sco.carlukesoftware.hiltinjectionexample.domain.repository

import androidx.annotation.WorkerThread
import sco.carlukesoftware.hiltinjectionexample.data.LogDao
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntriesList

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class LoggingRepository(private val logDao: LogDao) {

    // Room executes all queries on a separate thread.
    @WorkerThread
    suspend fun addLogEntry(logEntry: LogEntry) = logDao.addLogEntry(logEntry)

    @WorkerThread
    suspend fun addLogEntries(logEntries: LogEntriesList) = logDao.addLogEntries(logEntries)

    @WorkerThread
    suspend fun deleteLogEntry(logEntry: LogEntry) = logDao.deleteLogEntry(logEntry)

    @WorkerThread
    suspend fun deleteAllLogEntries() = logDao.deleteAllLogEntries()

    @WorkerThread
    suspend fun updateLogEntry(logEntry: LogEntry) = logDao.updateLogEntry(logEntry)

    @WorkerThread
    suspend fun getLogEntryById(id: Long) = logDao.getLogEntryById(id)

    fun getAllLogEntries() = logDao.getAllLogEntries()
}
