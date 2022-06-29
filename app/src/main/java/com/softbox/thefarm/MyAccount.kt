package com.softbox.thefarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.softbox.thefarm.model.PrefsManager

class MyAccount : AppCompatActivity() {

    private val prefsManager = PrefsManager.INSTANCE
    lateinit var dialog: android.app.AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        prefsManager.setContext(this.application)

        val name = findViewById<TextView>(R.id.name)
        val stdNo = findViewById<TextView>(R.id.id)
        val course = findViewById<TextView>(R.id.course)
//        val email = findViewById<TextView>(R.id.email)
//        val imageView7 = findViewById<ImageView>(R.id.imageView7)

        val employee = prefsManager.getEmployee()
        name.text = "${employee.firstName}  ${employee.lastName}"
        //stdNo.text = "ID: ${student.id}"
        stdNo.text = "Staff ID: ${employee.empID}"
        course.text = "Contract type: ${employee.type}"


//        if (prefsManager.getStudent().image != "none") {
//
//            val options: RequestOptions = RequestOptions()
//                .centerCrop()
//                .placeholder(R.drawable.ic_baseline_account_circle_24)
//                .error(R.drawable.ic_baseline_account_circle_24)
//
//            Glide.with(this).load(prefsManager.getStudent().image).apply(options).into(imageView7)
//        }


        val logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener {
            prefsManager.onLogout()
            startActivity(Intent(this, Login::class.java))
            finish()
        }

    }
}