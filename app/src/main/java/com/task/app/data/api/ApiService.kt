package com.task.app.data.api

import com.task.app.data.models.MedicineResponse
import retrofit2.http.GET

interface ApiService {
    @GET("ec7329b1-5134-43f8-8764-03212b8e1624")
    suspend fun getMedicines(): MedicineResponse
}