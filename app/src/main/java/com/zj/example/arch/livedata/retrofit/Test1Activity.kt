package com.zj.example.arch.livedata.retrofit

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zj.example.arch.R
import com.zj.example.arch.livedata.module.networkModule
import com.zj.example.arch.livedata.repository.api.TestApi
import com.zj.example.arch.livedata.repository.model.ApiResponse
import com.zj.example.arch.livedata.repository.testRepositoryInstance
import kotlinx.android.synthetic.main.activity_transform_layout_3.*

/**
 *
 * CreateTime:2019/1/23  20:25
 * @author 郑炯
 * @version 1.0
 */
class Test1Activity : AppCompatActivity() {
    val liveData1 = MutableLiveData<Int>()
    lateinit var initLiveData: LiveData<ApiResponse>
    val liveDataTransform1 = Transformations.switchMap(liveData1, object : Function<Int, LiveData<ApiResponse>> {
        override fun apply(input: Int?): LiveData<ApiResponse> {
            println("liveDataTransform1 switchMap input -> $input")
            return initLiveData
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform_layout_3)

        val testService = networkModule.apiRetrofit.create(TestApi::class.java)
        //initLiveData = testRepositoryInstance.testService.kuaiheInit()
        initLiveData = testService.kuaiheInit()
        //mediatorLiveData.value = "init"
        val source1 = MutableLiveData<String>()

        initLiveData.observe(this, Observer {
            println("mediatorLiveData onChange -> $it")
        })

        liveData1.observe(this, Observer<Int> { t ->
            println("liveData1 onChanged -> $t")
        })
        liveDataTransform1.observe(this, Observer<ApiResponse> { t ->
            println("liveDataTransform1 onChanged -> $t")
        })

        button1.setOnClickListener {
            println("liveData1 postValue -> 1")
            liveData1.postValue(1)
        }

        button2.setOnClickListener {
            println("source1 postValue -> zj")
            source1.postValue("zj")
        }

        button3.setOnClickListener {
            println("mediatorLiveData postValue -> zj")
            //mediatorLiveData.postValue("zj")
        }

        button4.setOnClickListener {
            println("removeSource source1")
            //mediatorLiveData.removeSource(source1)
        }
        button5.setOnClickListener {
            /*mediatorLiveData.addSource(source1) {
                println("source onChanged ->$it")
                mediatorLiveData.value = it
            }*/
        }
    }
}
