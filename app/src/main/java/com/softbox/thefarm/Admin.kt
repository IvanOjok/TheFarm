package com.softbox.thefarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class Admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)


        val c = findViewById<CardView>(R.id.cardView)
        c.setOnClickListener {
            startActivity(Intent(this, DoctorsActivity::class.java))

        }

        val cardView2 = findViewById<CardView>(R.id.cardView2)
        cardView2.setOnClickListener {
            startActivity(Intent(this, Reservations::class.java))

        }

        val cardView3 = findViewById<CardView>(R.id.cardView3)
        cardView3.setOnClickListener {
            startActivity(Intent(this, ManagementActivity::class.java))

        }

        val progress = findViewById<CardView>(R.id.progress)
        progress.setOnClickListener {
            startActivity(Intent(this, GetCalves::class.java))

        }
    }
}