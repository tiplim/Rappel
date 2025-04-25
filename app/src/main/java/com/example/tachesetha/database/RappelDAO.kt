package com.example.tachesetha.database

import androidx.room.*
import com.example.tachesetha.entities.Rappel
import kotlinx.coroutines.flow.Flow

@Dao
interface RappelDAO {

    @Insert
    fun insert(rappel: Rappel)

    @Update
    fun update(rappel: Rappel)

    @Delete
    fun delete(rappel: Rappel)

    @Query("SELECT * FROM rappel_table WHERE id = :id")
    fun getRappelById(id: Int): Rappel?

    @Query("SELECT * FROM rappel_table")
    fun getAllRappels(): Flow<List<Rappel>>

    @Query("DELETE FROM rappel_table")
    fun deleteAll()
}
