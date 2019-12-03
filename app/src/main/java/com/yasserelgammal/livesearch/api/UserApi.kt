package com.yasserelgammal.livesearch.api

import com.yasserelgammal.livesearch.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://192.168.1.200/user-api/"

interface UserApi {

    @GET("getcontacts.php")
    fun getContact(
        @Query("item_type") item_type:String,
        @Query("key") keyword:String
    ): Call<List<User>>

    companion object {
        operator fun invoke() : UserApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApi::class.java)
        }
    }
}