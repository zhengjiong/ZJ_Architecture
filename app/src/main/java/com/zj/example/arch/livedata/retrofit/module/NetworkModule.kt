package com.zj.example.arch.livedata.retrofit.module

import com.zj.example.arch.livedata.retrofit.repository.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * CreateTime:2019/1/24  18:57
 * @author 郑炯
 * @version 1.0
 */

val networkModule = NetworkModule()

class NetworkModule {

    val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()


    val apiRetrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://fuse-test.1919.cn/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
}