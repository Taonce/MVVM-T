package com.taonce.mvvmt

import android.content.Context
import android.os.Bundle
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
        val recyclerView = mRootView.findViewById<RecyclerView>(R.id.rcv)
        recyclerView.adapter = mAdapter

        // retrofit + coroutine
        safeLaunch(Dispatchers.IO) { RetrofitManager.getApi }

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

class MainPopupWindow(context: Context) : BasePopupWindow(context) {
    override fun getLayoutId(): Int = R.layout.recycler_item_main

    override fun work() {
        val imageView = contentView.findViewById<ImageView>(R.id.iv)
        Glide.with(imageView.context)
            .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.16pic.com%2F00%2F07%2F85%2F16pic_785133_b.jpg&refer=http%3A%2F%2Fphoto.16pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1627029920&t=ce5901d26d8be9989deaca630878f1e2")
            .centerCrop()
            .into(imageView)
    }
}
