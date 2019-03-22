package com.zj.example.arch.livedata.singlelivedata

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zj.example.arch.R
import kotlinx.android.synthetic.main.activity_singlelivedata_layout.*

/**
 *
 * Company: 上加下信息技术成都有限公司
 * CreateTime:2019/3/22  13:48
 * @author 郑炯
 * @version 1.0
 */
class Test1Activity : AppCompatActivity() {
    private val _singleLiveData = SingleLiveEvent<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singlelivedata_layout)


        button1.setOnClickListener {
            _singleLiveData.value = ""
        }

        button2.setOnClickListener {
            _singleLiveData.call()
        }

        //使用SingleLiveEvent后, 只有第一个订阅的Observer才会收到结果
        _singleLiveData.observe(this, Observer {
            println("observer1")
        })
        _singleLiveData.observe(this, Observer {
            println("observer2")
        })
    }
}