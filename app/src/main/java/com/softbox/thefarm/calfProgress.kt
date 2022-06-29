package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softbox.thefarm.model.ActivityAdapter
import com.softbox.thefarm.model.DBHelper
import com.softbox.thefarm.model.PrefsManager

class calfProgress : AppCompatActivity() {
    private val prefsManager = PrefsManager.INSTANCE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calf_progress)

        val calfID =  intent.getStringExtra("calfID")
        val calfAge=  intent.getStringExtra("calfAge")
        val calfWeight=  intent.getStringExtra("calfWeight")
        val calfDetails=  intent.getStringExtra("calfDetails")
        val date=  intent.getStringExtra("date")
        val time=  intent.getStringExtra("time")
        val owner=  intent.getStringExtra("owner")
        val upload=  intent.getStringExtra("upload")

        prefsManager.setContext(this.application)

        val rView = findViewById<RecyclerView>(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)
        //val o = FirebaseRecyclerOptions.Builder<Doctor>().setQuery(dbRef, Doctor::class.java).build()




        val list = DBHelper(this, null).getCalfManagementActivity(calfID!!)
        if (list.isNotEmpty()){
            val adapter = ActivityAdapter(this, list)
            rView.adapter = adapter
        }
        else{
            AlertDialog.Builder(this).setMessage("No records found").setPositiveButton("OK", object :
                DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0!!.dismiss()
                }

            }).show()
        }



        val addReservation = findViewById<ImageView>(R.id.addActivity)
        addReservation.setOnClickListener {

            val intent = Intent(this, DiagnosisActivity::class.java)
            intent.putExtra("calfID", calfID )
            intent.putExtra("calfAge", calfAge)
            intent.putExtra("calfWeight", calfWeight)
            intent.putExtra("calfDetails", calfDetails )
            intent.putExtra("date", date)
            intent.putExtra("time", time)
            intent.putExtra("owner", owner)
            intent.putExtra("upload", upload)

            startActivity(intent)
        }

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            if (prefsManager.isLoggedIn()) {
                startActivity(Intent(this, Home::class.java))
            }
            else{
                startActivity(Intent(this, Admin::class.java))
            }
        }

    }
}