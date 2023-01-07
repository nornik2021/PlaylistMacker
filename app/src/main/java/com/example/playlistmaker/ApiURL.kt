package com.example.playlistmaker

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiURL {
    private const val BASEURL = "https://itunes.apple.com/"
    private val logIn = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder().addInterceptor(logIn)
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASEURL)
        .client(httpClient.build())
        .build()
    val apiServ: ItunesApi = retrofit.create(ItunesApi::class.java)
}