package com.zj.example.arch.livedata.retrofit.repository

import com.zj.example.arch.livedata.retrofit.module.networkModule
import com.zj.example.arch.livedata.retrofit.api.DrinkService

/**
 *
 * CreateTime:2019/1/24  19:26
 * @author 郑炯
 * @version 1.0
 */
val drinkRepositoryInstance = DrinkRepository()
class DrinkRepository {
    val testService: DrinkService = networkModule.apiRetrofit.create(DrinkService::class.java)
}