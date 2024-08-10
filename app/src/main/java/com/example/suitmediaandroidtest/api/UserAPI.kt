package com.example.suitmediaandroidtest.api
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Definisikan antarmuka API untuk Retrofit
interface UserAPI {
    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<ResponseUser>
}
