package com.plcoding.bookpedia.book.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

const val DATABASE_NAME = "book.db"

@Database(
    entities = [BookEntity::class],
    version = 1
)
@TypeConverters(
    StringListTypeConverter::class
)
abstract class FavoriteBookDatabase : RoomDatabase() {
    abstract val favoriteBookDao: FavoriteBookDao
}