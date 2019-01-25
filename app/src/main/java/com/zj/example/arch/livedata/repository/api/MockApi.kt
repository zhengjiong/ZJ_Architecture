package com.zj.example.arch.livedata.repository.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.zj.example.arch.livedata.repository.model.ApiResponse

/**
 *
 * CreateTime:2019/1/24  18:48
 * @author 郑炯
 * @version 1.0
 */
class MockApi : TestApi {
    override fun kuaiheInit(): LiveData<ApiResponse> {
        return MutableLiveData<ApiResponse>()
    }
}