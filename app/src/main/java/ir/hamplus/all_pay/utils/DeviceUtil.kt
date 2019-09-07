package ir.hamplus.all_pay.utils

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
//import androidx.constraintlayout.solver.widgets.Guideline
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline


import androidx.exifinterface.media.ExifInterface

import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tapadoo.alerter.Alerter
import ir.hamplus.all_pay.R
import models_m.DynamicModel
import models_m.data.remote.*
import utils.extras.GeneralDicModel
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

//Include Useful functions & Constants

object DeviceUtil {

    private var typefaceRegular: Typeface? = null
    private var typefaceMedium: Typeface? = null
    private var typefaceLight: Typeface? = null
    internal var countRuns: Int = 0
    lateinit var db1: SQLiteDatabase
    val TAG = "DevUtil"
    //Constants
    var deepLinkProtocl: String = "asanpardakht2://"
    val internalDeepLink = 1
    val externalDeepLink = 2
    val DEEP_LINK_VERIFY_CODE = "/verifycode"
    val DEEP_LINK_Promotions = "/campagins"

    private val VIBRATE_LENGTH_MS = 40

    fun vibrate(context: Context) {
        try {
            val mVibrator = context.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
            mVibrator?.vibrate(VIBRATE_LENGTH_MS.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun vibrate(context: Context, length: Int) {
        try {
            val mVibrator = context.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
            mVibrator?.vibrate(length.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun isLocationEnabled(context: Context?): Boolean {
        if (context == null)
            return false

        val locationMode: Int
        val locationProviders: String

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.contentResolver, Settings.Secure.LOCATION_MODE)

            } catch (e: Settings.SettingNotFoundException) {
                e.printStackTrace()
                return false
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF

        } else {
            locationProviders =
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
            return !TextUtils.isEmpty(locationProviders)
        }
    }


    fun hideKeyboard(view: View?) {
        if (view == null) {
            return
        }
        try {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (imm == null || !imm.isActive) {
                return
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (ignored: Exception) {

        }

    }

    private fun isKeyboardShowed(view: View?): Boolean {
        if (view == null) {
            return false
        }
        try {
            val inputManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            return inputManager.isActive(view)
        } catch (e: Exception) {
            //FileLog.e(e);
        }

        return false
    }

    fun dial(activity: Context?, mobile: String) {
        if (activity == null)
            return

        val support_number = "tel:$mobile"

        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(support_number)

        val packageManager = activity.packageManager
        if (packageManager != null && intent.resolveActivity(packageManager) != null) {
            activity.startActivity(intent)
        }
    }

    fun isAppAvailable(context: Context?, appName: String): Boolean {
        if (context == null)
            return false

        val pm = context.packageManager
        return try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

    }


    fun fetchMenuItems(context: Context): MutableList<MenuModel> {
        val rawDynamic = getPrefStrValues(context, "menu_architecture") ?: return mutableListOf()
        val dynamic = Gson().fromJson(rawDynamic, DynamicModel::class.java)
        return dynamic?.sideMenu?.menus ?: mutableListOf()
    }

    // NavigationView Drawer menu items icon set & Sizes
    fun setImageDrawable(context: Context, url: String, menuItem: MenuItem) {
        val uri = Uri.parse(url)
        val marker = PicassoMarker(context, menuItem)

        Picasso.get()
            .load(uri)
            // .noFade()
            // change Size of Menu items when added to menu  it doesn't work just make quality lower
            //  .resize(40.pxToSP(context),15.pxToSP(context))
            //   .fit()
            .into(marker).apply {
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        print("success")
                    }

                    override fun onError(e: Exception) {
                        print("failure")
                    }
                }
            }
    }
    //Added for Exit item in Menu
    /* fun setImageDrawableFromResource(context: Context,url : String,menuItem : MenuItem){
         val uri = Uri.parse(url)
         val marker = PicassoMarker(context,menuItem)

         Picasso.get()
             .load(R.drawable.ic_about_us)
             .noFade()

             .into(marker).apply {  object : com.squareup.picasso.Callback {
                 override fun onSuccess() {
                     print("success")
                 }

                 override fun onError(e: Exception) {
                     print("failure")
                 }
             }
             }
     }
 */
    /* fun getPref(context: Context): SharedPreferences {
        if (preferences == null)
            preferences = context.getSharedPreferences("up733", Context.MODE_PRIVATE)
        return preferences!!
    }*/

    fun savePrefIntValues(context: Context?, key: String, value: Int) {
        //	android.util.Log.i("Pushe","Get Def Pref  in Save="+ PreferenceManager.getDefaultSharedPreferencesName(context) );
        val editor = Helper.getPref(context).edit()
        editor.putInt(key, value)
        editor.apply()

    }

    //
    fun savePrefStrValues(context: Context?, key: String, value: String) {
        val editor = Helper.getPref(context).edit()
        editor.putString(key, value)
        editor.apply()

    }

    //
    fun getPrefIntValues(context: Context?, key: String): Int {
        val getter = Helper.getPref(context)

        return getter.getInt(key, 0)
    }

    //
    fun getPrefStrValues(context: Context?, key: String): String? {
        //sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(context);
        val getter = Helper.getPref(context)

        return getter.getString(key, "")
    }

    fun showToast(context: Context?, s: String) {

        if (context == null)
            return

        if (context is Activity) {
            if ((context as Activity).isFinishing) {
                return
            }
        }
        val inflater = LayoutInflater.from(context)

        val layout = inflater.inflate(R.layout.ly_custom_toast, null)
        val text = layout.findViewById<AppCompatTextView>(R.id.ly_custom_toast_Text)
        text.text = s

        val toast = Toast(context)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100f.dpToPx())
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

    // @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun showAlertMsg(
        context: Activity?,
        s: String,
        hasColor: Boolean = false,
        color: Int? = null,
        duration: Long = 2000
    ) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            context?.let {
                var alerter = Alerter.create(context)
                    // .setTitle("خطا")
                    .setText(s)
                    .setContentGravity(Gravity.CENTER)
                    // .setBackgroundColorInt(Color.RED)
                    .setDuration(duration)
                    .enableSwipeToDismiss()
                //  .setTextTypeface(Typeface.createFromAsset( , "ScopeOne-Regular.ttf"))
                if (hasColor) {
                    alerter.setBackgroundColorInt(color!!)
                } else {
                    var d: Drawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        context!!.resources.getDrawable(R.drawable.alert_background, context.theme)
                    } else {
                        context!!.resources.getDrawable(R.drawable.alert_background)
                    }
                    alerter.setBackgroundDrawable(d)
                }
                alerter.show()
            }
        } else {

            showToast(context, s)
        }
    }

    fun checkValidMobile(s: CharSequence): Boolean {
        return s.toString().startsWith("09") && s.length == 11
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null)
            return false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var activeNetworkInfo: NetworkInfo? = null
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.activeNetworkInfo
        }
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected
    }


    //Read Phone Build infos & build UniqueDeviceKey

    /*   SDK <21
       IMEI?/FingerPrint? + Increment +: + Mobile
       >21
       Finger? + Increment +: + Mobile
     */
    fun uniqueDeviceKey(context: Context, imei: String? = null): String {
        //https://developer.android.com/reference/android/os/Build.VERSION
        val TAG = "DevUtil"

        Log.i(TAG, "DevUtil : IMEI =" + imei)

        Log.i(TAG, "DevUtil : MANUFACTURER =" + Build.MANUFACTURER)
        Log.i(TAG, "DevUtil : MODEL =" + Build.MODEL)
        Log.i(TAG, "DevUtil : PRODUCT =" + Build.PRODUCT)
        Log.i(TAG, "DevUtil : HARDWARE =" + Build.HARDWARE)
        Log.i(TAG, "DevUtil : SDK_INT =" + android.os.Build.VERSION.SDK_INT)
        Log.i(TAG, "DevUtil :  os.version =" + System.getProperty("os.version"))


        var uniquId = ":"

        if (!imei.isNullOrEmpty())
            uniquId += imei + ":"


        var buildFINGERPRINT: String? = null
        if (!Build.FINGERPRINT.isNullOrEmpty() && Build.FINGERPRINT != Build.UNKNOWN) {
            buildFINGERPRINT = Build.FINGERPRINT
            Log.i(TAG, "Build.FINGERPRINT  =" + Build.FINGERPRINT)
        }

        var buildHOST: String? = null
        if (!Build.HOST.isNullOrEmpty()) {
            buildHOST = Build.HOST
            Log.i(TAG, "Build.HOST  =" + Build.HOST)
        }

        var buildVERSIONINCREMENTAL: String? = null
        if (!Build.VERSION.INCREMENTAL.isNullOrEmpty() && Build.VERSION.INCREMENTAL != Build.UNKNOWN) {
            buildVERSIONINCREMENTAL = Build.VERSION.INCREMENTAL + uniquId
            Log.i(TAG, "Build.VERSION.INCREMENTAL  =" + Build.VERSION.INCREMENTAL)
        }

        var buildID: String? = null
        if (!Build.ID.isNullOrEmpty()) {
            buildID = Build.ID
            Log.i(TAG, "Build.ID  =" + Build.ID)
        }

        var mobileNo: String? = getPrefStrValues(context, "mobile_no")
        Log.i(TAG, "uniquId  before concat  =" + uniquId)

        if (!buildFINGERPRINT.isNullOrEmpty())
            uniquId += buildFINGERPRINT + ":"

        if (!buildHOST.isNullOrEmpty())
            uniquId += buildHOST + ":"

        if (!buildVERSIONINCREMENTAL.isNullOrEmpty())
            uniquId += buildVERSIONINCREMENTAL + ":"

        if (!buildID.isNullOrEmpty())
            uniquId += buildID + ":"

        if (!mobileNo.isNullOrEmpty())
            uniquId += mobileNo + ":"

        if (uniquId.length > 200)
            uniquId += uniquId.substring(0, 199)

        Log.i(TAG, "uniquId completed   =" + uniquId)
        return uniquId
    }

/*
    fun startLoading(view: cn.langwazi.loadingbutton.LoadingButton) {
        view.setStatus(cn.langwazi.loadingbutton.LoadingButton.STATUS_LOADING)
    }

    // Hide Button Loading
    fun finishLoading(view: cn.langwazi.loadingbutton.LoadingButton) {
        view.setStatus(cn.langwazi.loadingbutton.LoadingButton.STATUS_NORMAL)
    }
*/

    fun setProfilePicByPicasso(url: String, view: AppCompatImageView, w: Int = 220, h: Int = 220) {
        Picasso.get()
            .load(url)
            .resize(h, w) // h==w for show Round Imace in circle
            //.centerInside()
            //  .transform( CropCircleTransformation())
            //   .transform( CropTransformation(200,200))
            .into(view)

        //  val uri = Uri.parse(url)
        /*  Picasso.get()
              .load(pic)
              .noFade()
              .into(imgV_Profile_pic).apply {
                  object : com.squareup.picasso.Callback {
                      override fun onSuccess() {
                          Log.i(TAG, " onSuccess")
                      }

                      override fun onError(e: Exception) {
                          Log.i(TAG, "onError")
                      }
                  }
              }*/

        /*   Glide.with(this@ActMain)
                 .load(imageUrl )
               .into(imgV_Profile_pic)
           Log.i(TAG, "onDrawerOpened,  Asynch image set done" )
               Log.i(TAG, "onDrawerOpened,  uiThread " )
               imgV_Profile_pic.scaleType = ImageView.ScaleType.FIT_CENTER*/
    }




}
