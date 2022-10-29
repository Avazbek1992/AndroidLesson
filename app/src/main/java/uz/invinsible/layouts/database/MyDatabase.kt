package uz.invinsible.layouts.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $USER_TABLE_NAME (" +
                "$USER_ID integer primary key, " +
                "$USER_FULL_NAME text, " +
                "$USER_LAST_MESSAGE text )"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "DROP TABLE IF EXISTS $USER_TABLE_NAME"
        db?.execSQL(query)
        onCreate(db)
    }

    fun insertUser(fullName: String, lastMessage: String) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USER_FULL_NAME, fullName)
        contentValues.put(USER_LAST_MESSAGE, lastMessage)
        db.insert(USER_TABLE_NAME, null, contentValues)
        db.close()
    }

    fun selectUsers(): ArrayList<User> {
        val db = readableDatabase
        val query = "select * from $USER_TABLE_NAME"
        val cursor = db.rawQuery(query, arrayOf())
        val usersList = ArrayList<User>()

        while (cursor.moveToNext()) {
            usersList.add(
                User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    getIMGTxt(cursor.getString(1)),
                )
            )
        }

        return usersList
    }

    private fun getIMGTxt(fullName: String): String {
        val txtArr = fullName.split(" ")
        return txtArr[0][0] + "" + txtArr[1][0]
    }

    fun getUsersCount(): Int {
        val db = readableDatabase
        val query = "select * from $USER_TABLE_NAME"
        val cursor = db.rawQuery(query, arrayOf())
        return cursor.count
    }

    companion object {
        const val DATABASE_NAME = "MyDatabase"
        const val DATABASE_VERSION = 1
        const val USER_TABLE_NAME = "USERS"
        const val USER_ID = "_userId"
        const val USER_FULL_NAME = "_fullName"
        const val USER_LAST_MESSAGE = "_lastMessage"
    }
}