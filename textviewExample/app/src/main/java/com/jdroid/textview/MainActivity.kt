package com.jdroid.textview

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.jdroid.textview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TextWatcher {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        binding.txtAuto.text = binding.editText.text
        binding.txtEllip.text = binding.editText.text
        binding.txtXmlAuto.text = binding.editText.text
        binding.txtXmlEllip.text = binding.editText.text
    }
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.txtAuto.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20F)
            maxLines = 1
            setTextColor(getColor(R.color.red))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setAutoSizeTextTypeUniformWithConfiguration(4, 20, 1, TypedValue.COMPLEX_UNIT_DIP)
            }
        }

        binding.txtEllip.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20F)
            maxLines = 1
            setTextColor(getColor(R.color.red))
            ellipsize = TextUtils.TruncateAt.END
        }

        binding.editText.addTextChangedListener(this)
    }
}