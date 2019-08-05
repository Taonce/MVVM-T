package com.taonce.mvvmt

import android.os.Bundle
import com.taonce.mvvm.base.BaseActivity
import com.taonce.mvvm.network.RetrofitManager
import com.taonce.mvvm.util.iosDialog
import com.taonce.mvvm.util.showDebug
import com.taonce.mvvmt.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun work(savedInstanceState: Bundle?) {
        iosDialog("标题", "信息")
        mDataBinding.setItemClickListener { position, _ -> }
        mDataBinding.setItemLongClickListener { position, _ -> }

        GlobalScope.launch(Dispatchers.IO) {
            val result = RetrofitManager.getApi.getCategoryData("Android", 10, 1)
            showDebug(result)
        }
    }
}
