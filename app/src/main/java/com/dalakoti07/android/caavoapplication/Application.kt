package com.dalakoti07.android.caavoapplication

import android.app.Application
import android.content.Context
import com.dalakoti07.android.caavoapplication.network.ApiService
import com.dalakoti07.android.caavoapplication.network.Networking

class Application : Application() {
    init {
        instance=this
    }

    companion object{
        private var instance: Application? = null

        val remoteServiceClient :ApiService= Networking.getRetrofitInstance()
        fun provideApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}