package com.zj.example.arch.livedata.transform

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zj.example.arch.R
import kotlinx.android.synthetic.main.activity_transform_layout_1.*

/**
 *
 * CreateTime:2019/1/23  20:25
 * @author 郑炯
 * @version 1.0
 */
class Test1Activity : AppCompatActivity() {
    val liveData1 = MutableLiveData<Int>()
    val liveDataTransform1 = Transformations.switchMap(liveData1, object : android.arch.core.util.Function<Int, LiveData<String>> {
        override fun apply(input: Int?): LiveData<String> {
            println("liveDataTransform1 input-$input")
            return liveDataTransform3
        }
    })
    val liveDataTransform2 = Transformations.map(liveData1, object : Function<Int, String> {
        override fun apply(input: Int?): String {
            println("liveDataTransform2 input-$input")
            return input.toString() + "-str"
        }
    })

    val liveDataTransform3 = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform_layout_1)


        liveData1.observe(this, Observer<Int> { t ->
            println("onChanged -> $t")
        })
        liveDataTransform1.observe(this, Observer<String> { t ->
            println("liveDataTransform1 onChanged -> $t")
        })
        liveDataTransform2.observe(this, Observer<String> { t ->
            println("liveDataTransform2 onChanged -> $t")
        })

        //这里liveDataTransform3, 是接收不到liveData1传递过来的值的, 解决办法看Test1Activity
        liveDataTransform3.observe(this, Observer<String> { t ->
            println("liveDataTransform3 onChanged -> $t")
        })


        /**
         * 输出:
         *
        2019-01-24 14:08:21.300 28672-28672/com.zj.example.arch I/System.out: postValue -> 1
        2019-01-24 14:08:21.318 28672-28672/com.zj.example.arch I/System.out: onChanged -> 1
        2019-01-24 14:08:21.318 28672-28672/com.zj.example.arch I/System.out: liveDataTransform1 input-1
        2019-01-24 14:08:21.319 28672-28672/com.zj.example.arch I/System.out: liveDataTransform2 input-1
        2019-01-24 14:08:21.319 28672-28672/com.zj.example.arch I/System.out: liveDataTransform2 onChanged -> 1-str
         */
        button1.setOnClickListener {
            println("postValue -> 1")
            liveData1.postValue(1)
        }
    }
}