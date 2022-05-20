package com.jdroid.recyclerviewaddremove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jdroid.recyclerviewaddremove.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val arrayData = ArrayList<String>()
    private var recyclerAdapter: RecyclerViewAdapter? = null
    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnAdd.id -> {
                if (binding.editText.text.isNullOrEmpty()) {
                    Toast.makeText(this, "데이터 입력", Toast.LENGTH_SHORT).show()
                } else {
                    arrayData.add(binding.editText.text.toString())
                    recyclerAdapter?.notifyItemInserted(arrayData.size - 1)
                }
            }
            binding.btnRemove.id -> {
                val removePosition = arrayData.indexOfFirst { it == binding.editText.text.toString() }
                if (removePosition != -1) {
                    arrayData.removeAt(removePosition)
                    recyclerAdapter?.notifyItemRemoved(removePosition)
                } else {
                    Toast.makeText(this, "지울 데이터 없음", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerAdapter = RecyclerViewAdapter(arrayData)
        binding.recyclerView.adapter = recyclerAdapter

        binding.btnAdd.setOnClickListener(this)
        binding.btnRemove.setOnClickListener(this)
    }
}