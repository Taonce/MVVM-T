package com.taonce.mvvm.base;

import androidx.annotation.Nullable;

/**
 * Author: Taonce
 * Date: 2019/8/5
 * Project: MVVM-T
 * Desc:
 */

public interface BeforeTextChanged {
    void before(@Nullable CharSequence s, int start, int count, int after);
}
