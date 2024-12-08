package com.task.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.app.data.MedicineDao
import com.task.app.data.models.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}