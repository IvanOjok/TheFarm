package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.button.MaterialButton
import com.softbox.thefarm.model.DBHelper
import com.softbox.thefarm.model.Employee

class DoctorInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_info)

        val dName = intent.getStringExtra("firstName")
        val lName = intent.getStringExtra("lastName")
        val staffId = intent.getStringExtra("staffId")
        val type = intent.getStringExtra("type",)


//        val mail = intent.getStringExtra("email")
//        val dNo = intent.getStringExtra("idNo")
//        val prof = intent.getStringExtra("prof")

//        val imgId = findViewById<ImageView>(R.id.imageView7)
//        val options: RequestOptions = RequestOptions()
//            .centerCrop()
//            .placeholder(R.drawable.ic_baseline_account_circle_24)
//            .error(R.drawable.ic_baseline_account_circle_24)
//
//        Glide.with(this).load(img).apply(options).into(imgId)

        val name = findViewById<TextView>(R.id.name)
        name.text = dName + lName

        val id = findViewById<TextView>(R.id.id)
        id.text = "Staff ID: $staffId"

        val email = findViewById<TextView>(R.id.mail)
        email.text = "Employment Type: $type"

//        val phone = findViewById<TextView>(R.id.phone)
//        phone.text = "Phone: $dPhone"
//
//        val dProf = findViewById<TextView>(R.id.profession)
//        dProf.text = "Profession: $prof"

        val delete = findViewById<MaterialButton>(R.id.delete)
        delete.setOnClickListener {
            val emp = Employee(dName!!, lName!!, staffId!!, type!!)
            val del = DBHelper(this, null).deleteEmployee(emp)

            if (del){
                AlertDialog.Builder(this).setMessage("Employee Record Deleted Successfully").setPositiveButton("OK", object :
                    DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        startActivity(Intent(this@DoctorInfo, Admin::class.java))
                        finish()
                    }

                }).show()
            }
        }
    }
}