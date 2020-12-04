package com.example.globofly.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface MessageService {


    @GET
    fun getPromoMessage(@Url anotherUrl: String): Call<String>
}