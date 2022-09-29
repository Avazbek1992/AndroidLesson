package uz.invinsible.layouts.shared_pref

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class DataStorage(var context: Context) {

    @SuppressLint("CommitPrefEdits")
    fun saveSharedPref(gmail: String, password: String) {
        val sharedPref = context.getSharedPreferences("RegSharePref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("mail", gmail)
        editor.putString("pass", password)
        editor.apply()
    }

    fun readSharePref(key: String): String? {
        val sharedPref = context.getSharedPreferences("RegSharePref", Context.MODE_PRIVATE)
        return sharedPref.getString(key, "")
    }

    fun saveSharedPrefProfile(checked: Boolean) {
        val sharedPref = context.getSharedPreferences("RegSharePref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("check", checked)
        editor.apply()
    }

    fun readSharePrefProfile(key: String): Boolean {
        val sharedPref = context.getSharedPreferences("RegSharePref", Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, false)
    }
}