package com.jdroid.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jdroid.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnConstraint.id -> {
                setView(R.layout.view_constraintlayout)
            }

            binding.btnDrawer.id -> {

            }

            binding.btnFrame.id -> {

            }

            binding.btnGrid.id -> {

            }

            binding.btnLinearHorizon.id -> {
                setView(R.layout.view_linearlayout_horizontal)
            }

            binding.btnLinearVertical.id -> {
                setView(R.layout.view_linearlayout_vertical)
            }

            binding.btnRelative.id -> {

            }

            binding.btnTable.id -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener() {
        binding.btnConstraint.setOnClickListener(this)
        binding.btnDrawer.setOnClickListener(this)
        binding.btnFrame.setOnClickListener(this)
        binding.btnGrid.setOnClickListener(this)
        binding.btnLinearHorizon.setOnClickListener(this)
        binding.btnLinearVertical.setOnClickListener(this)
        binding.btnRelative.setOnClickListener(this)
        binding.btnTable.setOnClickListener(this)
    }

    private fun setView(layoutId: Int) {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(layoutId, binding.layout, true)
    }
}