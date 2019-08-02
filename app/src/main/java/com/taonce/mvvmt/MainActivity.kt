package com.taonce.mvvmt

import android.Manifest
import android.os.Bundle
import com.taonce.mvvm.base.BaseActivity
import com.taonce.mvvmt.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun work(savedInstanceState: Bundle?) {
        requestPermissions(arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ), {}, {})
    }
}
