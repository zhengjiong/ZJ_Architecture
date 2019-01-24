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
    val liveDataTransform2 = Transformations.map(liveData1, object : Function<Int, String> {
        override fun apply(input: Int?): String {
            println("liveDataTransform2 map input-$input")
            return input.toString() + "-str"
        }
    })

    val mediatorLiveData = MediatorLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform_layout_2)

        //mediatorLiveData.value = "init"
        val source1 = MutableLiveData<String>()

        mediatorLiveData.addSource(source1) {
            println("source onChanged ->$it")
        }

        liveData1.observe(this, Observer<Int> { t ->
            println("onChanged -> $t")
        })
        liveDataTransform1.observe(this, Observer<String> { t ->
            println("liveDataTransform1 onChanged -> $t")
        })
        liveDataTransform2.observe(this, Observer<String> { t ->
            println("liveDataTransform2 onChanged -> $t")
        })

        button1.setOnClickListener {
            println("postValue -> 1")
            liveData1.postValue(1)
        }

        button2.setOnClickListener {
            println("postValue -> zj")
            source1.postValue("zj")
        }
    }
}