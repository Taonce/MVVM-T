package com.taonce.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Taonce.
 * @description
 */
abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment(),
    CoroutineScope by MainScope() {
    protected val vb: VB by lazy { getViewBinding() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Dialog)
        return vb.root
    }

    abstract fun getViewBinding(): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        work(view, savedInstanceState)
    }

    abstract fun work(view: View, savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        // 避免协程的内存泄漏
        cancel()
    }
}