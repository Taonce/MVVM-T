package com.taonce.mvvmt

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.taonce.mvvm.base.BaseDialogFragment
import com.taonce.mvvm.base.BaseListActivity
import com.taonce.mvvm.base.BaseRecyclerAdapter
import com.taonce.mvvm.base.OnItemClickListener
import com.taonce.mvvm.network.RetrofitManager
import com.taonce.mvvm.util.iosDialog
import com.taonce.mvvm.util.safeClick
import com.taonce.mvvm.util.safeLaunch
import com.taonce.mvvm.util.showDebug
import com.taonce.mvvmt.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseListActivity<MainAdapter>() {
    companion object {
        const val TAG = "MVVM_T_MainActivity"
    }

    private val mData = mutableListOf<String>()
    private var popupWindow: MainPopupWindow? = null
    private val mainViewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

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
            .load("https://bkimg.cdn.bcebos.com/pic/9a504fc2d5628535746e08f997ef76c6a6ef6358?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_auto")
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
            .load("https://bkimg.cdn.bcebos.com/pic/9a504fc2d5628535746e08f997ef76c6a6ef6358?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_auto")
            .centerCrop()
            .into(imageView)
        imageView.safeClick {
            showDebug(msg = "imageView click")
        }
    }
}
