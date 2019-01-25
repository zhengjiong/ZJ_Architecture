package com.zj.example.arch.livedata.repository.util


import android.arch.lifecycle.LiveData

import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

/**
 * A Retrofit adapterthat converts the Call into a LiveData of ApiResponse.
 *
 * @param <R>
</R> */
class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<String>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): LiveData<String> {
        return object : LiveData<String>() {
            internal var started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response.raw().toString())
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(throwable.message)
                        }
                    })
                }
            }
        }
    }
}
