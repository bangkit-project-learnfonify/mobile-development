package com.capstone.learnfonify.data.retrofit


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjYsIm5hbWUiOiJNdWhhbW1hZCBSaXpreSBMdWJpcyIsImVtYWlsIjoibXVoYW1tYWRyaXpreWx1YmlzQHN0dWRlbnRzLnBvbG1lZC5hYy5pZCIsImlhdCI6MTcwMjk1ODk2NSwiZXhwIjoxNzAyOTgwNTY1fQ.FgcrMV0kkHmyBS1DlQpX1wGHY-2sEeDtKG4icBuR8kQ")
                    .build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://cloud-computing-chi.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
