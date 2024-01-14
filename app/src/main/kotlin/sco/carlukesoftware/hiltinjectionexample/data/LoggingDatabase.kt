package sco.carlukesoftware.hiltinjectionexample.data
/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.utils.DateConverter

/**
 * SQLite Database for storing the logs.
 */
@Database(
    entities = [
        LogEntry::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class LoggingDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao

    companion object {
        /**
         * Create as a Singleton - prevents multiple instances of database opening at the
         * same time.
         *
         * Note - The value of a volatile variable will never be cached, and all writes and reads
         * will be done to and from the main memory.
         * This helps make sure the value of INSTANCE is always up-to-date and the same for all
         * execution threads. It means that changes made by one thread to INSTANCE are visible to
         * all other threads immediately.
         */
        @Volatile
        private var INSTANCE: LoggingDatabase? = null

        private const val DATABASE_NAME: String = "logging.db"

        fun getDatabase(
            context: Context,
        ): LoggingDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoggingDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                //return instance
                instance
            }
        }
    }
}
