package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.softbox.thefarm.model.DBHelper
import com.softbox.thefarm.model.PrefsManager
import java.util.*

class DiagnosisActivity : AppCompatActivity() {

    private val prefsManager = PrefsManager.INSTANCE
   // lateinit var dialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis)

        prefsManager.setContext(this.application)


        val calfID =  intent.getStringExtra("calfID")
        val calfAge=  intent.getStringExtra("calfAge")
        val calfWeight=  intent.getStringExtra("calfWeight")
        val calfDetails=  intent.getStringExtra("calfDetails")
        val date=  intent.getStringExtra("date")
        val time=  intent.getStringExtra("time")
        val owner=  intent.getStringExtra("owner")
        val upload=  intent.getStringExtra("upload")

        val c = findViewById<ImageView>(R.id.back)

        c.setOnClickListener {
            startActivity(Intent(this, ManagementActivity::class.java))
            finish()
        }

        val id = findViewById<TextView>(R.id.id)
        val type = findViewById<EditText>(R.id.type)
        val detail = findViewById<EditText>(R.id.detail)
        val result = findViewById<EditText>(R.id.result)
        val aDate = findViewById<TextView>(R.id.date)
        val aTime = findViewById<TextView>(R.id.time)
        val createdBy = findViewById<EditText>(R.id.createdBy)

        id.text = "Activity for $calfID"


        val cale = Calendar.getInstance()
        val calYear = cale.get(Calendar.YEAR)
        val month = cale.get(Calendar.MONTH)
        val day = cale.get(Calendar.DAY_OF_MONTH)

        val calHour = cale.get(Calendar.HOUR_OF_DAY)
        val calMin = cale.get(Calendar.MINUTE)

        aDate.setText("$day/$month/$calYear")
        aTime.setText("$calHour:$calMin")
        createdBy.setText(prefsManager.getEmployee().firstName + prefsManager.getEmployee().lastName)

        aDate.setOnClickListener {
            val cal = MaterialDatePicker.Builder.datePicker()
            cal.setTitleText("Select a Date")
            val calender = cal.build()
            calender.show(supportFragmentManager, "MATERIAL_DATE_PICKER")

            calender.addOnPositiveButtonClickListener(object :
                MaterialPickerOnPositiveButtonClickListener<Long> {
                override fun onPositiveButtonClick(selection: Long) {
                    aDate.setText(calender.headerText)
                }

            })

        }
//
       aTime.setOnClickListener {

            val t = MaterialTimePicker.Builder().setTitleText("Select time").setHour(12).setMinute(10)
                .setTimeFormat(TimeFormat.CLOCK_12H).build()

            t.show(supportFragmentManager, "TIME")
            t.addOnPositiveButtonClickListener {
                val phour = t.hour
                val pmin = t.minute

                val fTime = when {
                    phour > 12 -> {
                        if (pmin<10){
                            "${phour-12}:${pmin} pm"
                        }
                        else{
                            "${phour-12}:${pmin} pm"
                        }
                    }
                    phour == 12 -> {
                        if (pmin<10){
                            "${phour}:${pmin} pm"
                        }
                        else{
                            "${phour}:${pmin} pm"
                        }
                    }
                    phour == 0 -> {
                        if (pmin<10){
                            "${phour+12}:${pmin} am"
                        }
                        else{
                            "${phour+12}:${pmin} am"
                        }
                    }
                    else -> {
                        if (pmin<10){
                            "${phour}:${pmin} am"
                        }
                        else{
                            "${phour}:${pmin} am"
                        }
                    }
                }

               aTime.setText(fTime)
            }

        }






        val create = findViewById<Button>(R.id.create)
        create.setOnClickListener {

            val d = type.text.toString()
            val t = detail.text.toString()
            val p = result.text.toString()
            val dat = aDate.text.toString()
            val tim = aTime.text.toString()
            val emp = createdBy.text.toString()

            if (d.isNotEmpty() && t.isNotEmpty() && p.isNotEmpty() && dat.isNotEmpty() && tim.isNotEmpty() && emp.isNotEmpty()){
               DBHelper(this, null).addManagementActivity(calfID!!, d, t, p, dat, tim, emp)

                    AlertDialog.Builder(this).setMessage("Activity saved").setPositiveButton("OK", object :
                        DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            startActivity(Intent(this@DiagnosisActivity, ManagementActivity::class.java))
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