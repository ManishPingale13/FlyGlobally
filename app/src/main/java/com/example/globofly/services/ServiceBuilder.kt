package com.example.globofly.services

import android.annotation.SuppressLint
import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    //Before release, Change this URL
    private const val URL = "http://192.168.2.4:9000/"

    private val logger
        get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //Create a custom interceptor
    @SuppressLint("ConstantLocale")
    private val headerInterceptor: Interceptor = Interceptor { chain ->
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("x-device-type", Build.DEVICE)
            .addHeader("Accept-Language", Locale.getDefault().language)
            .build()

        chain.proceed(request)
    }

    //Create okHttp Client
    private val okHttp =
        OkHttpClient.Builder().addInterceptor(headerInterceptor).addInterceptor(logger)
            .callTimeout(6, TimeUnit.SECONDS)


    //Create Retrofit Builder
    private val builder =
        Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    //Create Retrofit Instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}