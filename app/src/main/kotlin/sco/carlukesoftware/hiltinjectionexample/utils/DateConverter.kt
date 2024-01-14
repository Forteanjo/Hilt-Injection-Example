package sco.carlukesoftware.hiltinjectionexample.utils

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun toDatabaseValue(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromDatabaseValue(value: Long?): Date? {
        return value?.let { Date(it) }
    }
}
