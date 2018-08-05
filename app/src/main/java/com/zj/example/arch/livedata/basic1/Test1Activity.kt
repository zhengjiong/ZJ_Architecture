package com.zj.example.arch.livedata.basic1

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity

/**
 * Created by zhengjiong
 * date: 2018/8/5 21:34
 */

class Test1Activity : AppCompatActivity() {
    lateinit var mViewModel: Test1ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(Test1ViewModel::class.java)

        mViewModel.mCurrrentName.observe(this, Observer<String> {
            println("it=$it")
        })


        /**
         * 必须要从主线程调用setValue(T) 方法来更新LiveData 对象.
         * 如果代码在工作线程中执行, 你可以使用postValue(T) 方法来更新LiveData对象.
         */
        mViewModel.mCurrrentName.value = "a"

        Thread(Runnable {
            /**
             * 必须加sleep暂停时间, 上面才能有3次输出, 不然只会输出c
             */
            mViewModel.mCurrrentName.postValue("b")
            SystemClock.sleep(100)
            mViewModel.mCurrrentName.postValue("c")
            SystemClock.sleep(100)
            mViewModel.mCurrrentName.postValue("d")
        }).start()
    }
}