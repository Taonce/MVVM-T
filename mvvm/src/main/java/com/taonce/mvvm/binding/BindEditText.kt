package com.taonce.mvvm.binding

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.taonce.mvvm.base.AfterTextChanged
import com.taonce.mvvm.base.BeforeTextChanged
import com.taonce.mvvm.base.OnTextChanged


/**
 * Author: Taonce
 * Date: 2019/8/5
 * Project: MVVM-T
 * Desc: [EditText]的文本变化监听，这样的话不必每次重写三个方法，用哪个就重写哪个
 */
@BindingAdapter("bind:listener")
fun BindTextChanged(
    editText: EditText,
    after: AfterTextChanged? = null,
    before: BeforeTextChanged? = null,
    changed: OnTextChanged? = null
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            after?.apply { after(s) }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            before?.apply { before(s, start, count, after) }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            changed?.apply { changed(s, start, before, count) }
        }
    })
}
