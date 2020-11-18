package com.example.dbtest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dbtest.retro.ApiService
import com.example.dbtest.retro.ConvertResponse
import kotlinx.android.synthetic.main.fragment_currency.*
import kotlinx.android.synthetic.main.fragment_length.buttonConvert
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var units = arrayListOf("INR", "USD", "EUR", "GBP", "AUD", "CAD")
        var unitSpin= view?.findViewById<Spinner>(R.id.unitSpin)
        unitSpin?.adapter = this.context?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                units
            )
        }
        unitSpin?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }
        refresh.setOnClickListener {
            field1.setText("")
            activity?.recreate()
        }
        val retro=Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api=retro.create(ApiService::class.java)
        buttonConvert.setOnClickListener {
            when (unitSpin?.selectedItem) {
                "INR"->{
                    api.fetchInr().enqueue(object : Callback<ConvertResponse> {
                        override fun onResponse(
                            call: Call<ConvertResponse>,
                            response: Response<ConvertResponse>
                        ) {
                            var result= response.body()?.conversion_rates?.let { it1 -> ConvertResponse(it1) }
                            field2.text=(field1.text.toString().toFloat()*result!!.conversion_rates.INR).toString()
                            field3.text=(field1.text.toString().toFloat()*result.conversion_rates.USD).toString()
                            field4.text=(field1.text.toString().toFloat()*result.conversion_rates.EUR).toString()
                            field5.text=(field1.text.toString().toFloat()*result.conversion_rates.GBP).toString()
                            field6.text=(field1.text.toString().toFloat()*result.conversion_rates.AUD).toString()
                            field7.text=(field1.text.toString().toFloat()*result.conversion_rates.CAD).toString()
                        }

                        override fun onFailure(call: Call<ConvertResponse>, t: Throwable) {
                            Log.e("msg", "Failure")
                        }
                    })
                }
                "USD"->{
                    api.fetchUsd().enqueue(object : Callback<ConvertResponse>{
                        override fun onResponse(
                            call: Call<ConvertResponse>,
                            response: Response<ConvertResponse>
                        ) {
                            var result= response.body()?.conversion_rates?.let { it1 -> ConvertResponse(it1) }
                            field2.text=(field1.text.toString().toFloat()*result!!.conversion_rates.INR).toString()
                            field3.text=(field1.text.toString().toFloat()*result.conversion_rates.USD).toString()
                            field4.text=(field1.text.toString().toFloat()*result.conversion_rates.EUR).toString()
                            field5.text=(field1.text.toString().toFloat()*result.conversion_rates.GBP).toString()
                            field6.text=(field1.text.toString().toFloat()*result.conversion_rates.AUD).toString()
                            field7.text=(field1.text.toString().toFloat()*result.conversion_rates.CAD).toString()
                        }

                        override fun onFailure(call: Call<ConvertResponse>, t: Throwable) {
                        }
                    })
                }
                "EUR"->{
                    api.fetchEur().enqueue(object : Callback<ConvertResponse>{
                        override fun onResponse(
                            call: Call<ConvertResponse>,
                            response: Response<ConvertResponse>
                        ) {
                            var result= response.body()?.conversion_rates?.let { it1 -> ConvertResponse(it1) }
                            field2.text=(field1.text.toString().toFloat()*result!!.conversion_rates.INR).toString()
                            field3.text=(field1.text.toString().toFloat()*result.conversion_rates.USD).toString()
                            field4.text=(field1.text.toString().toFloat()*result.conversion_rates.EUR).toString()
                            field5.text=(field1.text.toString().toFloat()*result.conversion_rates.GBP).toString()
                            field6.text=(field1.text.toString().toFloat()*result.conversion_rates.AUD).toString()
                            field7.text=(field1.text.toString().toFloat()*result.conversion_rates.CAD).toString()
                        }

                        override fun onFailure(call: Call<ConvertResponse>, t: Throwable) {
                        }
                    })
                }
                "GBP"->{
                    api.fetchGbp().enqueue(object : Callback<ConvertResponse>{
                        override fun onResponse(
                            call: Call<ConvertResponse>,
                            response: Response<ConvertResponse>
                        ) {
                            var result= response.body()?.conversion_rates?.let { it1 -> ConvertResponse(it1) }
                            field2.text=(field1.text.toString().toFloat()*result!!.conversion_rates.INR).toString()
                            field3.text=(field1.text.toString().toFloat()*result.conversion_rates.USD).toString()
                            field4.text=(field1.text.toString().toFloat()*result.conversion_rates.EUR).toString()
                            field5.text=(field1.text.toString().toFloat()*result.conversion_rates.GBP).toString()
                            field6.text=(field1.text.toString().toFloat()*result.conversion_rates.AUD).toString()
                            field7.text=(field1.text.toString().toFloat()*result.conversion_rates.CAD).toString()
                        }

                        override fun onFailure(call: Call<ConvertResponse>, t: Throwable) {
                        }
                    })
                }
                "AUD"->{
                    api.fetchAud().enqueue(object :Callback<ConvertResponse>{
                        override fun onResponse(
                            call: Call<ConvertResponse>,
                            response: Response<ConvertResponse>
                        ) {
                            var result= response.body()?.conversion_rates?.let { it1 -> ConvertResponse(it1) }
                            field2.text=(field1.text.toString().toFloat()*result!!.conversion_rates.INR).toString()
                            field3.text=(field1.text.toString().toFloat()*result.conversion_rates.USD).toString()
                            field4.text=(field1.text.toString().toFloat()*result.conversion_rates.EUR).toString()
                            field5.text=(field1.text.toString().toFloat()*result.conversion_rates.GBP).toString()
                            field6.text=(field1.text.toString().toFloat()*result.conversion_rates.AUD).toString()
                            field7.text=(field1.text.toString().toFloat()*result.conversion_rates.CAD).toString()
                        }

                        override fun onFailure(call: Call<ConvertResponse>, t: Throwable) {
                        }
                    })
                }
                "CAD"->{
                    api.fetchCad().enqueue(object : Callback<ConvertResponse>{
                        override fun onResponse(
                            call: Call<ConvertResponse>,
                            response: Response<ConvertResponse>
                        ) {
                            var result= response.body()?.conversion_rates?.let { it1 -> ConvertResponse(it1) }
                            field2.text=(field1.text.toString().toFloat()*result!!.conversion_rates.INR).toString()
                            field3.text=(field1.text.toString().toFloat()*result.conversion_rates.USD).toString()
                            field4.text=(field1.text.toString().toFloat()*result.conversion_rates.EUR).toString()
                            field5.text=(field1.text.toString().toFloat()*result.conversion_rates.GBP).toString()
                            field6.text=(field1.text.toString().toFloat()*result.conversion_rates.AUD).toString()
                            field7.text=(field1.text.toString().toFloat()*result.conversion_rates.CAD).toString()
                        }

                        override fun onFailure(call: Call<ConvertResponse>, t: Throwable) {
                        }
                    })
                }
            }
            Toast.makeText(this@CurrencyFragment.context,"Converted", Toast.LENGTH_LONG).show()
        }
    }
}
