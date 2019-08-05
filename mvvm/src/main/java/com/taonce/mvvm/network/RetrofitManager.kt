package com.taonce.mvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Taonce.
 * @description
 */
class RetrofitManager {
    companion object {
        val getApi: ApiServices by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManager().getRetrofit().create(ApiServices::class.java)
        }
    }

    private lateinit var mRetrofit: Retrofit
    private val mOkHttpClient by lazy { OkHttpManager.mInstance.getHttpClient() }

    // 配置Retrofit
    private fun getRetrofit(): Retrofit {
        mRetrofit = Retrofit.Builder()
            .baseUrl("https://gank.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        return mRetrofit
    }

}