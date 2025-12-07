package com.example.mob2

import retrofit2.http.GET
import retrofit2.http.Path

interface HPApiService {

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): List<HPCharacter>

    @GET("api/characters/staff")
    suspend fun getStaff(): List<HPCharacter>

    @GET("api/characters/house/{house}")
    suspend fun getStudentsByHouse(@Path("house") house: String): List<HPCharacter>
}
