package com.taonce.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author: Taonce
 * Date: 2019/8/2
 * Project: MVVM-T
 * Desc: [Fragment] 基类，封装在 [MainScope] 中
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment(), CoroutineScope by MainScope() {
    /**
     * ViewBinding
     */
    protected val viewBinding: VB by lazy { getVB() }

    protected var rootView: View? = null

    /**
     * 判断是否初始化View
     */
    private var isViewInit = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = viewBinding.root
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!isViewInit) {
            super.onViewCreated(view, savedInstanceState)
            work(view, savedInstanceState)
            isViewInit = true
        }
    }

    abstract fun getVB(): VB

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
        if (activity != null && activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).requestPermissions(permissions, success, failed)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}

