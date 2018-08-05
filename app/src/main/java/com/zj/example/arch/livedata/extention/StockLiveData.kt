package com.zj.example.arch.livedata.extention

import android.arch.lifecycle.LiveData

/**
 * 扩展LiveData
 * Created by zhengjiong
 * date: 2018/8/5 22:31
 */
class StockLiveData(symbol: String) : LiveData<Int>() {
    var mStockManager: StockManager = StockManager(symbol)

    val mListener = object : SimplePriceListener {
        override fun onPriceChanged(price: Int) {
            //setValue(T)方法更新LiveData实例的值，并通知活动观察者有关更改。
            value = price
        }
    }

    /**
     * 当LiveData对象有一个活跃的Observer时，onActive()方法被调用。 这意味着你需要从这个方法开始观察股票价格的更新。
     */
    override fun onActive() {
        super.onActive()
        println("onActive")
    }

    /**
     * 当LiveData对象没有任何活跃的Observer时，onInactive()方法被调用。 由于没有Observer在监听，所以没有理由继续保持与StockManager服务的连接。
     *
     */
    override fun onInactive() {
        super.onInactive()
        println("onInactive")
    }
}

class StockManager(symbol: String) {
    fun requestPriceUpdates(listener: SimplePriceListener) {

    }

    fun removeUpdates(listener: SimplePriceListener) {

    }
}

interface SimplePriceListener {
    fun onPriceChanged(price: Int)
}