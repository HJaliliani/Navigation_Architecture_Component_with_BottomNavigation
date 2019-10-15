package ir.hamplus.all_pay.utils;

import android.content.Context;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;
 public class Helper {

    private static SharedPreferences preferences;

    public static SharedPreferences getPref(Context context) {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences;
    }
}
