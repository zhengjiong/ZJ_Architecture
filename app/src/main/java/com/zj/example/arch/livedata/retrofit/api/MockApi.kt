package com.zj.example.arch.livedata.retrofit.api

import android.arch.lifecycle.LiveData
import org.json.JSONObject

/**
 *
 * CreateTime:2019/1/24  18:48
 * @author 郑炯
 * @version 1.0
 */
class MockApi : KuaiHeService {
    override fun init2(): LiveData<ApiResponse<JSONObject>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init(): LiveData<ApiResponse<Any>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}