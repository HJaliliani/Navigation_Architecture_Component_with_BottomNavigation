package ir.hamplus.all_pay.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import ir.hamplus.all_pay.R
import ir.hamplus.all_pay.utils.DeviceUtil
import kotlinx.android.synthetic.main.ly_frg_login_get_mobile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentLoginGetMobile.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentLoginGetMobile.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentLoginGetMobile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next_frg_verify_code.setOnClickListener {

            if ( ! edTMobile.text.toString().isNullOrBlank() ){
                if (edTMobile.text.toString().length>0)
                    DeviceUtil.savePrefStrValues(this.context,"Mobile",edTMobile.text.toString())
               //Goto Verify
               // findNavController().navigate(R.id.action_fragmentLoginGetMobile_to_fragmentLoginVerifyCode)
               // Main Activity
                findNavController().navigate(R.id.action_fragmentLoginGetMobile_to_mainActivity)

                // findNavController().navigate(R.id.action_fragmentLoginGetMobile_to_fragmentLoginVerifyCode)

            }
            else {
               // findNavController().navigate(R.id.action_fragmentLoginGetMobile_to_fragmentLoginVerifyCode)
                findNavController().navigate(R.id.action_fragmentLoginGetMobile_to_mainActivity)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ly_frg_login_get_mobile, container, false)
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
         * @return A new instance of fragment FragmentLoginGetMobile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentLoginGetMobile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
