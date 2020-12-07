package com.example.globofly.services

import com.example.globofly.helpers.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {

    @GET("destination")
    fun getDestinationList(): Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    @GET("destination")
    fun getIndiaDestinations(@QueryMap filter: HashMap<String, String>): Call<List<Destination>>

    @POST("/destination")
    fun addDestination(@Body newDestination: Destination): Call<Destination>
}