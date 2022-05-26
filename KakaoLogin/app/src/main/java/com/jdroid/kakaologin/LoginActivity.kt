package com.jdroid.kakaologin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jdroid.kakaologin.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    private val mCallback : (OAuthToken?, Throwable?) -> Unit = {token, error ->
        if (error != null) {
            Log.e("jdroid", "로그인 실패 $error")
        } else if (token != null) {
            Log.e("jdroid", "로그인 성공 ${token.accessToken}")
            getSharedPreferences("jdroid", MODE_PRIVATE).edit().putString("token", token.toString()).apply()
        }
        checkToken()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnLogin.id -> {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                    UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                        if (error != null) {
                            Log.e("jdroid", "로그인 실패 $error")
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            } else {
                                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
                            }
                        } else if (token != null) {
                            Log.e("jdroid", "로그인 성공 ${token.accessToken}")
                            getSharedPreferences("jdroid", MODE_PRIVATE).edit().putString("token", token.toString()).apply()
                        }
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
                }
                checkToken()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyHash = Utility.getKeyHash(this)
        getSharedPreferences("jdroid", MODE_PRIVATE).edit().putString("keyHash", keyHash).apply()
        Log.e("jdroid", "keyhash : $keyHash")

        setContentView(binding.root)
        KakaoSdk.init(this, "4c08703553fd48561ec8a0070a182ee6")

        if (getSharedPreferences("jdroid", MODE_PRIVATE).getString("token", null).isNullOrBlank()) {
            binding.btnLogin.setOnClickListener(this)
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun checkToken() {
        if (getSharedPreferences("jdroid", MODE_PRIVATE).getString("token", null).isNullOrBlank()) {

        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}