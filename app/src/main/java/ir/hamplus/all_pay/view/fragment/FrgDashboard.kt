package ir.hamplus.all_pay.view.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import ir.hamplus.all_pay.R
import ir.hamplus.all_pay.utils.DeviceUtil
import ir.hamplus.all_pay.view.activity.*
import kotlinx.android.synthetic.main.ly_activity_main.*
import kotlinx.android.synthetic.main.ly_speech.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FrgDashboard.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FrgDashboard.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FrgDashboard : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
  var ReqCode = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ly_frg_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Method 1 to connect BottomNavigation with Navigation controller

        var   mainGrid: GridLayout = view.findViewById<View>(R.id.mainGrid) as GridLayout

        //Set Event
        if (mainGrid != null) {
            setSingleEvent(mainGrid)
        }



    }

    private fun setToggleEvent(mainGrid: GridLayout) {
        //Loop all child item of Main Grid
        for (i in 0 .. mainGrid.childCount) {
            //You can see , all child item is CardView , so we just cast object to CardView
            val cardView = mainGrid.getChildAt(i) as CardView
            cardView.setOnClickListener {
                if (cardView.cardBackgroundColor.defaultColor == -1) {
                    //Change background color
                    cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"))
                 //   Toast.makeText(this@MainActivity, "State : True", Toast.LENGTH_SHORT).show()

                } else {
                    //Change background color
                    cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                 //   Toast.makeText(this@MainActivity, "State : False", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setSingleEvent(mainGrid: GridLayout?) {
        //Loop all child item of Main Grid
        for (i in 0 .. mainGrid!!.childCount-1) {
            //You can see , all child item is CardView , so we just cast object to CardView
            val cardView = mainGrid.getChildAt(i) as CardView
            cardView.setOnClickListener {
                when(i){
                    0 ->{
                        val intent = Intent(context, ActSpech::class.java)
                        startActivityForResult(intent, ReqCode)
                    }
                    1->{
                        Log.i("ALL-Pay", i.toString())
                         val intent = Intent(context, ActBalance::class.java)
                         intent.putExtra("Mobile", DeviceUtil.getPrefStrValues(context, "Mobile"))
                          startActivity(intent)

//                        val int = Intent(context, ActPWAWebView::class.java)
//
//                       // int.putExtra("url", url)
//
//                        context?.startActivity(int)
                    }
                    2 ->{
                        Log.i("ALL-Pay", i.toString())
                        val intent = Intent(context, ActTransfer::class.java)
                        intent.putExtra("Mobile", DeviceUtil.getPrefStrValues(context, "Mobile"))
                        startActivity(intent)

                    }
                    3->{
                        Log.i("ALL-Pay", i.toString())

                        CallWebViewAct("https://www.bazaryonline.com/")

                    }
                    4->{

                        Log.i("ALL-Pay", i.toString())
                        //CallWebViewAct("")
                       // Toast.makeText(context,"Comming Soon",Toast.LENGTH_LONG).show()
                        val intent = Intent(context, ActRecharge::class.java)
                        intent.putExtra("Mobile", DeviceUtil.getPrefStrValues(context, "Mobile"))
                        startActivity(intent)

                    }
                    5 ->{
                        Log.i("ALL-Pay", i.toString())
                        CallWebViewAct("https://erbillifestyle.com/")

                    }
                    6->{
                        Log.i("ALL-Pay", i.toString())
                        CallWebViewAct("https://www.booking.com/city/iq/as-sulaymaniyah.en-gb.html?aid=356980;label=gog235jc-1DCAMobkIPYXMtc3VsYXltYW5peWFoSDNYA2huiAEBmAEJuAEHyAEM2AED6AEBiAIBqAIDuALCuM3rBcACAQ;sid=9b936d76b719240e6eeb2911750cda96;inac=0&keep_landing=1&")

                    }
                    7->{
                        Log.i("ALL-Pay", i.toString())


                        CallWebViewAct("https://en.wikipedia.org/wiki/Sulaymaniyah")

                    }
                    8 ->{
                        Log.i("ALL-Pay", i.toString())


                        CallWebViewAct("https://www.google.com/maps/search/sulaymaniyah+city+tourist+map/@35.5641964,45.3568317,14z/data=!3m1!4b1")
                    }
                    9->{
                        Log.i("ALL-Pay", i.toString())
                        CallWebViewAct("https://www.skyscanner.net/flights-from/isu/cheap-flights-from-sulaymaniyah-international-airport.html")

                    }
                    10->{
                        Log.i("ALL-Pay", i.toString())

                        CallWebViewAct("https://caesar-restaurant-cafe.business.site/")

                    }
                    11 ->{
                        Log.i("ALL-Pay", i.toString())
                       // CallWebViewAct("")
                        Toast.makeText(context,"Comming Soon",Toast.LENGTH_LONG).show()

                    }
                    12->{
                        Log.i("ALL-Pay", i.toString())
                      //  CallWebViewAct("")
                        Toast.makeText(context,"Comming Soon",Toast.LENGTH_LONG).show()

                    }

                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==ReqCode){
            if (resultCode == Activity.RESULT_OK && data!=null) {
                val result = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                tv_result?.text=result[0].toString()

                var speechedWord = data?.extras?.get("speech")
                speechedWord?.let {
                    if(ActSpech.arrForBalance.any { speechedWord.toString().contains(it) }){
                        val intent = Intent(context, ActBalance::class.java)
                        intent.putExtra("Mobile", DeviceUtil.getPrefStrValues(context, "Mobile"))
                        startActivity(intent)
                    }
                    else if (ActSpech.arrForTransfer.any { speechedWord.toString().contains(it) }){
                        val intent = Intent(context, ActTransfer::class.java)
                        intent.putExtra("Mobile", DeviceUtil.getPrefStrValues(context, "Mobile"))
                        startActivity(intent)

                    }
                       /* if ( ActSpech.arrForBalance.contains(speechedWord.toString().toLowerCase()) ){
                            val intent = Intent(context, ActBalance::class.java)
                            intent.putExtra("Mobile", DeviceUtil.getPrefStrValues(context, "Mobile"))
                            startActivity(intent)
                    } else if (ActSpech.arrForTransfer.contains(speechedWord?.toString().toLowerCase() )){
                             val intent = Intent(context, ActTransfer::class.java)
                            intent.putExtra("Mobile", DeviceUtil.getPrefStrValues(context, "Mobile"))
                            startActivity(intent)
                        }*/

                }
            }
        }
    }

    fun CallWebViewAct(url : String ){
        val int = Intent(context, ActPWAWebView::class.java)


//        var url: String? = if (it?.link.toString().endsWith("/"))
//            it?.link.toString()
//        else
//            it?.link.toString() + "/"


        int.putExtra("url", url)
        //must call this flag to prevent fatal error:
        //01-23 15:54:09.154 12391-12391/com.asanpardakht.app.pay E/UncaughtException: android.util.AndroidRuntimeException: Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
//        if (callFrom == "FrgDynamic") {
//            int.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            int.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            int.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
//        } else
//            int.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(int)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FrgDashboard.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FrgDashboard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
