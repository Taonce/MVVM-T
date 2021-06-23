package com.taonce.mvvmt

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taonce.mvvm.base.*
import com.taonce.mvvm.network.RetrofitManager
import com.taonce.mvvm.util.iosDialog
import com.taonce.mvvm.util.safeLaunch
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseListActivity<MainAdapter>() {
    companion object {
        const val TAG = "MVVM_T_MainActivity"
    }

    private val mData = mutableListOf<String>()
    private var popupWindow: MainPopupWindow? = null


    override fun getAdapter(): MainAdapter {
//        for (i in 1..5) {
//            mData.add("$i")
//        }
        mData.add("1")
        return MainAdapter(mData)
    }

    override fun getItemClickListener() =
        OnItemClickListener { _, _ ->
            popupWindow = MainPopupWindow()
            popupWindow?.show(supportFragmentManager, "MainActivity")
        }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun work(savedInstanceState: Bundle?) {
        // dialog util
        iosDialog("标题", "信息")

        // rcv
        val recyclerView = mRootView.findViewById<RecyclerView>(R.id.rcv)
        recyclerView.adapter = mAdapter

        // retrofit + coroutine
        safeLaunch(Dispatchers.IO) { RetrofitManager.getApi }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }
}

class MainAdapter(mData: MutableList<String>) :
    BaseRecyclerAdapter<String>(mData) {
    override fun getLayoutId(): Int = R.layout.recycler_item_main

    override fun setVariable(position: Int, rootView: View) {
        val imageView = rootView.findViewById<ImageView>(R.id.iv)
        Glide.with(imageView.context)
            .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.16pic.com%2F00%2F07%2F85%2F16pic_785133_b.jpg&refer=http%3A%2F%2Fphoto.16pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1627029920&t=ce5901d26d8be9989deaca630878f1e2")
            .centerCrop()
            .into(imageView)
        val textView = rootView.findViewById<TextView>(R.id.tv_item)
        textView.text = position.toString()
    }

    override fun update(newData: MutableList<String>) {
    }
}

class MainPopupWindow : BaseDialogFragment() {
    override fun getLayoutId(): Int = R.layout.recycler_item_main

    override fun work(view: View, savedInstanceState: Bundle?) {
        val imageView = mRootView.findViewById<ImageView>(R.id.iv)
        Glide.with(imageView.context)
            .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.16pic.com%2F00%2F07%2F85%2F16pic_785133_b.jpg&refer=http%3A%2F%2Fphoto.16pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1627029920&t=ce5901d26d8be9989deaca630878f1e2")
            .centerCrop()
            .into(imageView)
    }
}
