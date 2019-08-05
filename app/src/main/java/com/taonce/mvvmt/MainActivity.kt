package com.taonce.mvvmt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taonce.mvvm.util.iosDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iosDialog("标题", "信息")
    }
}
