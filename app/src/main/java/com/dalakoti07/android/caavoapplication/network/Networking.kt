package com.dalakoti07.android.caavoapplication.network

import com.dalakoti07.android.caavoapplication.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object Networking {
    //    object helps in achieving singleton pattern in android
    private const val NETWORK_CALL_TIMEOUT = 60
    private lateinit var apiClient:ApiService

    fun create(): ApiService {
        Timber.d("creating new retrofit instance")
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://s3-ap-southeast-1.amazonaws.com/he-public-data/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .apply {
                                level = if (BuildConfig.DEBUG)
                                    HttpLoggingInterceptor.Level.BODY
                                else
                                    HttpLoggingInterceptor.Level.NONE
                            })
//                    .addInterceptor(HeaderInterceptor())
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    fun getRetrofitInstance(): ApiService{
        if(this::apiClient.isInitialized){
            return apiClient
        }else{
            apiClient=create()
            return apiClient
        }
    }
}