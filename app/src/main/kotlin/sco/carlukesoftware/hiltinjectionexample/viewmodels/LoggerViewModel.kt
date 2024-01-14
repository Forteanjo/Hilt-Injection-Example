package sco.carlukesoftware.hiltinjectionexample.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntriesList
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.LoggingUseCase
import javax.inject.Inject

@HiltViewModel
class LoggerViewModel @Inject constructor(private val useCase: LoggingUseCase) : ViewModel()  {

    val logs = useCase.getAllLogEntries

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean
        get() = _onLoading

//    fun getAllLogEntries() {
//        viewModelScope.launch(Dispatchers.IO) {
//            allLogs.postValue(loggingRepository.getAllLogEntries())
//        }
//    }

    fun addLogEntry(logEntry: LogEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            _onLoading = true

            useCase.addLogEntry(logEntry)
//            with(loggingRepository) {
//                addLogEntry(logEntry)
//                getAllLogEntries()
//            }

            _onLoading = false        }
    }

    fun addLogEntries(logEntries: LogEntriesList) {
        viewModelScope.launch(Dispatchers.IO) {
            _onLoading = true

            useCase.addLogEntries(logEntries)

//            with(loggingRepository) {
//                addLogEntries(logEntries)
//                getAllLogEntries()
//            }

            _onLoading = false
        }
    }

    fun addLogMsg(msg: String) {
        addLogEntry(
            LogEntry(
                msg = msg,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    fun deleteLogEntry(logEntry: LogEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteLogEntry(logEntry)
//            with(loggingRepository) {
//                deleteLogEntry(logEntry)
//                getAllLogEntries()
//            }
        }
    }

    fun deleteAllLogEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteAllLogEntries
//            with(loggingRepository) {
//                deleteAllLogEntries()
//                getAllLogEntries()
//            }
        }
    }

    fun updateLogEntry(logEntry: LogEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.updateLogEntry(logEntry)
//            with(loggingRepository) {
//                updateLogEntry(logEntry)
//                getAllLogEntries()
//            }
        }
    }

//    fun selectLogEntryById(id: Long): LogEntry? {
//        viewModelScope.launch(Dispatchers.IO) {
//            return@launch useCase.selectLogEntryById(id = id)
//            //logEntry.postValue(loggingRepository.selectLogEntryById(id))
//        }
//    }

}