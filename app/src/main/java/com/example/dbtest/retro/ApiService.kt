package com.example.dbtest.retro

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/v6/14d1f39163093ce988815b28/latest/INR")
    fun fetchInr(): Call<ConvertResponse>

    @GET("/v6/14d1f39163093ce988815b28/latest/USD")
    fun fetchUsd(): Call<ConvertResponse>

    @GET("/v6/14d1f39163093ce988815b28/latest/EUR")
    fun fetchEur(): Call<ConvertResponse>

    @GET("/v6/14d1f39163093ce988815b28/latest/GBP")
    fun fetchGbp(): Call<ConvertResponse>

    @GET("/v6/14d1f39163093ce988815b28/latest/AUD")
    fun fetchAud(): Call<ConvertResponse>

    @GET("/v6/14d1f39163093ce988815b28/latest/CAD")
    fun fetchCad(): Call<ConvertResponse>
}