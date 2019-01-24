package com.zj.example.arch.livedata.transform

import android.arch.core.util.Function
import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zj.example.arch.R
import kotlinx.android.synthetic.main.activity_transform_layout_2.*

/**
 *
 * Company: 上加下信息技术成都有限公司
 * CreateTime:2019/1/23  20:25
 * @author 郑炯
 * @version 1.0
 */
class Test2Activity : AppCompatActivity() {
    val liveData1 = MutableLiveData<Int>()
    val liveDataTransform1 = Transformations.switchMap(liveData1, object : Function<Int, LiveData<String>> {
        override fun apply(input: Int?): LiveData<String> {
            println("liveDataTransform1 switchMap input-$input")
            return mediatorLiveData
        }
    })

    val mediatorLiveData = MediatorLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform_layout_2)

        mediatorLiveData.value = "init"
        val source1 = MutableLiveData<String>()

        mediatorLiveData.addSource(source1) {
            println("source onChanged ->$it")
            mediatorLiveData.value = it
        }

        mediatorLiveData.observe(this, Observer {
            println("mediatorLiveData onChange -> $it")
        })

        liveData1.observe(this, Observer<Int> { t ->
            println("liveData1 onChanged -> $t")
        })
        liveDataTransform1.observe(this, Observer<String> { t ->
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
            mediatorLiveData.postValue("zj")
        }
    }
}