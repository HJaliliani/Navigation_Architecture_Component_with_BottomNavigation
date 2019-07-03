package ir.hamplus.aac_with_bottonnavigation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import ir.hamplus.aac_with_bottonnavigation.R

import kotlinx.android.synthetic.main.ly_activity_login_pages_nav_host.*
import kotlinx.android.synthetic.main.ly_fragment_select_language.*

class ActivityLoginPagesNavHost : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ly_activity_login_pages_nav_host)


    }
}
