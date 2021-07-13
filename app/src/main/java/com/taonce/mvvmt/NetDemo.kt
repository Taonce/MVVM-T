package com.taonce.mvvmt

import com.taonce.mvvm.network.ApiServices
import com.taonce.mvvm.network.RetrofitManager
import retrofit2.http.GET

/**
 * @author tao.
 * @description Retrofit案例demo，包括调用、接口、数据类
 * @date 2021/7/13
 */
class NetDemo {

    suspend fun getTaonce() {
        RetrofitManager.getApi(Api::class.java, baseUrl = "https://api.github.com/users/")
            .getTaonce()
    }

    interface Api : ApiServices {
        @GET("Taonce")
        suspend fun getTaonce(): GithubPersonBean
    }

    data class GithubPersonBean(
        val avatar_url: String,
        val bio: String,
        val blog: String,
        val company: Any,
        val created_at: String,
        val email: Any,
        val events_url: String,
        val followers: Int,
        val followers_url: String,
        val following: Int,
        val following_url: String,
        val gists_url: String,
        val gravatar_id: String,
        val hireable: Any,
        val html_url: String,
        val id: Int,
        val location: String,
        val login: String,
        val name: String,
        val node_id: String,
        val organizations_url: String,
        val public_gists: Int,
        val public_repos: Int,
        val received_events_url: String,
        val repos_url: String,
        val site_admin: Boolean,
        val starred_url: String,
        val subscriptions_url: String,
        val twitter_username: Any,
        val type: String,
        val updated_at: String,
        val url: String
    )
}