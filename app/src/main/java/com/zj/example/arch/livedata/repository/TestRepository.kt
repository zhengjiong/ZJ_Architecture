package com.zj.example.arch.livedata.repository

import com.zj.example.arch.livedata.module.networkModule
import com.zj.example.arch.livedata.repository.api.TestApi

/**
 *
 * CreateTime:2019/1/24  19:26
 * @author 郑炯
 * @version 1.0
 */
val testRepositoryInstance = TestRepository()
class TestRepository {
    val testService: TestApi = networkModule.apiRetrofit.create(TestApi::class.java)
}