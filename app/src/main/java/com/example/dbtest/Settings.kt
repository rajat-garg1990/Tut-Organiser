package com.example.dbtest

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        topBar.setNavigationOnClickListener {
            finish()
        }
        var sp=getSharedPreferences("nightModePrefs", Context.MODE_PRIVATE)
        var darkSwitch=findViewById<SwitchMaterial>(R.id.darkSwitch)
        when(sp.getBoolean("nightModePrefs", false)){
            true -> {
                darkSwitch.isChecked = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false -> {
                darkSwitch.isChecked = false
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        darkSwitch.setOnCheckedChangeListener{ _, isChecked ->
            when (isChecked) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    saveModeState(true)
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    saveModeState(false)
                }
            }
        }
    }

    fun saveModeState(b: Boolean) {
        var sp=getSharedPreferences("nightModePrefs", Context.MODE_PRIVATE)
        var edit=sp.edit()
        edit.putBoolean("nightModePrefs", b)
        edit.apply()
    }
}