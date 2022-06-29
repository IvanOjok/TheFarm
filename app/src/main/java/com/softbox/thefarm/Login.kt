package com.softbox.thefarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.softbox.thefarm.model.PrefsManager
import com.softbox.thefarm.model.DBHelper

class Login : AppCompatActivity() {


    private val prefsManager = PrefsManager.INSTANCE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefsManager.setContext(this.application)



        //dialog = SpotsDialog.Builder().setContext(this).build()

        val dbHelper = DBHelper(this, null)


        val log = findViewById<Button>(R.id.login)
        log.setOnClickListener {


            val id = findViewById<EditText>(R.id.stdNo)
            val stdNo = id.text.toString()

            if(stdNo.isNotEmpty()){

                //dialog.show()

                if (stdNo == "Admin"){
                   // dialog.dismiss()
                    startActivity(Intent(this@Login, Admin::class.java))
                    finish()
                }
                else{

               val c = dbHelper.checkEmployee(stdNo)
                    if (c == true){
                        val employee = dbHelper.getEmployeeInfo(stdNo)
                        prefsManager.onLogin(employee)

                        //dialog.dismiss()
                        startActivity(Intent(this, Home::class.java))
                        finish()
                    }
                    else{
                        //dialog.dismiss()
                        Toast.makeText(this, "No record of employee", Toast.LENGTH_LONG).show()
                    }

                }
            }
            else{
                Toast.makeText(this, "Fill in the correct Details", Toast.LENGTH_LONG).show()
            }
        }


    }
}