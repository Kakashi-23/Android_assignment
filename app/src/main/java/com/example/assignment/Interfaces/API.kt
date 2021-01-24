package com.example.assignment.Interfaces

import com.example.assignment.Models.LanguageData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("search/repositories")
    fun getRepository(@Query("q") q:String):Call<LanguageData>

}