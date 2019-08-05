package com.taonce.mvvm.base;

import android.text.Editable;
import androidx.annotation.Nullable;

/**
 * Author: Taonce
 * Date: 2019/8/5
 * Project: MVVM-T
 * Desc:
 */

public interface AfterTextChanged {
    void after(@Nullable Editable editable);
}
