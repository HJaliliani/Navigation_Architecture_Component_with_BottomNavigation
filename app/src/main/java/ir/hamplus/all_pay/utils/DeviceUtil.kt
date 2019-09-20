package ir.hamplus.all_pay.utils


import android.content.Context

object DeviceUtil {

    fun savePrefStrValues(context: Context?, key: String, value: String) {
        val editor = Helper.getPref(context).edit()
        editor.putString(key, value)
        editor.apply()

    }


    //
    fun getPrefStrValues(context: Context?, key: String): String? {
        val getter = Helper.getPref(context)

        return getter.getString(key, "")
    }

}
