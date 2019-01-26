package com.zj.example.arch.livedata.retrofit

import android.arch.core.util.Function
import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zj.example.arch.R
import com.zj.example.arch.livedata.retrofit.api.ApiResponse
import com.zj.example.arch.livedata.retrofit.api.GithubService
import com.zj.example.arch.livedata.retrofit.api.KuaiHeService
import com.zj.example.arch.livedata.retrofit.repository.UserRepository
import com.zj.example.arch.livedata.retrofit.repository.kuaiHeRepositoryInstance
import com.zj.example.arch.livedata.retrofit.repository.util.LiveDataCallAdapterFactory
import com.zj.example.arch.livedata.retrofit.vo.Resource
import com.zj.example.arch.livedata.retrofit.vo.User
import kotlinx.android.synthetic.main.activity_transform_layout_3.*
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * CreateTime:2019/1/23  20:25
 * @author 郑炯
 * @version 1.0
 */
class Test2Activity : AppCompatActivity() {
    lateinit var kuaiheService: KuaiHeService
    var liveData1 = MutableLiveData<Int>()
    /*val liveDataTransform1 = Transformations.switchMap(liveData1, object : Function<Int, LiveData<Resource<User>>> {
        override fun apply(input: Int?): LiveData<Resource<User>> {
            println("liveDataTransform1 switchMap input -> $input")
            return userRepository.loadUser()
        }
    })
    val liveDataTransform2 = Transformations.switchMap(liveData1, object : Function<Int, LiveData<ApiResponse<User>>> {
        override fun apply(input: Int?): LiveData<ApiResponse<User>> {
            println("liveDataTransform2 switchMap input -> $input")
            return githubService.getUser("zhengjiong")
        }
    })*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform_layout_3)

        kuaiheService = Retrofit.Builder()
                .baseUrl("https://fuse-test.1919.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(KuaiHeService::class.java)


        /**
         * 从这里可以看出, KuaiHeService中的livedata一旦被观察就可以执行网络请求的操作!
         */
        //val initLiveData = kuaiHeRepositoryInstance.testService.init()
        val initLiveData by lazy {
            kuaiheService.init()
        }


        val mediatorLiveData: MediatorLiveData<ApiResponse<Any>> = MediatorLiveData()
        val mediatorLiveData2: MediatorLiveData<Int> = MediatorLiveData()


        button1.setOnClickListener {
            println("initLiveData observe")
            initLiveData.observe(this, Observer<ApiResponse<Any>> {
                println("initLiveData onChange -> " + it)
            })
        }

        button2.setOnClickListener {
            println("addSource initLiveData")
            mediatorLiveData.addSource(initLiveData, object : Observer<ApiResponse<Any>> {
                override fun onChanged(t: ApiResponse<Any>?) {
                    println("source onChanged 1 -> " + t)
                }
            })

        }

        mediatorLiveData.addSource(liveData1, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                println("source onChanged 2 -> " + t)
                //mediatorLiveData.value = t
            }
        })


        mediatorLiveData.observe(this, object : Observer<ApiResponse<Any>> {
            override fun onChanged(t: ApiResponse<Any>?) {
                println("mediatorLiveData2 onChanged -> " + t)
            }
        })

        mediatorLiveData2.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                println("mediatorLiveData2 onChanged -> " + t)
            }
        })

        button3.setOnClickListener {
            println("liveData1 postValue -> 1")
            liveData1.postValue(1)
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
