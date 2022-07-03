package com.jdroid.recyclerviewaddremove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jdroid.recyclerviewaddremove.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    // RecyclerView에 사용될 데이터 ArrayList
    private val arrayData = ArrayList<String>()
    // RecyclerView의 Adapter
    private var recyclerAdapter: RecyclerViewAdapter? = null

    override fun onClick(v: View?) {
        when(v?.id) {
            // ADD버튼을 눌렀을 때 동작
            binding.btnAdd.id -> {
                if (binding.editText.text.isNullOrEmpty()) {
                    // EditText가 비어있을 경우 Toast메시지 띄움
                    Toast.makeText(this, "데이터 입력한 후 추가", Toast.LENGTH_SHORT).show()
                } else {
                    // EditText가 비어있지 않을 경우 ArrayList에 데이터를 추가하고
                    // adapter에 notifyItemInserted
                    arrayData.add(binding.editText.text.toString())
                    recyclerAdapter?.notifyItemInserted(arrayData.size - 1)
                }
            }
            // REMOVE버튼을 눌렀을 때 동작
            binding.btnRemove.id -> {
                // EditText의 내용이 ArrayList에 포함되어있는지와 Index체크
                val removePosition = arrayData.indexOfFirst { it == binding.editText.text.toString() }
                if (removePosition != -1) {
                    // 포함되어 있을 경우 ArrayList에서 해당 내용 삭제 후
                    // adapter에 notifyRemoved
                    arrayData.removeAt(removePosition)
                    recyclerAdapter?.notifyItemRemoved(removePosition)
                } else {
                    // 포함되어 있지 않을 경우 Toast 메시지
                    Toast.makeText(this, "일치하는 데이터 없음", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // recyclerAdapter 생성 후 recyclerView의 adapter 지정
        recyclerAdapter = RecyclerViewAdapter(arrayData)
        binding.recyclerView.adapter = recyclerAdapter

        // Button들의 ClickListener 생성
        binding.btnAdd.setOnClickListener(this)
        binding.btnRemove.setOnClickListener(this)
    }
}