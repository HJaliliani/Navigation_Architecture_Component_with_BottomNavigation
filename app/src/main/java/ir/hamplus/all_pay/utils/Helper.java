package ir.hamplus.all_pay.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import ir.hamplus.all_pay.R;

/**
 * Created by hashemmousavi on 4/24/2017 AD.
 *
 */

public class Helper {

    private static SharedPreferences preferences;

    public static SharedPreferences getPref(Context context) {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(context);//context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return preferences;
    }

    private static Typeface typefaceRegular;
    private static Typeface typefaceMedium;
    private static Typeface typefaceLight;


    public static Typeface getTypefaceRegular(Context context) {
        if (typefaceRegular == null)
            typefaceRegular = Typeface.createFromAsset(context.getAssets(), "fonts/IRANYekanRegular.ttf");

        return typefaceRegular;
    }

    public static Typeface getTypefaceBold(Context context) {
        if (typefaceMedium == null)
            typefaceMedium = Typeface.createFromAsset(context.getAssets(), "fonts/Vazir.ttf");

        return typefaceMedium;
    }

    public static Typeface getTypefaceLight(Context context) {
        if (typefaceLight == null)
            typefaceLight = Typeface.createFromAsset(context.getAssets(), "fonts/IRANYekanLight.ttf");

        return typefaceLight;
    }

    public static SpannableString getSpannableString(Context context, String txt) {
        SpannableString s = new SpannableString(txt);
        CustomTypefaceSpan c = new CustomTypefaceSpan(getTypefaceRegular(context));
        s.setSpan(c, 0, s.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return s;
    }

    public static void showErrorMessageAlert(Context activity, String msg) {
        if (activity == null)
            return;

        if (activity instanceof Activity) if (((Activity) activity).isFinishing()) return;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setTitle(getSpannableString(activity, "خطا"));
        dialogBuilder.setMessage(getSpannableString(activity, msg));
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public static void showMessageAlert(Context activity, String title, String msg) {
        if (activity == null)
            return;

        if (activity instanceof Activity) if (((Activity) activity).isFinishing()) return;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setTitle(getSpannableString(activity, title));
        dialogBuilder.setMessage(getSpannableString(activity, msg));
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public static boolean isValidEmail(String target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    public static boolean isValidMobile(CharSequence s) {
        return s.toString().startsWith("09") && s.length() == 11;
    }

    public static boolean isValidPhone(String phone) {
        return phone.startsWith("0") && phone.length() == 11;
    }

    public static String thousandSeparator(int n) {
        String s;
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###");
        s = formatter.format(n);

        return String.format("%s", s);
    }


    public static ProgressDialog showProgressDialog(Context context, int stringId) {
        if (context == null)
            return null;

        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return null;
            }
        }

        final ProgressDialog progress = new ProgressDialog(context);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progress.setMessage(getSpannableString(context, context.getString(stringId)));
        progress.setCanceledOnTouchOutside(false);
        progress.setCancelable(false);
        progress.show();
        return progress;
    }

    public static void hideProgressDialog(ProgressDialog pd) {
        try {
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(Context context, String s) {
        if (context == null)
            return;

        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        LayoutInflater inflater = LayoutInflater.from(context);

        View layout = inflater.inflate(R.layout.custom_toast, null);
        TextView text = layout.findViewById(R.id.text);
        text.setText(s);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void showToast(Context context, int resourceID) {
        if (context == null)
            return;

        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        LayoutInflater inflater = LayoutInflater.from(context);

        View layout = inflater.inflate(R.layout.custom_toast, null);
        TextView text = layout.findViewById(R.id.text);
        text.setText(context.getString(resourceID));

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }


    public static Bitmap decodeSampledBitmapFromResource(Context context, int id, int h, int w) {

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), id, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / w, photoH / h);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeResource(context.getResources(), id, bmOptions);

    }


    // TODO: 5/21/2018 AD Sent in AddOrder
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }
}
