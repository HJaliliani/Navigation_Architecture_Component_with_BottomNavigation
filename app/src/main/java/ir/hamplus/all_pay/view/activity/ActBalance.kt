package ir.hamplus.all_pay.view.activity

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import ir.hamplus.all_pay.R
import kotlinx.android.synthetic.main.activity_act_transfer.edBalancePhoneNumber
import kotlinx.android.synthetic.main.ly_balance.*


class ActBalance : AppCompatActivity() {
    companion object {
        const val PERMISSIONS_REQUEST_AsiaCell_USSD_CODE = 100
        const val PERMISSIONS_REQUEST_Korek_USSD_Code = 200
        const val PERMISSIONS_REQUEST_Zain_USSD_CODE = 300
    }

    var mobile =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ly_balance)

        if (intent.hasExtra("Mobile")) {
            mobile = intent.extras["Mobile"]?.toString().toString()
            if (mobile.length>0)
                edBalancePhoneNumber.setText(mobile)
            detectPhoneAndCallOperatorBalance(mobile)
         }

        btnBalance.setOnClickListener {
            if (edBalancePhoneNumber.text.toString().length >5)
               detectPhoneAndCallOperatorBalance(edBalancePhoneNumber.text.toString())

        }

        btnBalanceAsiaCellCall.setOnClickListener {
            callAsiaCellUssdBalanceCode()
        }

        btnBalanceCallKorek.setOnClickListener {
            callKorekUssdBalanceCode()
        }

        btnBalanceCallZain.setOnClickListener {
            callZainUssdBalanceCode()
        }
//        btnTransferCharge.setOnClickListener {
//            val i = Intent(this, ActTransfer::class.java)
//          //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(i)
//        }
        edBalancePhoneNumber.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length >= 3) {
                    when (s.substring(0, 3)) {
                        "077" -> {
                            btnBalanceAsiaCellCall.visibility = View.VISIBLE
                            btnBalanceCallKorek.visibility = View.GONE
                            btnBalanceCallZain.visibility = View.GONE
                        }
                        "075" -> {
                            btnBalanceCallKorek.visibility = View.VISIBLE
                            btnBalanceAsiaCellCall.visibility = View.GONE
                             btnBalanceCallZain.visibility = View.GONE


                        }
                        "078" -> {
                            btnBalanceCallZain.visibility = View.VISIBLE
                            btnBalanceCallKorek.visibility = View.GONE
                            btnBalanceAsiaCellCall.visibility = View.GONE
                        }
                        else -> {
                            btnBalanceCallZain.visibility = View.GONE
                            btnBalanceCallKorek.visibility = View.GONE
                            btnBalanceAsiaCellCall.visibility = View.GONE
                        }

                    }
                } else {
                    btnBalanceCallZain.visibility = View.GONE
                    btnBalanceCallKorek.visibility = View.GONE
                    btnBalanceAsiaCellCall.visibility = View.GONE
                }
            } // onTextChanged

            override fun afterTextChanged(s: Editable?) {
                //  TODO("not implemented")
            }

        })


    }

    private fun detectPhoneAndCallOperatorBalance(mobile: String) {
        when (mobile.substring(0, 3)) {
            "077" -> {
                callAsiaCellUssdBalanceCode()
            }
            "075" -> {
               callKorekUssdBalanceCode()

            }
            "078" -> {
               callZainUssdBalanceCode()
            }
            else -> {

            }
        }
    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_AsiaCell_USSD_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callAsiaCellUssdBalanceCode()
                } else {
                    // toast("Permission must be granted in order to display contacts information")
                }
            }
            PERMISSIONS_REQUEST_Korek_USSD_Code ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callKorekUssdBalanceCode()
                } else {
                    // toast("Permission must be granted in order to display contacts information")
                }
            }
            PERMISSIONS_REQUEST_Zain_USSD_CODE ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callZainUssdBalanceCode()
                } else {
                    // toast("Permission must be granted in order to display contacts information")
                }
            }

        }
    }

    @SuppressLint("MissingPermission")
    fun callAsiaCellUssdBalanceCode() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(
                this!!,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) { // Needs permission
            /* DeviceUtil.showAlertMsg(
                 activity,
                 GeneralDicModel.readSms , true, Color.GREEN , 5000
             )*/
            requestPermissions(
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_AsiaCell_USSD_CODE
            )

        } else {     // Permission has already been granted
            val encodedHash = Uri.encode("#")
            val ussd = "*133$encodedHash"
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$ussd")))
        }

    }

    @SuppressLint("MissingPermission")
    fun callKorekUssdBalanceCode() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(
                this!!,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) { // Needs permission
            /* DeviceUtil.showAlertMsg(
                 activity,
                 GeneralDicModel.readSms , true, Color.GREEN , 5000
             )*/
            requestPermissions(
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_Korek_USSD_Code
            )

        } else {     // Permission has already been granted
            val encodedHash = Uri.encode("#")
            val ussd = "*211$encodedHash"
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$ussd")))
        }

    }

    @SuppressLint("MissingPermission")
    fun callZainUssdBalanceCode() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(
                this!!,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) { // Needs permission
            /* DeviceUtil.showAlertMsg(
                 activity,
                 GeneralDicModel.readSms , true, Color.GREEN , 5000
             )*/
            requestPermissions(
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_Korek_USSD_Code
            )

        } else {     // Permission has already been granted
            val encodedHash = Uri.encode("#")
            val ussd = "*142$encodedHash"
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$ussd")))
        }

    }

}
