package com.taonce.mvvm.base;

import androidx.annotation.Nullable;

/**
 * Author: Taonce
 * Date: 2019/8/5
 * Project: MVVM-T
 */

public interface OnTextChanged {
    void changed(@Nullable CharSequence s, int start, int before, int count);
}
