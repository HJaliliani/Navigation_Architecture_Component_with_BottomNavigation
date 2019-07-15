package ir.hamplus.aac_with_bottonnavigation.view.activity

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.ly_activity_main.*
import androidx.navigation.ui.NavigationUI
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ir.hamplus.aac_with_bottonnavigation.R


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.frgHome -> {
                Toast.makeText(this,"Home Clicked", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.frgDashboard -> {
                Toast.makeText(this,"Dashboard Clicked", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.frgNotifications -> {
                Toast.makeText(this,"Notification Clicked", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ly_activity_main)

        //Method 1 to connect BottomNavigation with Navigation controller
        val navControl = findNavController( R.id.nav_host_frag_main)
        bottomNavigationView?.setupWithNavController(navControl)

        //Method2:
       /*  val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_frag_main) as NavHostFragment?
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment!!.navController)*/


        //Add listener to Bottom Navigation Button
        //bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)




    }
}
