package com.taonce.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [Fragment] 基类，封装在 [MainScope] 中
 */
abstract class BaseFragment : Fragment(), CoroutineScope by MainScope() {
    protected lateinit var mRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayoutId(), container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        work(view, savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    abstract fun work(view: View, savedInstanceState: Bundle?)

    /**
     * 获取VM，只能获取不带参数的VM
     */
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T = ViewModelProviders.of(this).get(clazz)

    /**
     * 申请权限，交由Activity处理
     */
    fun requestPermissions(
        permissions: Array<String>,
        success: () -> Unit,
        failed: (permissions: Array<String>) -> Unit
    ) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity).requestPermissions(permissions, success, failed)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}

