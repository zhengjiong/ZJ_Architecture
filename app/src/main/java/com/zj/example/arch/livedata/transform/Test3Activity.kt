package com.zj.example.arch.livedata.transform

import android.arch.core.util.Function
import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zj.example.arch.R
import kotlinx.android.synthetic.main.activity_transform_layout_3.*

/**
 *
 * CreateTime:2019/1/23  20:25
 * @author 郑炯
 * @version 1.0
 */
class Test3Activity : AppCompatActivity() {
    val liveData1 = MutableLiveData<Int>()
    val liveData2 = MutableLiveData<Long>()
    val liveDataTransform1 = Transformations.switchMap(liveData1, object : Function<Int, LiveData<String>> {
        override fun apply(input: Int?): LiveData<String> {
            println("liveDataTransform1 switchMap input -> $input")
            return mediatorLiveData
        }
    })

    val mediatorLiveData = MediatorLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform_layout_3)

        //mediatorLiveData.value = "init"
        val source1 = MutableLiveData<String>()

        mediatorLiveData.observe(this, Observer {
            println("mediatorLiveData onChange -> $it")
        })

        val observer = Observer<Int> { t ->
            println("liveData1 onChanged -> $t")
        }
        val observer2 = Observer<Long> { t ->
            println("liveData2 onChanged -> $t")
        }
        liveData1.observe(this, observer)
        liveData2.observe(this, observer2)

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

        button4.setOnClickListener {
            println("removeSource source1")
            mediatorLiveData.removeSource(source1)
        }
        button5.setOnClickListener {
            mediatorLiveData.addSource(source1) {
                println("source onChanged ->$it")
                mediatorLiveData.value = it
            }
        }
        button6.setOnClickListener {
            liveData2.removeObserver(observer2)
            println("liveData2 removeObserver")

        }
        button7.setOnClickListener {
            //remove后, 重新观察会接收到最后一次发出的数据
            liveData2.observe(this, observer2)
        }
        button8.setOnClickListener {
            liveData2.postValue(System.currentTimeMillis())
        }
    }
}