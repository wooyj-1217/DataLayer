package com.wooyj.datalayer.data.remote

class TokenAuthenticator()
// import android.content.Context
// import kotlinx.coroutines.runBlocking
// import okhttp3.Authenticator
// import okhttp3.Request
// import okhttp3.Response
// import okhttp3.Route
// import javax.net.ssl.HttpsURLConnection
//
// TODO("token authenticator 예전에 제가 짰던거 들고오긴 했는데.. 이거 맞는지는 잘 모르겠습니다.")
// class TokenAuthenticator constructor(
//    private val context: Context,
//    private val authJsPublicService: AuthJsPublicService
// ) : Authenticator {
//
//    override fun authenticate(route: Route?, response: Response): Request? {
//        val accessToken = App.prefManager.token.accessToken
//        val refreshToken = App.prefManager.token.refreshToken
//
//        return synchronized(this) {
//            if (!UserUtil.isLogin()) {
//                getGuestToken(response)
//            } else if (accessToken.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
//                getGuestToken(response)
//            } else {
//                if (response.code == HttpsURLConnection.HTTP_UNAUTHORIZED) {
//                    if (Utils.responseCount(response) >= 3) {
//                        // access token을 이용한 api call 시 실패횟수가 3회 이상일 때 (return null이 3번 이상.)
//                        getGuestToken(response)
//                    } else {
//                        getNewToken(response, refreshToken)
//                    }
//                } else {
//                    null
//                }
//            }
//        }
//    }
//
//    private fun getNewToken(response: Response, refreshToken: String?): Request? {
//        try {
//            val tokenResponse = runBlocking {
//                authJsPublicService.postTokenRefresh(ReqRefreshToken(refreshToken!!)).execute()
//            }
//
//            if (tokenResponse != null) {
//                when (tokenResponse.code()) {
//                    HttpsURLConnection.HTTP_OK -> {
//                        val newToken: ProtoRes = tokenResponse.body()!!
//                        newToken.data?.getDecodeData()?.let {
//                            val token = AuthJs.PostAuthTokenRes.parseFrom(it)
//                            App.prefManager.token.accessToken = token.accessToken
//                            App.prefManager.token.refreshToken = token.refreshToken
//                            return response.request.newBuilder()
//                                .header("Authorization", "Bearer" + " " + token.accessToken)
//                                .build()
//                        }
//                        return response.request.newBuilder().build()
//                    }
//                    HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_INTERNAL_ERROR -> {
//                        return response.request.newBuilder().build()
//                    }
//                    else -> {
//                        return null
//                    }
//                }
//            }
//            return null
//        } catch (e: Exception) {
//            return null
//        }
//    }
//
//    private fun getGuestToken(response: Response): Request? {
//        if (UserUtil.isLogin()) {
//            UserUtil.logout()
//        }
//        val call = runBlocking { authJsPublicService.postGuestToken(ReqGuestToken(Utils.getAndroidId(context))) }
//        try {
//            return if (!call.isExecuted) {
//                val tokenResponse = call.execute()
//                return if (tokenResponse.code() == HttpsURLConnection.HTTP_OK) {
//                    val newToken: ProtoRes = tokenResponse.body()!!
//                    newToken.data?.getDecodeData()?.let {
//                        val token = AuthJs.PostAuthTokenRes.parseFrom(it)
//                        App.prefManager.token.accessToken = token.accessToken
//                        App.prefManager.token.refreshToken = token.refreshToken
//                        response.request.newBuilder()
//                            .header("Authorization", "Bearer " + token.accessToken)
//                            .build()
//                    }
//                } else {
//                    null
//                }
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            return null
//        }
//    }
// }
