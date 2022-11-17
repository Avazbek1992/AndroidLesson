package uz.invinsible.layouts.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class MyDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $USER_TABLE_NAME (" +
                "$USER_ID text primary key, " +
                "$USER_FULL_NAME text, " +
                "$USER_LAST_MESSAGE text )"
        db?.execSQL(query)
        val query1 = "CREATE TABLE $MESSAGE_TABLE_NAME (" +
                "$MESSAGE_ID integer primary key, " +
                "$MESSAGE_FROM integer, " +
                "$MESSAGE_TO integer," +
                "$MESSAGE_TEXT text," +
                "$MESSAGE_DATE text )"
        db?.execSQL(query1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "DROP TABLE IF EXISTS $USER_TABLE_NAME"
        db?.execSQL(query)
        onCreate(db)
        val query1 = "DROP TABLE IF EXISTS $MESSAGE_TABLE_NAME"
        db?.execSQL(query1)
        onCreate(db)
    }

    fun insertUser(phone: String, fullName: String, lastMessage: String) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USER_ID, phone)
        contentValues.put(USER_FULL_NAME, fullName)
        contentValues.put(USER_LAST_MESSAGE, lastMessage)
        db.insert(USER_TABLE_NAME, null, contentValues)
        db.close()
    }

    fun insertMessage(from: Int, to: Int, message: String, currentDate: String) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MESSAGE_FROM, from)
        contentValues.put(MESSAGE_TO, to)
        contentValues.put(MESSAGE_TEXT, message)
        contentValues.put(MESSAGE_DATE, currentDate)
        db.insert(MESSAGE_TABLE_NAME, null, contentValues)
        db.close()
    }

    fun selectMessages(from: Int, to: Int): ArrayList<Message> {
        val db = readableDatabase
        val query =
            "select * from $MESSAGE_TABLE_NAME where $MESSAGE_FROM = $from and $MESSAGE_TO = $to or $MESSAGE_FROM = $to and $MESSAGE_TO = $from"
        val cursor = db.rawQuery(query, arrayOf())
        val messageList = ArrayList<Message>()
        while (cursor.moveToNext()) {
            messageList.add(
                Message(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    ""
                )
            )
        }
        return messageList
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

    fun getIMGTxt(fullName: String): String {
        val txtArr = fullName.split(" ")
        return txtArr[0][0] + "" + txtArr[1][0]
    }

    fun getUsersCount(): Int {
        val db = readableDatabase
        val query = "select * from $USER_TABLE_NAME"
        val cursor = db.rawQuery(query, arrayOf())
        return cursor.count
    }

    fun getUser(userId: Int): User {
        val db = readableDatabase
        val query = "select * from $USER_TABLE_NAME where $USER_ID = $userId"
        val cursor = db.rawQuery(query, arrayOf())
        cursor.moveToFirst()
        return User(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            getIMGTxt(cursor.getString(1))
        )
    }

    fun updateLastMessage(userId: Int, lastMessage: String) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USER_LAST_MESSAGE, lastMessage)
        db.update(USER_TABLE_NAME, contentValues, "$USER_ID=?", arrayOf(userId.toString()))
        db.close()
    }

    companion object {
        const val DATABASE_NAME = "MyDatabase"
        const val DATABASE_VERSION = 1
        const val USER_TABLE_NAME = "USERS"
        const val USER_ID = "_userId"
        const val USER_FULL_NAME = "_fullName"
        const val USER_LAST_MESSAGE = "_lastMessage"
        const val MESSAGE_TABLE_NAME = "MESSAGE"
        const val MESSAGE_ID = "_messageId"
        const val MESSAGE_FROM = "_from"
        const val MESSAGE_TO = "_to"
        const val MESSAGE_TEXT = "_message"
        const val MESSAGE_DATE = "_date"

    }
}