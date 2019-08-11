package com.taonce.mvvmt

import android.content.Context
import android.os.Bundle
import com.taonce.mvvm.base.*
import com.taonce.mvvm.network.RetrofitManager
import com.taonce.mvvm.util.iosDialog
import com.taonce.mvvm.util.safeLaunch
import com.taonce.mvvm.util.showDebug
import com.taonce.mvvmt.databinding.ActivityMainBinding
import com.taonce.mvvmt.databinding.RecyclerItemMainBinding
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseListActivity<ActivityMainBinding, MainAdapter>() {

    private val mData = mutableListOf<String>()
    override fun getAdapter(): MainAdapter {
        for (i in 1..5) {
            mData.add("$i")
        }
        return MainAdapter(mData)
    }

    override fun getItemClickListener() =
        OnItemClickListener { _, _ -> MainPopupWindow(this) }

    override fun getItemLongClickListener(): OnItemLongClickListener? = null

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun work(savedInstanceState: Bundle?) {
        // dialog util
        iosDialog("标题", "信息")

        // rcv
        mDataBinding.adapter = mAdapter
        mDataBinding.setLoadMore {
            mAdapter.add(mutableListOf("a", "a", "a", "a"))
        }

        // retrofit + coroutine
        safeLaunch(Dispatchers.IO) { RetrofitManager.getApi }

    }
}

class MainAdapter(mData: MutableList<String>) :
    BaseRecyclerAdapter<RecyclerItemMainBinding, String>(mData) {
    override fun getLayoutId(): Int = R.layout.recycler_item_main

    override fun setVariable(position: Int, dataBinding: RecyclerItemMainBinding) {
        dataBinding.url = "https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg"
    }

    override fun update(newData: MutableList<String>) {
    }
}

class MainPopupWindow(context: Context) : BasePopupWindow<RecyclerItemMainBinding>(context) {
    override fun getLayoutId(): Int = R.layout.recycler_item_main

    override fun work() {
        mDataBinding.url = "https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg"
    }
}
