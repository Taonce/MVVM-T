package com.taonce.mvvm.base

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.taonce.mvvm.util.showDebug
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [ActivityCompat] 基类，封装在 [MainScope] 中
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), CoroutineScope by MainScope() {
    companion object {
        private const val PERMISSION_REQUEST_CODE = 0x01
    }

    /**
     * ViewBinding
     */
    protected val mViewBinding: VB by lazy { getViewBinding() }

    /**
     * RootView
     */
    protected lateinit var mRootView: View

    // 申请权限时成功和失败的回调
    private var mPermissionRequestSuccess: (() -> Unit)? = null

    private var mPermissionRequestFailed: ((permissions: Array<String>) -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        showDebug(msg = "activity create start")
        super.onCreate(savedInstanceState)
        mRootView = mViewBinding.root
        setContentView(mRootView)
        work(savedInstanceState)
        showDebug(msg = "activity create end")
    }

    abstract fun getViewBinding(): VB

    abstract fun work(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        // 取消MainScope下的所有的协程
        cancel()
    }

    /**
     * 获取VM，只能获取不带参数的VM
     */
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T = ViewModelProviders.of(this).get(clazz)

    /**
     * 透明状态栏
     */
    fun transparentStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()
    }

    /**
     * 申请权限
     * [permissions] 权限列表
     * [success] 成功回调
     * [failed] 失败回调，并且把失败的权限返回
     */
    fun requestPermissions(
        permissions: Array<String>,
        success: () -> Unit,
        failed: (permissions: Array<String>) -> Unit
    ) {
        mPermissionRequestSuccess = success
        mPermissionRequestFailed = failed
        val shouldRequestPermissions = mutableListOf<String>()
        shouldRequestPermissions.addAll(
            permissions.filter {
                ContextCompat.checkSelfPermission(
                    this,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            }
        )
        if (shouldRequestPermissions.isEmpty()) (mPermissionRequestSuccess!!)() else ActivityCompat.requestPermissions(
            this,
            shouldRequestPermissions.toTypedArray(),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val deniedPermissions = mutableListOf<String>()
            if (grantResults.isNotEmpty()) {
                for (grant in grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        deniedPermissions.add(permissions[grant])
                    }
                }
                if (deniedPermissions.isEmpty()) (mPermissionRequestSuccess!!)() else (mPermissionRequestFailed!!)(
                    deniedPermissions.toTypedArray()
                )
            }
        }
    }
}
