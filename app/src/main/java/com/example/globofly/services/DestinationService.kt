package com.example.globofly.services

import com.example.globofly.helpers.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {


    @Headers("x-device-type: Android")//Static Header
    @GET("destination")
    fun getDestinationList(@Header("Accept-Language") language: String): Call<List<Destination>>//Dynamic Header

    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    @GET("destination")
    fun getIndiaDestinations(@QueryMap filter: HashMap<String, String>): Call<List<Destination>>

    @POST("/destination")
    fun addDestination(@Body newDestination: Destination): Call<Destination>

    @FormUrlEncoded
    @PUT("/destination/{id}")
    fun updateDestination(
        @Path("id") id: Int,
        @Field("city") city: String,
        @Field("country") country: String,
        @Field("description") description: String,
    ): Call<Destination>

    @DELETE("/destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<Unit>
}