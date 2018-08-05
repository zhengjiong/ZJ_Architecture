package com.zj.example.arch.livedata.extention

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * Created by zhengjiong
 * Date: 2018/8/5 22:30
 */
class Test1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stockLiveData = StockLiveData("¥")

        stockLiveData.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                println("onChanged t=$t")
            }

        })
    }
}