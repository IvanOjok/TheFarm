package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.softbox.thefarm.model.DBHelper


class AddDoctor : AppCompatActivity() {


    lateinit var dialog: android.app.AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_doctor)

        val dbHelper = DBHelper(this, null)

        val back = findViewById<ImageView>(R.id.imageView2)
        back.setOnClickListener {
            startActivity(Intent(this, DoctorsActivity::class.java))
            finish()
        }

        val add = findViewById<MaterialButton>(R.id.add)
        add.setOnClickListener {
            val f = findViewById<EditText>(R.id.first)
            val first = f.text.toString()

            val l = findViewById<EditText>(R.id.last)
            val last = l.text.toString()

            val createEmail = findViewById<EditText>(R.id.createEmail)
            val staffId = createEmail.text.toString()

            val c = findViewById<EditText>(R.id.profession)
            val type = c.text.toString()
//
//            val doc = findViewById<EditText>(R.id.dNo)
//            val dNo = doc.text.toString()
//
//            val ph = findViewById<EditText>(R.id.phone)
//            val phone = ph.text.toString()

            if ( first.isNotEmpty() && last.isNotEmpty() && staffId.isNotEmpty() && type.isNotEmpty()){
                dbHelper.addEmployee(first, last, staffId, type)
                AlertDialog.Builder(this).setMessage("New Employee Record added successfully").setPositiveButton("OK", object :
                    DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        startActivity(Intent(this@AddDoctor, Admin::class.java))
                        finish()
                    }

                }).show()

            }
            else{
                Toast.makeText(this, "fill in all details", Toast.LENGTH_SHORT).show()
            }


        }



     }

}