package com.zj.example.arch

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zj.example.arch.livedata.basic1.Test1Activity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zhengjiong
 * Date: 2018/8/5 20:46
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(listOf(
                Item("example1-livedata-viewmodel基本使用", Test1Activity::class.java),
                Item("example2-livedata-扩展", com.zj.example.arch.livedata.extention.Test1Activity::class.java),
                Item("example3-livedata-transformations-map-switch-1", com.zj.example.arch.livedata.transform.Test1Activity::class.java),
                Item("example4-livedata-transformations-map-switch-2-mediatorlivedata", com.zj.example.arch.livedata.transform.Test2Activity::class.java),
                Item("example5-livedata-transformations-map-switch-3-addsource", com.zj.example.arch.livedata.transform.Test3Activity::class.java),
                Item("example6-livedata-transformations-retrofit", com.zj.example.arch.livedata.retrofit.Test1Activity::class.java),
                Item("example7-livedata-transformations-retrofit-2", com.zj.example.arch.livedata.retrofit.Test2Activity::class.java),
                Item("example8-livedata-singlelivedata", com.zj.example.arch.livedata.singlelivedata.Test1Activity::class.java)
        ))
    }

    class Item(val name: String, val clazz: Class<*>)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val cartView: CardView = itemView.findViewById(R.id.cartView)

    }

    class Adapter(val items: List<Item>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_main, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.cartView.context
            holder.tvTitle.text = items[position].name
            holder.cartView.setOnClickListener {
                val clazz = items[holder.adapterPosition].clazz
                context.startActivity(Intent(context, clazz))
            }
        }

    }
}
