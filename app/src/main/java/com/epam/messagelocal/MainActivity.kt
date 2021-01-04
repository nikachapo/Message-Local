package com.epam.messagelocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val options = navOptions {
        anim {
            enter = android.R.anim.fade_in
            exit = android.R.anim.fade_out
            popEnter = android.R.anim.fade_in
            popExit = android.R.anim.fade_out
        }
    }

    private lateinit var navController: NavController

    private lateinit var bottomNavView: BottomNavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavView = findViewById(R.id.nav_view)
        initNavigation()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_chats, R.id.navigation_search)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun initNavigation() {
        navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavView.isVisible = destination.id != R.id.navigation_messages

            if (destination.id == R.id.navigation_search) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }
}