package com.zj.example.arch.livedata.retrofit

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zj.example.arch.R
import com.zj.example.arch.livedata.retrofit.api.ApiResponse
import com.zj.example.arch.livedata.retrofit.api.GithubService
import com.zj.example.arch.livedata.retrofit.api.DrinkService
import com.zj.example.arch.livedata.retrofit.repository.UserRepository
import com.zj.example.arch.livedata.retrofit.repository.drinkRepositoryInstance
import com.zj.example.arch.livedata.retrofit.repository.util.LiveDataCallAdapterFactory
import kotlinx.android.synthetic.main.activity_transform_layout_3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * CreateTime:2019/1/23  20:25
 * @author 郑炯
 * @version 1.0
 */
class Test1Activity : AppCompatActivity() {
    val liveData1 = MutableLiveData<Int>()
    lateinit var userRepository: UserRepository
    lateinit var githubService: GithubService
    lateinit var drinkService: DrinkService
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

    /*val liveDataTransform3 = Transformations.switchMap(liveData1, object : Function<Int, LiveData<ApiResponse<Any>>> {
        override fun apply(input: Int?): LiveData<ApiResponse<Any>> {
            println("liveDataTransform3 switchMap input -> $input")
            return drinkService.init()
        }
    })*/

    val liveDataTransform4 = Transformations.switchMap(liveData1, object : Function<Int, LiveData<ApiResponse<Any>>> {
        override fun apply(input: Int?): LiveData<ApiResponse<Any>> {
            println("liveDataTransform4 switchMap input -> $input")
            return drinkRepositoryInstance.testService.init()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform_layout_3)

        githubService = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(GithubService::class.java)

        drinkService = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(DrinkService::class.java)

        userRepository = UserRepository(AppExecutors(), githubService)
        //mediatorLiveData.value = "init"

        /*initLiveData.observe(this, Observer {
            println("mediatorLiveData onChange -> $it")
        })*/

        liveData1.observe(this, Observer<Int> { t ->
            println("liveData1 onChanged -> $t")
        })
        /*liveDataTransform1.observe(this, Observer<Resource<User>> { t ->
            println("liveDataTransform1 onChanged -> $t")
        })*/

        /*liveDataTransform2.observe(this, Observer<ApiResponse<User>> { t ->
            println("liveDataTransform2 onChanged -> $t")
        })
        liveDataTransform3.observe(this, Observer<ApiResponse<Any>> { t ->
            println("liveDataTransform3 onChanged -> $t")
        })*/

        liveDataTransform4.observe(this, Observer<ApiResponse<Any>> {
            println("liveDataTransform4 onChanged -> $it")
        })

        /**
         * 从这里可以看出, Service中的livedata一旦被观察就可以执行网络请求的操作!
         */
        drinkRepositoryInstance.testService.init().observe(this, Observer<ApiResponse<Any>> {
            println("drinkRepositoryInstance.testService.init() onChange -> " + it)
        })

        button1.setOnClickListener {
            println("liveData1 postValue -> 1")
            liveData1.postValue(1)
        }

        button2.setOnClickListener {
            println("source1 postValue -> zj")
            drinkRepositoryInstance.testService.init()
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
