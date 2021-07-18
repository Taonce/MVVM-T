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

    private val retrofitList: Map<String, Retrofit> = mutableMapOf()

    private val mOkHttpClient by lazy { OkHttpManager.mInstance.getHttpClient() }

    // 配置Retrofit
    private fun getRetrofit(baseUrl: String = BASE_URL): Retrofit {
        if (retrofitList.isEmpty()) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
        } else {
            mRetrofit = if (retrofitList.containsKey(baseUrl)) {
                retrofitList[baseUrl]
            } else {
                Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build()
            }
        }
        return mRetrofit!!
    }

    fun <T : ApiServices> getApi(servicesClass: Class<T>, baseUrl: String = BASE_URL): T =
        getRetrofit(baseUrl).create(servicesClass) as T
}