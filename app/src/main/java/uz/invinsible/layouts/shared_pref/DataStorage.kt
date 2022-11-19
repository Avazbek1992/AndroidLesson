package uz.invinsible.layouts.shared_pref

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import uz.invinsible.layouts.R

class DataStorage(var context: Context) {

    val putExtraNameKey = "KEY_INTENT_NAME"
    val putExtraKey = "KEY_INTENT_PHONE"

    @SuppressLint("CommitPrefEdits")
    fun saveSharedPrefPhone(gmail: String, password: String) {
        val sharedPref = context.getSharedPreferences("RegSharePref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("mail", gmail)
        editor.putString("pass", password)
        editor.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun saveSharedPrefPhone(phone: String) {
        val sharedPref = context.getSharedPreferences("RegSharePref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("phone", phone)
        editor.apply()
    }

    fun readPhone(): String? {
        val sharedPref = context.getSharedPreferences("RegSharePref", Context.MODE_PRIVATE)
        return sharedPref.getString("phone", "")
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

    fun myDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.sign_up_dialog_layout)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        return dialog
    }


    fun getIMGTxt(fullName: String): String {
        val txtArr = fullName.split(" ")
        if (txtArr.size>1)
        return txtArr[0][0] + "" + txtArr[1][0] else return txtArr[0][0]+""
    }
}