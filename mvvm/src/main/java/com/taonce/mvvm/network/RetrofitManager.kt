package com.taonce.mvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Taonce.
 * @description
 */
object RetrofitManager {
    // 配置base_url
    private const val BASE_URL = ""

    private var mRetrofit: Retrofit? = null

    private val mOkHttpClient by lazy { OkHttpManager.mInstance.getHttpClient() }

    // 配置Retrofit
    private fun getRetrofit(baseUrl: String = BASE_URL): Retrofit {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
        }
        return mRetrofit!!
    }

    fun <T : ApiServices> getApi(servicesClass: Class<T>, baseUrl: String = BASE_URL): Class<T> =
        getRetrofit(baseUrl).create(servicesClass::class.java)
}