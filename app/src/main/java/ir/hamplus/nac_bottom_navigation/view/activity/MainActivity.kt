package ir.hamplus.nac_bottom_navigation.view.activity

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import ir.hamplus.nac_bottom_navigation.R


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.frgHome -> {
                Toast.makeText(this,"Home Clicked", Toast.LENGTH_SHORT).show()
                findNavController(R.id.nav_host_frag_main).navigate(R.id.action_global_frgHome)

                return@OnNavigationItemSelectedListener true
            }
            R.id.frgDashboard -> {
                Toast.makeText(this,"Dashboard Clicked", Toast.LENGTH_SHORT).show()
                findNavController(R.id.nav_host_frag_main).navigate(R.id.action_global_frgDashboard)

                return@OnNavigationItemSelectedListener true
            }
            R.id.frgNotifications -> {
                Toast.makeText(this,"Notification Clicked", Toast.LENGTH_SHORT).show()

                findNavController(R.id.nav_host_frag_main).navigate(R.id.action_global_frgNotifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Method 1 to connect BottomNavigation with Navigation controller
   /*     val navControl = findNavController( R.id.nav_host_frag_main)
        navControl.popBackStack() //Handle backstack
        bottomNavigationView?.setupWithNavController(navControl)*/


        //Method2:
         /*val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_frag_main) as? NavHostFragment?
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment!!.navController)*/


        //Add listener to Bottom Navigation Button & BackStack Handling
       bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

  /*  override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }*/
}
