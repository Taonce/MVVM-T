package com.taonce.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Taonce.
 * @description
 */
abstract class BaseDialogFragment<VDB : ViewDataBinding> :
    DialogFragment(), CoroutineScope by MainScope() {

    protected lateinit var mDataBinding: VDB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Dialog)
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mDataBinding.root
    }

    abstract fun getLayoutId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDataBinding.lifecycleOwner = this
        work(view, savedInstanceState)
    }

    abstract fun work(view: View, savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        // 避免协程的内存泄漏
        cancel()
    }
}