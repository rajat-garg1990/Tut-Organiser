package com.example.dbtest

import android.content.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment1.*

class Fragment1 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addButton.setOnClickListener {
            startActivity(Intent(this@Fragment1.context,AddStudent::class.java))
        }
        viewButton.setOnClickListener {
            startActivity(Intent(this@Fragment1.context,ViewStudentActivity::class.java))
        }
        removeButton.setOnClickListener {
            startActivity(Intent(this@Fragment1.context,ModifyStudentActivity::class.java))
        }
        updateButton.setOnClickListener {
            startActivity(Intent(this@Fragment1.context,ModifyStudentActivity::class.java))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1,container,false)
    }
}