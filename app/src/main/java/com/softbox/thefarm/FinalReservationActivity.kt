package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.softbox.thefarm.model.DBHelper
import com.softbox.thefarm.model.PrefsManager
import com.softbox.thefarm.model.Stock

class FinalReservationActivity : AppCompatActivity() {
    private val prefsManager = PrefsManager.INSTANCE
    var stdID:String ?= null
    var dId:String ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_reservation)

        val calfID =  intent.getStringExtra("calfID")
        val calfAge=  intent.getStringExtra("calfAge")
        val calfWeight=  intent.getStringExtra("calfWeight")
        val calfDetails=  intent.getStringExtra("calfDetails")
        val date=  intent.getStringExtra("date")
        val time=  intent.getStringExtra("time")
        val owner=  intent.getStringExtra("owner")
        val upload=  intent.getStringExtra("upload")


        val name = findViewById<TextView>(R.id.name)
        val timed = findViewById<TextView>(R.id.id)
        val purposed = findViewById<TextView>(R.id.course)
        val messaged = findViewById<TextView>(R.id.message)
        val approved = findViewById<TextView>(R.id.approve)

        val bar = findViewById<TextView>(R.id.textView2)
        bar.text = calfID


        val c = findViewById<ImageView>(R.id.back)
        val delete = findViewById<Button>(R.id.delete)

        prefsManager.setContext(this.application)

        name.text = calfID
        timed.text = "Brought On: $date $time \n By $owner"
        purposed.text = "Calf Age (when brought): $calfAge \n Calf Weight (when brought): $calfWeight "
        messaged.text = "Calf Details: $calfDetails"
        approved.text = "Registered by: $upload"

        c.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }

        delete.setOnClickListener {
            val stock = Stock(calfID!!, calfAge!!, calfWeight!!, calfDetails!!, date!!, time!!, owner!!, upload!!)
        val del = DBHelper(this, null).deleteStock(stock)

            if (del){

                 androidx.appcompat.app.AlertDialog.Builder(this).setMessage("Calf Record Deleted").setPositiveButton("OK", object :DialogInterface.OnClickListener{
                     override fun onClick(p0: DialogInterface?, p1: Int) {
                         startActivity(Intent(this@FinalReservationActivity, Home::class.java))
                         finish()
                     }

                 }).show()
            }
        }
    }
}