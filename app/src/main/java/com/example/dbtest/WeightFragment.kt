package com.example.dbtest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_weight.*

class WeightFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weight, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            refresh.setOnClickListener{
            field1.setText("")
            activity?.recreate()
        }
        var units= arrayListOf("kg","lbs","oz","tonne","mg","g")
        unitSpin.adapter= this@WeightFragment.context?.let { ArrayAdapter<String>(it,android.R.layout.simple_spinner_dropdown_item,units) }
        unitSpin.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }
        buttonConvert.setOnClickListener {
            var pound= 0.0F
            var oz=0.0F
            var tonne=0.0F
            var mg=0L
            var g=0L
            var kg=0.0F
            when(unitSpin.selectedItem){
                "kg"-> {
                    pound = ((field1.text.toString().toFloat()) * 2.204623).toFloat()
                    oz = ((field1.text.toString().toFloat()) * 35.273962).toFloat()
                    tonne = ((field1.text.toString().toFloat()) * 0.001).toFloat()
                    mg = ((field1.text.toString().toFloat()) * 1000000).toLong()
                    g = ((field1.text.toString().toFloat()) * 1000).toLong()
                    kg=(field1.text.toString().toFloat())
                    Log.e("msg", "Success")
                }
                "lbs"->{
                    pound=(field1.text.toString().toFloat())
                    oz=((field1.text.toString().toFloat())*16).toFloat()
                    tonne=((field1.text.toString().toFloat())*0.000454).toFloat()
                    mg=((field1.text.toString().toFloat())*453592.37).toLong()
                    g=((field1.text.toString().toFloat())*453.59237).toLong()
                    kg=((field1.text.toString().toFloat())*0.4535923).toFloat()
                }
                "oz"->{
                    pound=((field1.text.toString().toFloat())*0.0625).toFloat()
                    oz=(field1.text.toString().toFloat())
                    tonne=((field1.text.toString().toFloat())*0.0000283).toFloat()
                    mg=((field1.text.toString().toFloat())*28349.523).toLong()
                    g=((field1.text.toString().toFloat())*28.349523).toLong()
                    kg=((field1.text.toString().toFloat())*0.0283495).toFloat()
                }
                "tonne"->{
                    pound=((field1.text.toString().toFloat())*2204.623).toFloat()
                    oz=((field1.text.toString().toFloat())*35273.96).toFloat()
                    tonne=(field1.text.toString().toFloat())
                    mg=((field1.text.toString().toFloat())*1000000000).toLong()
                    g=((field1.text.toString().toFloat())*1000000).toLong()
                    kg=((field1.text.toString().toFloat())*1000).toFloat()
                }
                "mg"->{
                    pound=((field1.text.toString().toFloat())*0.000002).toFloat()
                    oz=((field1.text.toString().toFloat())*0.000035).toFloat()
                    tonne=((field1.text.toString().toFloat())*1E-9).toFloat()
                    mg=(field1.text.toString().toLong())
                    g=((field1.text.toString().toFloat())*0.001).toLong()
                    kg=((field1.text.toString().toFloat())*0.000001).toFloat()
                }
                "g"->{
                    pound=((field1.text.toString().toFloat())*0.002205).toFloat()
                    oz=((field1.text.toString().toFloat())*0.035274).toFloat()
                    tonne=((field1.text.toString().toFloat())*0.000001).toFloat()
                    mg= ((field1.text.toString().toFloat())*1000).toLong()
                    g=(field1.text.toString().toLong())
                    kg=((field1.text.toString().toFloat())*0.001).toFloat()
                }
            }
            Log.e("msg", "Print")
            field1.setText(field1.text.toString())
            field2.text = pound.toString()
            field3.text = oz.toString()
            field4.text = tonne.toString()
            field5.text = mg.toString()
            field6.text = g.toString()
            field7.text = kg.toString()
            Toast.makeText(this@WeightFragment.context,"Converted", Toast.LENGTH_LONG).show()
        }
    }
}