 package com.softbox.thefarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.softbox.thefarm.model.PrefsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

 class MainActivity : AppCompatActivity() {

     private val prefsManager = PrefsManager.INSTANCE
     val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefsManager.setContext(this.application)

        Handler().postDelayed({
            if (prefsManager.isLoggedIn()){
                startActivity(Intent(this, Home::class.java))
                finish()
            }
            else{
                startActivity(Intent(this, Login::class.java))
                finish()
            }

        }, 3000)

    }
}