package ir.hamplus.all_pay.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.ly_activity_main.*
import androidx.navigation.ui.setupWithNavController
import ir.hamplus.all_pay.R


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
        bottomNavigationView.setSelectedItemId(R.id.frgDashboard)

      /*  var   mainGrid: GridLayout = findViewById<View>(R.id.mainGrid) as GridLayout

        //Set Event
        if (mainGrid != null) {
            setSingleEvent(mainGrid)
        }
*/


    }
  /*  private fun setToggleEvent(mainGrid: GridLayout) {
        //Loop all child item of Main Grid
        for (i in 0 .. mainGrid.childCount) {
            //You can see , all child item is CardView , so we just cast object to CardView
            val cardView = mainGrid.getChildAt(i) as CardView
            cardView.setOnClickListener {
                if (cardView.cardBackgroundColor.defaultColor == -1) {
                    //Change background color
                    cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"))
                    Toast.makeText(this@MainActivity, "State : True", Toast.LENGTH_SHORT).show()

                } else {
                    //Change background color
                    cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                    Toast.makeText(this@MainActivity, "State : False", Toast.LENGTH_SHORT).show()
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
                    0->{
                        Log.i("ALL-Pay", i.toString())
                      *//*  val intent = Intent(this@MainActivity, ActivityOne::class.java)
                        intent.putExtra("info", "This is activity from card item index  $i")
                        startActivity(intent)*//*
                    }
                    1 ->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    2->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    3->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    4 ->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    5->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    6->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    7 ->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    8->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    9->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    10 ->{
                        Log.i("ALL-Pay", i.toString())

                    }
                    11->{
                        Log.i("ALL-Pay", i.toString())

                    }

                }

            }
        }
    }

*/
}
