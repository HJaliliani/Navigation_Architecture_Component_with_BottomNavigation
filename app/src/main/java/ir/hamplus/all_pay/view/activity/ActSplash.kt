package ir.hamplus.all_pay.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ir.hamplus.all_pay.BuildConfig
import ir.hamplus.all_pay.R
import ir.hamplus.all_pay.utils.DeviceUtil
 import kotlinx.android.synthetic.main.ly_act_splash.*

class ActSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContentView(R.layout.ly_act_splash)
        //Show Version of App in ActSplash , Read from Gradle
        val spaces = "\n"
        val appVersionName = BuildConfig.VERSION_NAME
      if (!appVersionName.isNullOrEmpty()) {
          txtV_VersionName.text = "Version $appVersionName"
          txtV_VersionName.typeface = null
      }
        else
          txtV_VersionName.visibility = View.INVISIBLE
        // METHOD 1

        /****** Create Thread that will sleep for 5 seconds  */
        val background = object : Thread() {
            override fun run() {

                try {
                    // Thread will sleep for 5 seconds
                   Thread.sleep((1 * 2000).toLong())
                    if (DeviceUtil.getPrefStrValues(this@ActSplash,"Phone").isNullOrEmpty()) {
                        // call  mobile number Page
                        val i = Intent(baseContext, ActivityLoginPagesNavHost::class.java)
                        startActivity(i)
                        finish()
                    } // if
                    else {
                        val i = Intent(this@ActSplash, MainActivity::class.java)
                        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
                        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK )
                        startActivity(i)
                      finish()
                    } // else
                } catch (e: Exception) {
                }
            }
        } // Thread
        background.start()
    } // onCreate

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
    override fun onRestart() {
        super.onRestart()
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}