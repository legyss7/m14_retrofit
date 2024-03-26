package com.hw14

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val searchUser: SearchUserApi = retrofit.create(SearchUserApi::class.java)
}


interface SearchUserApi {
    @GET("/api/")
    suspend fun getUser(): UserModel
}