package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.softbox.thefarm.model.Activity
import com.softbox.thefarm.model.DBHelper
import com.softbox.thefarm.model.Stock

class ActivityDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val calfID =  intent.getStringExtra("calfID")
        val type=  intent.getStringExtra("type")
        val detail=  intent.getStringExtra("detail")
        val result=  intent.getStringExtra("result")
        val date=  intent.getStringExtra("date")
        val time=  intent.getStringExtra("time")
        val owner=  intent.getStringExtra("owner")



        val name = findViewById<TextView>(R.id.name)
        val timed = findViewById<TextView>(R.id.id)
        val purposed = findViewById<TextView>(R.id.course)
        val messaged = findViewById<TextView>(R.id.message)
        val approved = findViewById<TextView>(R.id.approve)

        val bar = findViewById<TextView>(R.id.textView2)
        bar.text = calfID


        val c = findViewById<ImageView>(R.id.back)
        val delete = findViewById<Button>(R.id.delete)

        val update = findViewById<Button>(R.id.update)

        //prefsManager.setContext(this.application)

        name.text = calfID
        timed.text = "Activity Type: $type"
        purposed.text = "Activity Details:\n $detail \n"
        messaged.text = "Activity Results:\n $result  \n"
        approved.text = "Recorded by: $owner \n Date: $date \n Time: $time \n"

        c.setOnClickListener {
            startActivity(Intent(this, ManagementActivity::class.java))
            finish()
        }


        delete.setOnClickListener {
            val activity = Activity(calfID!!, type, detail, result, date!!, time!!, owner!!)
            val del = DBHelper(this, null).deleteActivity(activity)

            if (del){

                androidx.appcompat.app.AlertDialog.Builder(this).setMessage("Activity Record Deleted").setPositiveButton("OK", object :
                    DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        startActivity(Intent(this@ActivityDetails, ManagementActivity::class.java))
                        finish()
                    }

                }).show()
            }
        }

        update.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("calfID", calfID)
            intent.putExtra("type",type)
            intent.putExtra("detail", detail)
            intent.putExtra("result", result)
            intent.putExtra("date", date)
            intent.putExtra("time", time)
            intent.putExtra("owner", owner)
            startActivity(intent)
        }


    }
}