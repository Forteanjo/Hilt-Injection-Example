package sco.carlukesoftware.hiltinjectionexample.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sco.carlukesoftware.hiltinjectionexample.data.LogDao
import sco.carlukesoftware.hiltinjectionexample.data.LoggingDatabase
import sco.carlukesoftware.hiltinjectionexample.domain.repository.LoggingRepository
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.AddLogEntries
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.AddLogEntry
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.DeleteAllLogEntries
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.DeleteLogEntry
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.GetAllLogEntries
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.GetLogEntryById
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.LoggingUseCase
import sco.carlukesoftware.hiltinjectionexample.domain.usecases.UpdateLogEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLogDao(database: LoggingDatabase): LogDao {
        return database.logDao()
    }

    @Singleton
    @Provides
    fun provideLoggingRepository(logDao: LogDao): LoggingRepository {
        return LoggingRepository(logDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LoggingDatabase = LoggingDatabase.getDatabase(context)
//        return Room.databaseBuilder(
//            context.applicationContext,
//            LoggingDatabase::class.java,
//            "logging.db"
//        ).build()
//    }

    @Provides
    fun providesUseCase(loggingRepository: LoggingRepository) = LoggingUseCase(
        getAllLogEntries = GetAllLogEntries(loggingRepository),
        addLogEntry = AddLogEntry(loggingRepository),
        addLogEntries = AddLogEntries(loggingRepository),
        getLogEntryById = GetLogEntryById(loggingRepository),
        deleteLogEntry = DeleteLogEntry(loggingRepository),
        deleteAllLogEntries = DeleteAllLogEntries(loggingRepository),
        updateLogEntry = UpdateLogEntry(loggingRepository)
    )
}