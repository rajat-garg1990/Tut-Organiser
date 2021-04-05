package com.example.dbtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.dbtest.adapters.ViewPagerAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var drawer: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainBar)
        //tabs
        var tabMenu = findViewById<TabLayout>(R.id.tabMenu)
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Fragment1(), "Dashboard")
        adapter.addFragment(Fragment2(), "Schedule")
        adapter.addFragment(Fragment3(), "Tools")
        viewPager.adapter = adapter
        tabMenu.setupWithViewPager(viewPager)

        //Drawer
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        var signInClient = GoogleSignIn.getClient(this, gso)
        var account = GoogleSignIn.getLastSignedInAccount(this)
        var username = account?.displayName
        var email = account?.email
        var photo = account?.photoUrl
        var userName = navigationView.getHeaderView(0).findViewById<TextView>(R.id.username)
        var userEmail = navigationView.getHeaderView(0).findViewById<TextView>(R.id.userEmail)
        var userImage = navigationView.getHeaderView(0).findViewById<ImageView>(R.id.imageUser)
        userName.text = username
        userEmail.text = email
        Picasso.get().load(photo).into(userImage)
        drawer = findViewById(R.id.drawer_layout)
        toggle = object : ActionBarDrawerToggle(
            this,
            drawer,
            mainBar,
            R.string.open_drawer,
            R.string.close_drawer
        ) {}
        toggle.syncState()
        toggle.isDrawerIndicatorEnabled = true
        drawer.addDrawerListener(toggle)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.signOut -> {
                    signInClient.signOut()
                    finish()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
            true
        }
        //drawer ends
    }

    private fun checkTheme() {
        var sp = getSharedPreferences("nightModePrefs", Context.MODE_PRIVATE)
        when (sp.getBoolean("nightModePrefs", false)) {
            true ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            false ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> startActivity(Intent(this, Settings::class.java))
            R.id.feedback -> startActivity(Intent(this,Feedback::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this).setTitle("Are you sure you want to quit?")
            .setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
            }.setNegativeButton("No") { _, _ -> }
            .show()
    }
    override fun onStart() {
        super.onStart()
        checkTheme()
        var user = GoogleSignIn.getLastSignedInAccount(this)
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}