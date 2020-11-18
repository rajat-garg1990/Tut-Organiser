package com.example.dbtest

import android.content.ContentUris
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment2.*
import java.util.*

class Fragment2:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment2,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        calEvent.setOnClickListener {
            val calendarIntent=Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
            startActivity(calendarIntent)
        }
        calSeeEvent.setOnClickListener {
            val build=CalendarContract.CONTENT_URI.buildUpon().appendPath("time")
            ContentUris.appendId(build,Calendar.getInstance().timeInMillis)
            var uri=build.build()
            val intent=Intent(Intent.ACTION_VIEW)
                .setData(uri)
            startActivity(intent)
        }
    }
}