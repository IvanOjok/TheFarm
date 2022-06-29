package com.softbox.thefarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softbox.thefarm.model.DoctorAdapter
import com.softbox.thefarm.model.DBHelper


class DoctorsActivity : AppCompatActivity() {
    //lateinit var dialog: android.app.AlertDialog
    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors)

        //dialog.show()

        val rView = findViewById<RecyclerView>(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)
        //val o = FirebaseRecyclerOptions.Builder<Doctor>().setQuery(dbRef, Doctor::class.java).build()

        val list = DBHelper(this, null).getAllEmployees()

        val adapter = DoctorAdapter(this@DoctorsActivity, list)
        rView.adapter = adapter



        val b = findViewById<ImageView>(R.id.addDoctor)
        b.setOnClickListener {
            startActivity(Intent(this, AddDoctor::class.java))
            finish()
        }

        val c = findViewById<ImageView>(R.id.back)
        c.setOnClickListener {
            startActivity(Intent(this, Admin::class.java))
            finish()
        }
    }
}