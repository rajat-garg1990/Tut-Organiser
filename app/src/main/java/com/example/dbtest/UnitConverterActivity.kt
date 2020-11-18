package com.example.dbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_unit_converter.*

class UnitConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_converter)

        unitBar.setNavigationOnClickListener { finish() }
        addChildFragment(WeightFragment(), R.id.fragConverter)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.weight -> {
                    addChildFragment(WeightFragment(), R.id.fragConverter)
                }
                R.id.length -> {
                    addChildFragment(LengthFragment(), R.id.fragConverter)
                }
                R.id.currency -> {
                    addChildFragment(CurrencyFragment(), R.id.fragConverter)
                }
            }
            true
        }
    }

    private fun addChildFragment(fragment: Fragment, frameId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(frameId, fragment).commit()
    }
}