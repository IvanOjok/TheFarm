package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.softbox.thefarm.model.DBHelper

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val calfID =  intent.getStringExtra("calfID")
        val type=  intent.getStringExtra("type")
        val detail=  intent.getStringExtra("detail")
        val result=  intent.getStringExtra("result")
        val date=  intent.getStringExtra("date")
        val time=  intent.getStringExtra("time")
        val owner=  intent.getStringExtra("owner")


        val id = findViewById<TextView>(R.id.id)
        val typed = findViewById<EditText>(R.id.type)
        val detaild = findViewById<EditText>(R.id.detail)
        val resultb = findViewById<EditText>(R.id.result)
        val aDate = findViewById<TextView>(R.id.date)
        val aTime = findViewById<TextView>(R.id.time)
        val createdBy = findViewById<EditText>(R.id.createdBy)

        id.text = "Activity for $calfID"

        typed.setText(type)
        detaild.setText(detail)
        resultb.setText(result)
        aDate.setText(date)
        aTime.setText(time)
        createdBy.setText(owner)


        val create = findViewById<Button>(R.id.create)
        create.setOnClickListener {

            val d = typed.text.toString()
            val t = detaild.text.toString()
            val p = resultb.text.toString()
            val dat = aDate.text.toString()
            val tim = aTime.text.toString()
            val emp = createdBy.text.toString()

            if (d.isNotEmpty() && t.isNotEmpty() && p.isNotEmpty() && dat.isNotEmpty() && tim.isNotEmpty() && emp.isNotEmpty()){
                DBHelper(this, null).updateActivity(calfID!!, d, t, p, dat, tim, emp)

                AlertDialog.Builder(this).setMessage("Activity Updated").setPositiveButton("OK", object :
                    DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        startActivity(Intent(this@UpdateActivity, ManagementActivity::class.java))
                        finish()
                    }

                }).show()
//                    startActivity(Intent(this, Home::class.java))
//                    finish()

            }
            else{
                Toast.makeText(this, "Kindly fill in all the necessary credentials", Toast.LENGTH_LONG).show()

            }
        }
    }
}