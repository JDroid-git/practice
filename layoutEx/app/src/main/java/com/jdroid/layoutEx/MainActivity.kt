package com.jdroid.layoutEx

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jdroid.layoutEx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var inflater: LayoutInflater? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnConstraint.id -> {
                setView(R.layout.view_constraintlayout)
            }

            binding.btnFrame.id -> {
                setView(R.layout.view_framelayout)
            }

            binding.btnGridHorizontal.id -> {
                setView(R.layout.view_gridlayout_horizontal)
            }

            binding.btnGridVertical.id -> {
                setView(R.layout.view_gridlayout_vertical)
            }

            binding.btnLinearHorizon.id -> {
                setView(R.layout.view_linearlayout_horizontal)
            }

            binding.btnLinearVertical.id -> {
                setView(R.layout.view_linearlayout_vertical)
            }

            binding.btnRelative.id -> {
                setView(R.layout.view_relativelayout)
            }

            binding.btnTable.id -> {
                setView(R.layout.view_tablelayout)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        initListener()
    }

    private fun initListener() {
        binding.btnConstraint.setOnClickListener(this)
        binding.btnFrame.setOnClickListener(this)
        binding.btnGridVertical.setOnClickListener(this)
        binding.btnGridHorizontal.setOnClickListener(this)
        binding.btnLinearHorizon.setOnClickListener(this)
        binding.btnLinearVertical.setOnClickListener(this)
        binding.btnRelative.setOnClickListener(this)
        binding.btnTable.setOnClickListener(this)
    }

    private fun setView(layoutId: Int) {
        binding.layout.removeAllViews()
        inflater?.inflate(layoutId, binding.layout, true)
    }
}