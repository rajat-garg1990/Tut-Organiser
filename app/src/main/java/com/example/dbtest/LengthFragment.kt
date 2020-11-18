package com.example.dbtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_length.*

class LengthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_length, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var m = 0.0F
        var inch = 0.0F
        var feet = 0.0F
        var yard = 0.0F
        var mile = 0.0F
        var km = 0.0F
        var cm = 0.0F
        var units = arrayListOf("m", "inch", "feet", "yard", "mile", "km", "cm")
        unitSpin.adapter = this@LengthFragment.context?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                units
            )
        }
        unitSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }
        refresh.setOnClickListener {
            field1.setText("")
            activity?.recreate()
        }
        buttonConvert.setOnClickListener {
            when (unitSpin.selectedItem) {
                "m" -> {
                    m = (field1.text.toString()).toFloat()
                    inch = ((field1.text.toString().toFloat()) * 39.370079).toFloat()
                    feet = ((field1.text.toString().toFloat()) * 3.28084).toFloat()
                    yard = ((field1.text.toString().toFloat()) * 1.093613).toFloat()
                    mile = ((field1.text.toString().toFloat()) * 0.000621).toFloat()
                    km = ((field1.text.toString().toFloat()) * 0.001).toFloat()
                    cm = ((field1.text.toString().toFloat()) * 100).toFloat()
                }
                "cm" -> {
                    m = ((field1.text.toString()).toFloat() * 0.01).toFloat()
                    inch = ((field1.text.toString().toFloat()) * 0.393701).toFloat()
                    feet = ((field1.text.toString().toFloat()) * 0.032808).toFloat()
                    yard = ((field1.text.toString().toFloat()) * 0.010936).toFloat()
                    mile = ((field1.text.toString().toFloat()) * 0.000006).toFloat()
                    km = ((field1.text.toString().toFloat()) * 0.00001).toFloat()
                    cm = (field1.text.toString().toFloat())
                }
                "inch" -> {
                    m = ((field1.text.toString()).toFloat() * 0.0254).toFloat()
                    inch = ((field1.text.toString().toFloat()))
                    feet = ((field1.text.toString().toFloat()) * 0.08333).toFloat()
                    yard = ((field1.text.toString().toFloat()) * 0.027778).toFloat()
                    mile = ((field1.text.toString().toFloat()) * 0.000016).toFloat()
                    km = ((field1.text.toString().toFloat()) * 0.000025).toFloat()
                    cm = ((field1.text.toString().toFloat()) * 2.54).toFloat()
                }
                "feet" -> {
                    m = ((field1.text.toString()).toFloat() * 0.3048).toFloat()
                    inch = ((field1.text.toString().toFloat()) * 12.0).toFloat()
                    feet = ((field1.text.toString().toFloat()))
                    yard = ((field1.text.toString().toFloat()) * 0.33333).toFloat()
                    mile = ((field1.text.toString().toFloat()) * 0.000189).toFloat()
                    km = ((field1.text.toString().toFloat()) * 0.000305).toFloat()
                    cm = ((field1.text.toString().toFloat()) * 30.48).toFloat()
                }
                "yard" -> {
                    m = ((field1.text.toString()).toFloat() * 0.9144).toFloat()
                    inch = ((field1.text.toString().toFloat()) * 36.0).toFloat()
                    feet = ((field1.text.toString().toFloat()) * 3.0).toFloat()
                    yard = ((field1.text.toString().toFloat()))
                    mile = ((field1.text.toString().toFloat()) * 0.000568).toFloat()
                    km = ((field1.text.toString().toFloat()) * 0.000914).toFloat()
                    cm = ((field1.text.toString().toFloat()) * 91.44).toFloat()
                }
                "mile" -> {
                    m = ((field1.text.toString()).toFloat() * 1609.344).toFloat()
                    inch = ((field1.text.toString().toFloat()) * 63360.0).toFloat()
                    feet = ((field1.text.toString().toFloat()) * 5280.0).toFloat()
                    yard = ((field1.text.toString().toFloat()) * 1760.0).toFloat()
                    mile = ((field1.text.toString().toFloat()))
                    km = ((field1.text.toString().toFloat()) * 1.609344).toFloat()
                    cm = ((field1.text.toString().toFloat()) * 160934.4).toFloat()
                }
                "km" -> {
                    m = ((field1.text.toString()).toFloat() * 1000.0).toFloat()
                    inch = ((field1.text.toString().toFloat()) * 39370.0787).toFloat()
                    feet = ((field1.text.toString().toFloat()) * 3280.8399).toFloat()
                    yard = ((field1.text.toString().toFloat()) * 1093.613).toFloat()
                    mile = ((field1.text.toString().toFloat()) * 0.621).toFloat()
                    km = ((field1.text.toString().toFloat()))
                    cm = ((field1.text.toString().toFloat()) * 100000.0).toFloat()
                }
            }
            field1.setText(field1.text.toString())
            field2.text = inch.toString()
            field3.text = feet.toString()
            field4.text = yard.toString()
            field5.text = mile.toString()
            field6.text = km.toString()
            field7.text = cm.toString()
            field8.text = m.toString()
            Toast.makeText(this@LengthFragment.context, "Converted", Toast.LENGTH_LONG).show()
        }
    }
}