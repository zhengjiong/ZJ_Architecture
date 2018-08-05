package com.zj.example.arch.livedata.basic1

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


/**
 * Created by zhengjiong
 * date: 2018/8/5 21:50
 */
class Test1ViewModel : ViewModel() {

    var mCurrrentName = MutableLiveData<String>()


}