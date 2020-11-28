package com.example.dbtest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment3.*

class Fragment3:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment3,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        unitConverter.setOnClickListener {
            startActivity(Intent(this@Fragment3.context, UnitConverterActivity::class.java))
        }
        notes.setOnClickListener {
            startActivity(Intent(this@Fragment3.context,NotesActivity::class.java))
        }
    }
}