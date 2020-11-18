package com.example.dbtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_update_student.*
import kotlinx.android.synthetic.main.fragment1.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        var signInClient = GoogleSignIn.getClient(this, gso)
        signInButton.setOnClickListener {
            var signInIntent = signInClient.signInIntent
            startActivityForResult(signInIntent, 11)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 11) {
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                var account = task.result
                updateUI(account)
            } catch (e: ApiException) {
                Toast.makeText(
                    this,
                    "Unable to Login!! Please try again in few seconds.",
                    Toast.LENGTH_LONG
                ).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else return
    }
}