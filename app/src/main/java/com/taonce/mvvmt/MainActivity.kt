package com.taonce.mvvmt

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.taonce.mvvm.base.BaseDialogFragment
import com.taonce.mvvm.base.BaseListActivity
import com.taonce.mvvm.base.BaseRecyclerAdapter
import com.taonce.mvvm.base.OnItemClickListener
import com.taonce.mvvm.util.*
import com.taonce.mvvmt.databinding.ActivityMainBinding
import com.taonce.mvvmt.databinding.RecyclerItemMainBinding
import com.taonce.mvvmt.databinding.RecyclerItemMainBinding.inflate

class MainActivity : BaseListActivity<ActivityMainBinding, MainAdapter>() {
    companion object {
        const val TAG = COMMON_TAG + "MainActivity"

        const val IMAGE_URL =
            "https://bkimg.cdn.bcebos.com/pic/9a504fc2d5628535746e08f997ef76c6a6ef6358?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_auto"
    }

    private val mData = mutableListOf<String>()

    override fun getAdapter(): MainAdapter {
        mData.add("1")
        mData.add("1")
        return MainAdapter(mData)
    }

    override fun getItemClickListener() =
        OnItemClickListener { _, _ ->
            mRootView.showCustomSnackBar(
                "show custom snack bar",
                "dismiss",
                Color.WHITE, Color.BLACK
            ) { showDebug("clickSnackBar", tag = TAG) }
        }

    override fun preCreate() {
        isShowToolBar(false)
        showOrHideStatusBar(false)
    }

    override fun getViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)

    override fun work(savedInstanceState: Bundle?) {
        // rcv
        mViewBinding.rcv.adapter = mAdapter
    }
}

class MainAdapter(mData: MutableList<String>) :
    BaseRecyclerAdapter<RecyclerItemMainBinding, String>(mData) {

    override fun getViewBinding(parent: ViewGroup) =
        inflate(LayoutInflater.from(parent.context))

    override fun setVariable(position: Int, viewBinding: RecyclerItemMainBinding) {
        Glide.with(viewBinding.iv.context).load(MainActivity.IMAGE_URL)
            .centerCrop()
            .into(viewBinding.iv)
        viewBinding.tvItem.text = position.toString()
    }

    override fun update(newData: MutableList<String>) {
    }
}

class MainTestDialog : BaseDialogFragment<RecyclerItemMainBinding>() {
    override fun getViewBinding() = inflate(LayoutInflater.from(context))

    override fun work(view: View, savedInstanceState: Bundle?) {
        Glide.with(mViewBinding.iv.context).load(MainActivity.IMAGE_URL)
            .centerCrop()
            .into(mViewBinding.iv)
        mViewBinding.iv.safeClick {
            showDebug(msg = "imageView click")
            showInfo(msg = "imageView click")
            showWarning(msg = "imageView click")
            showError(msg = "imageView click")
            showThrowable(msg = "imageView click")
        }
    }
}
