package ir.hamplus.all_pay.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_act_transfer.*
import android.widget.Toast
import ir.hamplus.all_pay.R


class ActTransfer : AppCompatActivity() {
    companion object {
        const val PERMISSIONS_REQUEST_AsiaCell_USSD_CODE = 100
        const val PERMISSIONS_REQUEST_Korek_USSD_Code = 200
        const val PERMISSIONS_REQUEST_Zain_USSD_CODE = 300
    }
    var sourceNumber = ""
    var desNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_transfer)


        if (intent.hasExtra("Mobile"))
            edSourceNumTransferBalance.setText(  intent.extras["Mobile"]?.toString() )

       setOperatorImage(edSourceNumTransferBalance.text.toString(), "source")

        edBalancePhoneNumber.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setOperatorImage(s.toString(), "destination")
            } // onTextChanged

            override fun afterTextChanged(s: Editable?) {
            }
        })

        edSourceNumTransferBalance.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setOperatorImage(s.toString(), "source")
            } // onTextChanged

            override fun afterTextChanged(s: Editable?) {
            }
        })

        btn_transferCredit.setOnClickListener {
            if (sourceNumber.length>0 ) {
                when (sourceNumber) {
                    "AsiaCell" ->{
                        callAsiaCellUssdBalanceCode()
                    }
                    "Korek" ->{
                        callKorekUssdBalanceCode()

                    }
                    "Zain" ->{
                        callZainUssdBalanceCode()
                    }
                }
            }

        }
    }

    fun setOperatorImage(mobileNumber: String, type: String) {
        when (type) {
            "destination" ->
                if (mobileNumber.length >= 3) {
                    when (mobileNumber.substring(0, 3)) {
                        "077" -> {
                            ImgDestSim.visibility = View.VISIBLE
                            ImgDestSim.setImageResource(R.drawable.logo_asiacel)
                            desNumber = "AsiaCell"
                        }
                        "075" -> {
                            ImgDestSim.visibility = View.VISIBLE
                            ImgDestSim.setImageResource(R.drawable.logo_korek)
                            desNumber = "Korek"
                        }
                        "078" -> {
                            ImgDestSim.visibility = View.VISIBLE
                            ImgDestSim.setImageResource(R.drawable.logo_zain)
                            desNumber = "Zain"
                        }
                        else -> {
                            desNumber = ""
                            ImgDestSim.visibility = View.GONE
                        }
                    }
                } else {
                    desNumber = ""
                    ImgDestSim.visibility = View.GONE

                }
            "source" -> {
                if (mobileNumber.length >= 3) {
                    when (mobileNumber.substring(0, 3)) {
                        "077" -> {
                            ImgSourceSim.visibility = View.VISIBLE
                            ImgSourceSim.setImageResource(R.drawable.logo_asiacel)
                            sourceNumber = "AsiaCell"
                        }
                        "075" -> {
                            ImgSourceSim.visibility = View.VISIBLE
                            ImgSourceSim.setImageResource(R.drawable.logo_korek)
                            sourceNumber = "Korek"
                        }
                        "078" -> {
                            ImgSourceSim.visibility = View.VISIBLE
                            ImgSourceSim.setImageResource(R.drawable.logo_zain)
                            sourceNumber = "Zain"
                        }
//                        "009" ->{
//                            ImgSourceSim.visibility = View.VISIBLE
//                            ImgSourceSim.setImageResource(R.drawable.logo_zain)
//                            sourceNumber = "Zain"
//                        }
                        else -> {
                            ImgSourceSim.visibility = View.GONE
                            sourceNumber = ""
                        }

                    }
                } else {
                    sourceNumber = ""
                    ImgSourceSim.visibility = View.GONE

                }
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

     fun ussdToCallableUri(ussd: String): Uri {

        var uriString = ""

        if (!ussd.startsWith("tel:"))
            uriString += "tel:"

        for (c in ussd.toCharArray()) {

            if (c == '#')
                uriString += Uri.encode("#")
            else
                uriString += c
        }

        return Uri.parse(uriString)
    }


    @SuppressLint("MissingPermission")
    fun callAsiaCellUssdBalanceCode() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(
                this!!,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) { // Needs permission
            requestPermissions(
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_AsiaCell_USSD_CODE
            )
        } else {     // Permission has already been granted
            val ussd = "*123*${edCreditTransferAmount.text}*${edBalancePhoneNumber.text}#"

            startActivity(Intent(Intent.ACTION_CALL,ussdToCallableUri(ussd) ) )
        }
    }

    @SuppressLint("MissingPermission")
    fun callKorekUssdBalanceCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(
                this!!,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) { // Needs permission
            requestPermissions(
                arrayOf(Manifest.permission.CALL_PHONE),
                PERMISSIONS_REQUEST_Korek_USSD_Code
            )
        } else {     // Permission has already been granted
            val ussd = "*215*${edBalancePhoneNumber.text}*${edCreditTransferAmount.text}#"

            startActivity(Intent(Intent.ACTION_CALL,  ussdToCallableUri(ussd)))
        }
    }

    @SuppressLint("MissingPermission")
    fun callZainUssdBalanceCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(
                this!!,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) { // Needs permission
            requestPermissions(
                arrayOf(Manifest.permission.SEND_SMS),
                PERMISSIONS_REQUEST_Zain_USSD_CODE
            )
        } else {     // Permission has already been granted
            sendSmsMsgFnc(edSourceNumTransferBalance.text.toString() , "${edBalancePhoneNumber.text} ${edCreditTransferAmount.text}")
        }
    }

    fun sendSmsMsgFnc(mblNumVar: String, smsMsgVar: String) {
     /*   if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {*/
            try {
                val smsMgrVar = SmsManager.getDefault()
                smsMgrVar.sendTextMessage(mblNumVar, null, smsMsgVar, null, null)
                Toast.makeText(
                    applicationContext, "Message Sent",
                    Toast.LENGTH_LONG
                ).show()
            } catch (ErrVar: Exception) {
                Toast.makeText(
                    applicationContext, ErrVar.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
                ErrVar.printStackTrace()
            }
    /*    } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.SEND_SMS), 10)
            }
        }*/

    }

}
