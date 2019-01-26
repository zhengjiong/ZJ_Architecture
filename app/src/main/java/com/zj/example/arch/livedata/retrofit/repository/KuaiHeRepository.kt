package com.zj.example.arch.livedata.retrofit.repository

import com.zj.example.arch.livedata.retrofit.module.networkModule
import com.zj.example.arch.livedata.retrofit.api.KuaiHeService

/**
 *
 * CreateTime:2019/1/24  19:26
 * @author 郑炯
 * @version 1.0
 */
val kuaiHeRepositoryInstance = KuaiHeRepository()
class KuaiHeRepository {
    val testService: KuaiHeService = networkModule.apiRetrofit.create(KuaiHeService::class.java)
}