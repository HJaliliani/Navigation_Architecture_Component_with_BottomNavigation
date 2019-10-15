package ir.hamplus.nac_bottom_navigation.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.findNavController
import ir.hamplus.aac_with_bottonnavigation.ListFragment
import ir.hamplus.aac_with_bottonnavigation.ListFragmentDirections
import ir.hamplus.aac_with_bottonnavigation.ListFragmentDirections.ActionListFragmentToParamsFragment
import ir.hamplus.aac_with_bottonnavigation.dummy.DummyContent
import ir.hamplus.nac_bottom_navigation.R

class ActivityLoginPagesNavHost : AppCompatActivity() ,
    ListFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        Log.i("Navigation", "Selected $item")

       // Call by Arguments
       /* val arguments = Bundle()
        arguments.putString("param1", "Mojtaba")
        arguments.putString("param2", "Mousavi Mehman Dusti ${item.toString()}"  )
        findNavController(R.id.nav_host_frag_login_pages).navigate(R.id.action_listFragment_to_paramsFragment, arguments)
            */

        //Call by SafeArgs
        val action = ListFragmentDirections.actionListFragmentToParamsFragment()
        action.param1 = "p11111"
        action.param2 = "P222222"
        findNavController(R.id.nav_host_frag_login_pages).navigate(action)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pages)


    }
}
