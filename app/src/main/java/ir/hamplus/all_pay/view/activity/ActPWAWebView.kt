package ir.hamplus.all_pay.view.activity

/*import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrPosition*/
import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.net.http.SslError
import android.os.*
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.webkit.*
import android.webkit.WebView
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import ir.hamplus.all_pay.R
import kotlinx.android.synthetic.main.ly_webview.*

//import com.gw.swipeback.SwipeBackLayout
import java.io.File
import java.io.FileOutputStream
import java.util.*


class ActPWAWebView : AppCompatActivity() {
    companion object {
        const val TAG = "WV"
        // var barcode : String =""
        var currentURLforRefresh: String? = ""
        var loadingError: Boolean = false
        // to prevent read & set title in pwa Prefetch call inside thread that crash occurs
        //   var isPrefetch = false
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {


        //  isPrefetch = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ly_webview)


        var loadUrl: String? = null
        if (intent.hasExtra("url"))
            loadUrl = intent.extras["url"]?.toString()
        else finish()

        //   loadUrl ="https://tasn.ir/v1.9/charge-sim/result"
//        if (!URLUtil.isValidUrl(loadUrl)) {
//            Log.i(TAG, "URL is invalid")
//            finish()
//        }

        currentURLforRefresh = loadUrl
      //  var hostUrl = Uri.parse(loadUrl).host


        WV1.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
            }
        }

            WV1.webViewClient = object : WebViewClient() {
                @Suppress("OverridingDeprecatedMember")
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    Log.i(TAG, "shouldOverrideUrlLoading")
                    return super.shouldOverrideUrlLoading(view, url)
                }

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    Log.i(TAG, "shouldOverrideUrlLoading")
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    Log.i(TAG, "onPageStarted")

                }

                // Handle API < 21
                @Suppress("OverridingDeprecatedMember")
                override fun shouldInterceptRequest(
                    view: WebView?,
                    url: String?
                ): WebResourceResponse? {
                    Log.i(TAG, "3_shouldInterceptRequest")
                    return super.shouldInterceptRequest(view, url) // getNewResponse(url!!)
                }

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest?
                ): WebResourceResponse? {
                    Log.i(TAG, "4_shouldInterceptRequest")
                    var url = request!!.url.toString()
                    Log.i(TAG, "Url in 4 =$url")

                    return super.shouldInterceptRequest(view, request) //getNewResponse(url)
                }

                override fun onPageFinished(view: WebView, url: String) {
                    Log.i(TAG, "onPageFinished  , URL=" + url)
                    //  super.onPageFinished(view, url)
                    Log.i(TAG, "onPageFinished  , LoadError=" + loadingError)


                    // when the inside urls of loading page is containing #

                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    Log.i(TAG, "onReceivedSslError")
                    handler?.proceed()
                    var message: String = ""
                    when (error?.primaryError) {
                        SslError.SSL_UNTRUSTED -> message =
                            "The certificate authority is not trusted."
                        SslError.SSL_EXPIRED -> message = "The certificate has expired."
                        SslError.SSL_IDMISMATCH -> message = "The certificate Hostname mismatch."
                        SslError.SSL_NOTYETVALID -> message = "The certificate is not yet valid."
                    }
                    message += " Do you want to continue anyway?"

                }

                override fun onReceivedError(
                    view: WebView,
                    request: WebResourceRequest,
                    error: WebResourceError
                ) {
                    Log.i(TAG, "OnReceiveError new api")

                    super.onReceivedError(view, request, error)
                }

                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    Log.i(
                        TAG,
                        "OnReceiveError : Depricated , errorCode" + errorCode + ", Dec=" + description + " , failing URL =" + failingUrl
                    )
                    Log.i(TAG, "onReceivedError Depri, loadingError=$loadingError ")
                    loadingError = true
                    Log.i(TAG, "onReceivedError Depri, loadingError=$loadingError ")
                    super.onReceivedError(view, errorCode, description, failingUrl)
                }

                override fun onReceivedHttpError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    errorResponse: WebResourceResponse?
                ) {
                    Log.i(TAG, "onReceivedHttpError")
                    Log.i(TAG, "onReceivedHttpError ,errorResponse = " + errorResponse.toString())
                    super.onReceivedHttpError(view, request, errorResponse)

                    Log.i(TAG, "onReceivedHttpError , url=" + view?.url)
                    Log.i(TAG, "Error Loading = title=" + view?.title)
                }
            }
            WV1.settings.javaScriptEnabled = true

            WV1.loadUrl(loadUrl)

        } // onCreate

    }


