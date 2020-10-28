package com.example.vk.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VKRetrofitImpl {

    companion object {
        val instance = Retrofit.Builder()
            .baseUrl("https://api.vk.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build().create(VKUserApi::class.java)
    }
}