package com.example.dbtest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_feedback.*

class Feedback : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val dev= arrayOf("rajat_garg1990@yahoo.co.in")
        sendFeedbackBtn.setOnClickListener {
            val feedbackIntent = Intent(Intent.ACTION_SEND)
            //feedbackIntent.data = Uri.parse("mailto:")
            feedbackIntent.type = "message/rfc822"
            feedbackIntent.putExtra(Intent.EXTRA_EMAIL, dev)
            feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, subjectText.text.toString())
            feedbackIntent.putExtra(Intent.EXTRA_TEXT, messageText.text.toString())
            startActivity(Intent.createChooser(feedbackIntent, "Sending Mail.."))
        }
    }
}