package com.softbox.thefarm.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object{
        val DATABASE_NAME = "The Farm"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "Employees"
        val ID = "ID"
        val EMP_FIRST_NAME = "firstName"
        val EMP_LAST_NAME = "lastName"
        val EMP_ID = "empID"
        val EMP_TYPE = "type"

        val TABLE_NAME2 = "Stock"
        val ID2 = "ID2"
        var calfID = "calfID"
        var calfAge = "calfAge"
        var calfWeight = "calfWeight"
        var calfDetails = "calfDetails"
        var date = "date"
        var time = "time"
        var owner = "owner"
        var upload = "upload"

        val TABLE_3 = "Manage"
        val ID3 = "ID"
        var calfID1 = "calfID"
        var type = "type"
        var detail = "detail"
        var result = "result"
        var aDate = "date"
        var aTime = "time"
        var createdBy = "createdBy"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMP_FIRST_NAME + " TEXT, "
                + EMP_LAST_NAME + " TEXT, " + EMP_ID + " TEXT, " + EMP_TYPE + " TEXT" + ")")



        val query2 = ("CREATE TABLE " + TABLE_NAME2 + "("+ ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + calfID + " TEXT, "
                + calfAge + " TEXT ," + calfWeight + " TEXT, " + calfDetails + " TEXT, " + date + " TEXT," + time + " TEXT," +
                owner + " TEXT, " + upload + " TEXT " + ")")

        val query3 = ("CREATE TABLE " + TABLE_3 + "("+ ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + calfID1 + " TEXT, "
                + type + " TEXT ," + detail + " TEXT, " + result + " TEXT, " + aDate + " TEXT," + aTime + " TEXT," +
                createdBy + " TEXT" + ")")


        p0!!.execSQL(query)
        p0.execSQL(query2)
        p0.execSQL(query3)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
       p0!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        p0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2)
        p0.execSQL("DROP TABLE IF EXISTS " + TABLE_3)

        onCreate(p0)
    }

    fun addEmployee(firstName:String, lastName:String, empId:String, type:String){
        val values = ContentValues()
        values.put(EMP_FIRST_NAME, firstName)
        values.put(EMP_LAST_NAME, lastName)
        values.put(EMP_ID, empId)
        values.put(EMP_TYPE, type)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()

    }

    fun getInfo(): Cursor {
        val db = this.readableDatabase
        return  db.rawQuery("SELECT * FROM"+ TABLE_NAME, null)
    }

    fun checkEmployee(empId: String): Boolean{
        val columns = arrayOf(EMP_FIRST_NAME)
        val db = this.readableDatabase

        //selection criteria
        val selection = "$EMP_ID = ?"

        //selection argument
        val selectionArgs = arrayOf(empId)

        val cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null)

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0){
            return true
        }
        return false
    }

    fun getAllEmployees(): ArrayList<Employee> {

        val columns = arrayOf(
            EMP_FIRST_NAME, EMP_LAST_NAME, EMP_ID, EMP_TYPE
        )

        val sortOrder = "${EMP_ID} DESC"
        val empList = ArrayList<Employee>()

        val db = this.readableDatabase

        val cursor = db.query(TABLE_NAME, columns, null, null, null, null, sortOrder)

        if (cursor.moveToFirst()){
            do {
                val employee = Employee(firstName = cursor.getString(cursor.getColumnIndexOrThrow(
                    EMP_FIRST_NAME)),
                    lastName = cursor.getString(cursor.getColumnIndexOrThrow(EMP_LAST_NAME)),
                    empID = cursor.getString(cursor.getColumnIndexOrThrow(EMP_ID)),
                    type = cursor.getString(cursor.getColumnIndexOrThrow(EMP_TYPE)),)

                empList.add(employee)

            }
            while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return empList

    }

    fun deleteEmployee(s:Employee): Boolean{
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "${EMP_ID} = ?", arrayOf(s.empID))
        db.close()
        return true
    }

    fun getEmployeeInfo(empId: String): Employee{

        var employee: Employee ?= null
        val columns = arrayOf(EMP_FIRST_NAME, EMP_LAST_NAME, EMP_ID, EMP_TYPE)
        val db = this.readableDatabase

        //selection criteria
        val selection = "$EMP_ID = ?"

        //selection argument
        val selectionArgs = arrayOf(empId)

        val cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null)


        if (cursor.moveToFirst()){
            do {
                employee = Employee(firstName = cursor.getString(cursor.getColumnIndexOrThrow(
                    EMP_FIRST_NAME)),
                    lastName = cursor.getString(cursor.getColumnIndexOrThrow(EMP_LAST_NAME)),
                    empID = cursor.getString(cursor.getColumnIndexOrThrow(EMP_ID)),
                    type = cursor.getString(cursor.getColumnIndexOrThrow(EMP_TYPE)),)



            }
            while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
            return employee!!

    }


    fun addStock(calfID:String,
                 calfAge:String,
                 calfWeight:String,
                 calfDetails:String,
                 date:String,
                 time:String,
                 owner:String,
                 upload:String,
    ){
        val values = ContentValues()
        values.put("calfID", calfID)
        values.put("calfAge", calfAge)
        values.put("calfWeight", calfWeight)
        values.put("calfDetails", calfDetails)
        values.put("date", date)
        values.put("time", time)
        values.put("owner", owner)
        values.put("upload", upload)

        val db = this.writableDatabase
        db.insert(TABLE_NAME2, null, values)
        db.close()

    }

    fun getStockInfo(): Cursor {
        val db = this.readableDatabase
        return  db.rawQuery("SELECT * FROM"+ TABLE_NAME2, null)
    }

    fun checkStock(calfID: String): Boolean{
        val columns = arrayOf(calfDetails, owner)
        val db = this.readableDatabase

        //selection criteria
        val selection = "$calfID = ?"

        //selection argument
        val selectionArgs = arrayOf(calfID)

        val cursor = db.query(TABLE_NAME2, columns, selection, selectionArgs, null, null, null)

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0){
            return true
        }
        return false
    }

    fun getAllStock(): ArrayList<Stock> {

        val columns = arrayOf(
            calfID,
            calfAge,
            calfWeight,
            calfDetails,
            date,
            time,
            owner,
            upload
        )

        val sortOrder = "${date} ASC"
        val stockList = ArrayList<Stock>()

        val db = this.readableDatabase

        val cursor = db.query(TABLE_NAME2, columns, null, null, null, null, sortOrder)

        if (cursor.moveToFirst()){
            do {
                val stock = Stock(calfID = cursor.getString(cursor.getColumnIndexOrThrow(
                    calfID
                )),
                    calfAge = cursor.getString(cursor.getColumnIndexOrThrow(calfAge)),
                    calfWeight = cursor.getString(cursor.getColumnIndexOrThrow(calfWeight)),
                    calfDetails = cursor.getString(cursor.getColumnIndexOrThrow(calfDetails)),
                    date = cursor.getString(cursor.getColumnIndexOrThrow(date)),
                    time = cursor.getString(cursor.getColumnIndexOrThrow(time)),
                    owner = cursor.getString(cursor.getColumnIndexOrThrow(owner)),
                    upload = cursor.getString(cursor.getColumnIndexOrThrow(upload)))

                stockList.add(stock)

            }
            while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return stockList


    }

    fun deleteStock(s:Stock): Boolean{
        val db = this.writableDatabase
        db.delete(TABLE_NAME2, "${calfID} = ?", arrayOf(s.calfID))
        db.close()
        return true
    }


    fun addManagementActivity(
                 calfID1: String,
                 type : String,
                 detail : String,
                 result  : String,
                 aDate : String,
                 aTime : String,
                 createdBy: String,
    ){
        val values = ContentValues()
        values.put("calfID", calfID1)
        values.put("type", type)
        values.put("detail", detail)
        values.put("result", result)
        values.put("date", aDate)
        values.put("time", aTime)
        values.put("createdBy", createdBy)

        val db = this.writableDatabase
        db.insert(TABLE_3, null, values)
        db.close()

    }

    fun getAllManagementActivity(): ArrayList<Activity> {

        val columns = arrayOf(
            calfID1,
        type,
        detail,
        result,
        aDate,
        aTime,
        createdBy,
        )

        val sortOrder = "${aDate} ASC"
        val empList = ArrayList<Activity>()

        val db = this.readableDatabase

        val cursor = db.query(TABLE_3, columns, null, null, null, null, sortOrder)

        if (cursor.moveToFirst()){
            do {
                val activity = Activity(calfID = cursor.getString(cursor.getColumnIndexOrThrow(
                    calfID1)),
                    type= cursor.getString(cursor.getColumnIndexOrThrow(
                        type)),
                    detail= cursor.getString(cursor.getColumnIndexOrThrow(
                        detail)),
                    result= cursor.getString(cursor.getColumnIndexOrThrow(
                        result)),
                    aDate= cursor.getString(cursor.getColumnIndexOrThrow(
                        aDate)),
                    aTime = cursor.getString(cursor.getColumnIndexOrThrow(
                        aTime))
                    , createdBy= cursor.getString(cursor.getColumnIndexOrThrow(
                        createdBy)),)

                empList.add(activity)

            }
            while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return empList

    }

    fun getCalfManagementActivity(calfId: String): ArrayList<Activity> {

        val columns = arrayOf(
            calfID1,
            type,
            detail,
            result,
            aDate,
            aTime,
            createdBy,
        )

        val sortOrder = "${aTime} DESC"
        val empList = ArrayList<Activity>()

        val db = this.readableDatabase

        //selection criteria
        val selection = "$calfID1 = ?"

        //selection argument
        val selectionArgs = arrayOf(calfId)

        val cursor = db.query(TABLE_3, columns, selection, selectionArgs, null, null, sortOrder)


        if (cursor.moveToFirst()){
            do {
                val activity = Activity(calfID = cursor.getString(cursor.getColumnIndexOrThrow(
                    calfID1)),
                    type= cursor.getString(cursor.getColumnIndexOrThrow(
                        type)),
                    detail= cursor.getString(cursor.getColumnIndexOrThrow(
                        detail)),
                    result= cursor.getString(cursor.getColumnIndexOrThrow(
                        result)),
                    aDate= cursor.getString(cursor.getColumnIndexOrThrow(
                        aDate)),
                    aTime = cursor.getString(cursor.getColumnIndexOrThrow(
                        aTime))
                    , createdBy= cursor.getString(cursor.getColumnIndexOrThrow(
                        createdBy)),)

                empList.add(activity)

            }
            while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return empList

    }



    fun getActivityInfo(calfId: String, date: String, time: String): Activity{

        var activity: Activity ?= null
        val columns = arrayOf( calfID1,
            type,
            detail,
            result,
            aDate,
            aTime,
            createdBy,)
        val db = this.readableDatabase

        //selection criteria
        val selection = "$calfID1 = ? AND $aDate = ? AND $aTime = ?"

        //selection argument
        val selectionArgs = arrayOf(calfId, date, time)

        val cursor = db.query(TABLE_3, columns, selection, selectionArgs, null, null, null)


        if (cursor.moveToFirst()){
            do {
                  activity = Activity(calfID = cursor.getString(cursor.getColumnIndexOrThrow(
                    calfID1)),
                    type= cursor.getString(cursor.getColumnIndexOrThrow(
                        type)),
                    detail= cursor.getString(cursor.getColumnIndexOrThrow(
                        detail)),
                    result= cursor.getString(cursor.getColumnIndexOrThrow(
                        result)),
                    aDate= cursor.getString(cursor.getColumnIndexOrThrow(
                        aDate)),
                    aTime = cursor.getString(cursor.getColumnIndexOrThrow(
                        aTime))
                    , createdBy= cursor.getString(cursor.getColumnIndexOrThrow(
                        createdBy)),)


            }
            while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return activity!!

    }

    fun deleteActivity(s:Activity): Boolean{
        val db = this.writableDatabase
        db.delete(TABLE_3, "$calfID = ? AND $aDate = ? AND $aTime = ? ", arrayOf(s.calfID, s.aDate, s.aTime))
        db.close()
        return true
    }

    fun updateActivity(calfID1: String,
                       type : String,
                       detail : String,
                       result  : String,
                       aDate : String,
                       aTime : String,
                       createdBy: String,){
        val values = ContentValues()
        values.put("calfID", calfID1)
        values.put("type", type)
        values.put("detail", detail)
        values.put("result", result)
        values.put("date", aDate)
        values.put("time", aTime)
        values.put("createdBy", createdBy)

        val db = this.writableDatabase
        db.update(TABLE_3, values, "$calfID = ? AND $date = ? AND $time = ? ", arrayOf(calfID1, aDate, aTime))
        db.close()

    }

    fun deleteRecords(): Boolean{
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_3)
        db.close()
        return true
    }
    
    fun sendCSV(): File {
        var f = File(Environment.getExternalStorageDirectory(), "")
        if (!f.exists()) {
            f.mkdirs()
        }

        var file = File(f, "records.csv")
        try {
            file.createNewFile()
            val csvWriter = PrintWriter(FileWriter(file))
            val db = this.readableDatabase

            val cursor = db.rawQuery("select * from $TABLE_3", null)
            csvWriter.println("Management Records Table")
            csvWriter.println("calfId, activity type, activity details, activity results, date, time, Recorded by")

            val df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
            while (cursor.moveToNext()) {
                val calfId = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        calfID1
                    )
                )
                val type = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        type
                    )
                )
                val detail = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        detail
                    )
                )
                val result = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        result
                    )
                )
                val aDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        aDate
                    )
                )
                val aTime = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        aTime
                    )
                )
                val createdBy = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        createdBy
                    )
                )

                val record =
                    calfId + "," + type + "," + detail + "," + result + "," + aDate + "," + aTime + "," + createdBy
                csvWriter.println(record)
            }
            cursor.close()
            db.close()
        } catch (e: Exception) {

        }
        return file
    }
        
    }


