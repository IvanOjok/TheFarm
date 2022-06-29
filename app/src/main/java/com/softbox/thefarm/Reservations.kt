package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softbox.thefarm.model.DBHelper
import com.softbox.thefarm.model.StockAdapter
import com.softbox.thefarm.model.PrefsManager


class Reservations : AppCompatActivity() {

    private val prefsManager = PrefsManager.INSTANCE


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations)

        prefsManager.setContext(this.application)


        val rView = findViewById<RecyclerView>(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)
        //val o = FirebaseRecyclerOptions.Builder<Doctor>().setQuery(dbRef, Doctor::class.java).build()




        val list = DBHelper(this, null).getAllStock()
        if (list.isNotEmpty()){
            val adapter = StockAdapter(this@Reservations, list)
            rView.adapter = adapter
        }
        else{
            AlertDialog.Builder(this).setMessage("No records found").setPositiveButton("OK", object :
                DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
//                    startActivity(Intent(this@Reservations, Home::class.java))
//                    finish()
                    p0!!.dismiss()
                }

            }).show()
        }



        val addReservation = findViewById<ImageView>(R.id.addReservation)
        addReservation.setOnClickListener {
            startActivity(Intent(this, CreateReservation::class.java))
        }

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            if (prefsManager.isLoggedIn()){
                startActivity(Intent(this, Home::class.java))
            }
            else{
                startActivity(Intent(this, Admin::class.java))

            }
                  }
    }
}