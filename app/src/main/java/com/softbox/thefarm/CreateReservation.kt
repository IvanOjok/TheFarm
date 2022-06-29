package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.softbox.thefarm.model.PrefsManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.softbox.thefarm.model.DBHelper
import java.util.*

class CreateReservation : AppCompatActivity() {

    private val prefsManager = PrefsManager.INSTANCE
    //lateinit var dialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_reservation)

        prefsManager.setContext(this.application)



        val image = findViewById<ImageView>(R.id.imageView2)
        image.setOnClickListener {
            startActivity(Intent(this, Reservations::class.java))
            finish()
        }


        val calfID = findViewById<EditText>(R.id.calfID)
        val calfAge = findViewById<EditText>(R.id.calfAge)
        val calfWeight = findViewById<EditText>(R.id.calfWeight)
        val calfDetails = findViewById<EditText>(R.id.calfDetails)
        val date = findViewById<TextView>(R.id.date)
        val time = findViewById<TextView>(R.id.time)
        val owner = findViewById<EditText>(R.id.owner)
        val upload = findViewById<EditText>(R.id.upload)



        val cale = Calendar.getInstance()
        val calYear = cale.get(Calendar.YEAR)
        val month = cale.get(Calendar.MONTH)
        val day = cale.get(Calendar.DAY_OF_MONTH)

        val calHour = cale.get(Calendar.HOUR_OF_DAY)
        val calMin = cale.get(Calendar.MINUTE)

        date.setText("$day/$month/$calYear")
        time.setText("$calHour:$calMin")
        if(prefsManager.isLoggedIn()){
            val empId = prefsManager.getEmployee().empID
            upload.setText(empId)
        }


        date.setOnClickListener {
            val cal = MaterialDatePicker.Builder.datePicker()
            cal.setTitleText("Select a Date")
            val calender = cal.build()
            calender.show(supportFragmentManager, "MATERIAL_DATE_PICKER")

            calender.addOnPositiveButtonClickListener(object : MaterialPickerOnPositiveButtonClickListener<Long>{
                override fun onPositiveButtonClick(selection: Long) {
                    date.setText(calender.headerText)
                }

            })

        }
//
        time.setOnClickListener {

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

                time.setText(fTime)
            }

        }


        val create = findViewById<Button>(R.id.create)
        create.setOnClickListener {

            val d = date.text.toString()
            val t = time.text.toString()

            val id = calfID.text.toString()
            val age = calfAge.text.toString()
            val weight = calfWeight.text.toString()
            val details = calfDetails.text.toString()
            val own = owner.text.toString()
            val employeeId = upload.text.toString()

            if (d.isNotEmpty() && t.isNotEmpty() && id.isNotEmpty() && age.isNotEmpty() && weight.isNotEmpty() && details.isNotEmpty()
                && own.isNotEmpty() && employeeId.isNotEmpty() && id.length == 12 ){
               // dialog.show()

                //val k = UUID.randomUUID().toString()

                DBHelper(this, null).addStock(id, age, weight, details, d, t, own, employeeId)

                //dialog.dismiss()
                AlertDialog.Builder(this).setMessage("New Calf record added").setPositiveButton("OK", object :DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            startActivity(Intent(this@CreateReservation, Reservations::class.java))
                            finish()
                        }

                    }).show()
//
            }

            else{
                Toast.makeText(this, "Kindly fill in the correct credentials", Toast.LENGTH_LONG).show()

            }
        }
    }
}