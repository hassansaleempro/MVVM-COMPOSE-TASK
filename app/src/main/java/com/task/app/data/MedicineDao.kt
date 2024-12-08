package com.task.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.app.data.models.Medicine

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(medicines: List<Medicine>)

    @Query("SELECT * FROM medicines")
    suspend fun getMedicines(): List<Medicine>

    @Query("SELECT * FROM medicines WHERE name = :name")
    suspend fun getMedicineByName(name: String): Medicine?
}