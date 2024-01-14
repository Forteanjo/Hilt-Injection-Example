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

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntriesList
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry

/**
 * Data access object to query the database.
 */
@Dao
interface LogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLogEntry(logEntry: LogEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLogEntries(logEntries: LogEntriesList)

    @Update
    suspend fun updateLogEntry(logEntry: LogEntry)

    @Delete
    suspend fun deleteLogEntry(logEntry: LogEntry)

    @Query("DELETE FROM logEntries")
    suspend fun deleteAllLogEntries()

    @Query("SELECT * FROM logEntries WHERE id = :id LIMIT 1")
    suspend fun getLogEntryById(id: Long): LogEntry?

    @Query("SELECT * FROM logEntries ORDER BY id DESC")
    fun getAllLogEntries(): Flow<LogEntriesList>
}
