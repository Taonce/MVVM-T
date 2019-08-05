package com.taonce.mvvmt

import android.os.Bundle
import com.taonce.mvvm.base.BaseActivity
import com.taonce.mvvm.util.iosDialog
import com.taonce.mvvmt.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun work(savedInstanceState: Bundle?) {
        iosDialog("标题", "信息")
        mDataBinding.setItemClickListener { position, _ -> }
        mDataBinding.setItemLongClickListener { position, _ -> }
    }
}
