package com.softbox.thefarm.model

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

enum class PrefsManager{
    INSTANCE;

    //constructor() : this()
    //private constructor()

    private var context: Application? = null

    constructor(application: Application) {
        this.context = application
    }

    constructor()

    fun onLogin(employee: Employee): Boolean{
        val sharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_USER_ID, employee.empID)
        editor.putString(KEY_FIRST_NAME, employee.firstName)
        editor.putString(KEY_LAST_NAME, employee.lastName)
        editor.putString(KEY_TYPE, employee.type)
//        editor.putString(KEY_EMAIL, student.email)
//        editor.putString(KEY_STD_NO, student.stdNo)
//        editor.putString(KEY_PASSWORD, student.password)
//        editor.putString(KEY_IMAGE, student.image)
        editor.apply()
        return true
    }

    fun isLoggedIn():Boolean{
        val sharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        if (sharedPreferences.getString(KEY_USER_ID, null) != null)
            return true
        return false
    }

    fun getEmployee(): Employee {
        val sharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return Employee(
             firstName = sharedPreferences.getString(KEY_FIRST_NAME, null)!!,
            lastName = sharedPreferences.getString(KEY_LAST_NAME, null)!!,
            empID = sharedPreferences.getString(KEY_USER_ID, null)!!,
            type = sharedPreferences.getString(KEY_TYPE, null)!!,

//            stdNo = sharedPreferences.getString(KEY_STD_NO, null)!!,
//            password = sharedPreferences.getString(KEY_PASSWORD, null)!!,
//            image = sharedPreferences.getString(KEY_IMAGE, null)!!
        )
    }




//    fun onDLogin(doctor: Doctor): Boolean{
//        val sharedPreferences = context!!.getSharedPreferences(D_PREF_NAME, Context.MODE_PRIVATE)
//        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//        editor.putString(D_ID , doctor.id!!)
//        editor.putString(D_FIRST_NAME, doctor.firstName)
//        editor.putString(D_LAST_NAME, doctor.lastName)
//        editor.putString(D_EMAIL, doctor.email)
//        editor.putString(D_PROFESSION, doctor.profession)
//        editor.putString(D_NO, doctor.doctorNo)
//        editor.putString(D_URL,  doctor.imgUrl)
//        editor.putString(D_PHONE,  doctor.phone)
//        editor.apply()
//        return true
//    }
//
//    fun isDLoggedIn():Boolean{
//        val sharedPreferences = context!!.getSharedPreferences(D_PREF_NAME, Context.MODE_PRIVATE)
//        if (sharedPreferences.getString(D_EMAIL, null) != null)
//            return true
//        return false
//    }
//
//    fun getDoctor(): Doctor {
//        val sharedPreferences = context!!.getSharedPreferences(D_PREF_NAME, Context.MODE_PRIVATE)
//        return Doctor(
//            id = sharedPreferences.getString(D_ID, null)!!,
//            firstName = sharedPreferences.getString(D_FIRST_NAME, null)!!,
//            lastName = sharedPreferences.getString(D_LAST_NAME, null)!!,
//            email = sharedPreferences.getString(D_EMAIL, null)!!,
//            profession = sharedPreferences.getString(D_PROFESSION, null)!!,
//            doctorNo = sharedPreferences.getString(D_NO, null)!!,
//            imgUrl = sharedPreferences.getString(D_URL, null)!!,
//            phone = sharedPreferences.getString(D_PHONE, null)!!
//        )
//    }





    fun onLogout(): Boolean{
        val sharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

//        val dSharedPreferences = context!!.getSharedPreferences(D_PREF_NAME, Context.MODE_PRIVATE)
//        val dEditor = dSharedPreferences.edit()
//        dEditor.clear()
//        dEditor.apply()
        return true
    }

    fun setContext(ctx: Application){
        context = ctx
    }
}
