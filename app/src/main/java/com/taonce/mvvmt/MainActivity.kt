package com.taonce.mvvmt

import android.content.Context
import android.os.Bundle
import com.taonce.mvvm.base.BaseActivity
import com.taonce.mvvm.base.BasePopupWindow
import com.taonce.mvvm.base.BaseRecyclerAdapter
import com.taonce.mvvm.network.RetrofitManager
import com.taonce.mvvm.util.iosDialog
import com.taonce.mvvm.util.safeLaunch
import com.taonce.mvvm.util.showDebug
import com.taonce.mvvmt.databinding.ActivityMainBinding
import com.taonce.mvvmt.databinding.RecyclerItemMainBinding
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun work(savedInstanceState: Bundle?) {
        // dialog util
        iosDialog("标题", "信息")

        // rcv
        val mData = mutableListOf<String>()
        for (i in 1..50) {
            mData.add("$i")
        }
        mDataBinding.adapter = MainAdapter(mData)
        mDataBinding.setItemClickListener { _, _ -> MainPopupWindow(this) }
        mDataBinding.setItemLongClickListener { position, _ -> showDebug("long click item data is ${mData[position]}") }

        // network + coroutine
        safeLaunch(Dispatchers.IO) { RetrofitManager.getApi }

    }
}

class MainAdapter(mData: MutableList<String>) :
    BaseRecyclerAdapter<RecyclerItemMainBinding, String>(mData) {
    override fun getLayoutId(): Int = R.layout.recycler_item_main

    override fun setVariable(position: Int, dataBinding: RecyclerItemMainBinding) {
    }

    override fun update(newData: MutableList<String>) {
    }
}

class MainPopupWindow(context: Context) : BasePopupWindow<ActivityMainBinding>(context) {
    override fun getLayoutId(): Int = R.layout.recycler_item_main

    override fun work() {
    }
}
