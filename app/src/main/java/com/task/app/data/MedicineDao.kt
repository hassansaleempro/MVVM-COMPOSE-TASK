package com.task.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.task.app.data.models.Medicine

@Dao
interface MedicineDao {
    @Insert
    suspend fun insertMedicines(medicines: List<Medicine>)

    @Query("SELECT * FROM medicines")
    suspend fun getMedicines(): List<Medicine>

    @Query("SELECT * FROM medicines WHERE id = :id")
    suspend fun getMedicineById(id: String): Medicine?
}