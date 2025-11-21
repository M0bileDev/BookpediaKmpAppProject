package com.plcoding.bookpedia.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {

    @Upsert
    suspend fun upsert(bookEntity: BookEntity)

    @Query("SELECT * FROM bookentity")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM bookentity WHERE id=:id LIMIT 1")
    fun getFavoriteBook(id: String): Flow<BookEntity?>

    @Query("DELETE FROM bookentity WHERE id=:id")
    suspend fun deleteFavoriteBook(id: String): BookEntity?
}