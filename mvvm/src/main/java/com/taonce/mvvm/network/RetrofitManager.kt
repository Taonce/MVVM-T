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
        // 配置base_url
        const val BASE_URL = ""
    }

    private var mRetrofit: Retrofit? = null
    private val mOkHttpClient by lazy { OkHttpManager.mInstance.getHttpClient() }

    // 配置Retrofit
    private fun getRetrofit(): Retrofit {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
        }
        return mRetrofit!!
    }

}