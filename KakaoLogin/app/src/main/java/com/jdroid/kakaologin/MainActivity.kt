package com.jdroid.kakaologin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jdroid.kakaologin.databinding.ActivityMainBinding
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(binding.root)

        KakaoSdk.init(this, "4c08703553fd48561ec8a0070a182ee6")
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("jdroid", "사용자 정보 요청 실패 $error")
            } else if (user != null) {
                Log.e("jdroid", "사용자 정보 요청 성공 : $user")
                binding.txtNickName.text = user.kakaoAccount?.profile?.nickname
                binding.txtAge.text = user.kakaoAccount?.ageRange.toString()
                binding.txtEmail.text = user.kakaoAccount?.email
            }
        }


    }
}