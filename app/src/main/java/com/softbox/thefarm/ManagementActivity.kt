package com.softbox.thefarm

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softbox.thefarm.model.ActivityAdapter
import com.softbox.thefarm.model.DBHelper
import com.softbox.thefarm.model.PrefsManager

class ManagementActivity : AppCompatActivity() {

   // lateinit var dialog: android.app.AlertDialog
   private val prefsManager = PrefsManager.INSTANCE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

        prefsManager.setContext(this.application)
        val rView = findViewById<RecyclerView>(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)
        //val o = FirebaseRecyclerOptions.Builder<Doctor>().setQuery(dbRef, Doctor::class.java).build()




        val list = DBHelper(this, null).getAllManagementActivity()
        if (list.isNotEmpty()){
            val adapter = ActivityAdapter(this@ManagementActivity, list)
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
            startActivity(Intent(this, AnimalListActivity::class.java))
        }

//        val back = findViewById<ImageView>(R.id.back)
//        back.setOnClickListener {
//            if (prefsManager.isLoggedIn()) {
//                startActivity(Intent(this, Home::class.java))
//            }
//            else{
//                startActivity(Intent(this, Admin::class.java))
//            }
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.manage_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.send -> {

                val f = DBHelper(this, null).sendCSV()
                val uri = FileProvider.getUriForFile(this, "com.softbox.thefarm.provider", f) //Uri.fromFile(f)
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_SUBJECT, "Records")
                intent.putExtra(Intent.EXTRA_STREAM, uri)
                intent.setType("text/html")
                startActivity(intent)

            }
            R.id.delete -> {
                AlertDialog.Builder(this).setMessage("Are You sure You want to delete all records?").setPositiveButton("OK", object :
                    DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        DBHelper(this@ManagementActivity, null).deleteRecords()
                        startActivity(intent)
                        p0!!.dismiss()
                    }

                }).setNegativeButton("CANCEL", object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }
                }).show()
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}