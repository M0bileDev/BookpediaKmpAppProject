package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabase
import java.io.File

const val BOOKPEDIA = "Bookpedia"

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDirectory = when {
            os.contains("win") -> File(System.getenv("APPDATA"), BOOKPEDIA)
            os.contains("mac") -> File(userHome, "Library/Application Support/$BOOKPEDIA")
            else -> File(userHome, ".local/share/$BOOKPEDIA")
        }

        if(!appDirectory.exists()){
            appDirectory.mkdir()
        }
    }
}