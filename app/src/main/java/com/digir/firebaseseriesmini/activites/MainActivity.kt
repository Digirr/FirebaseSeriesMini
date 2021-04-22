package com.digir.firebaseseriesmini.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.digir.firebaseseriesmini.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Lapie widok bottomNavigationView
        val navView: BottomNavigationView = findViewById(R.id.bottomNavView)

        //Potem nav controller
        val navController = findNavController(R.id.mainNavHost)

        //Polaczenie z paskiem na gorze, aby tytuly sie zgadzaly na gorze
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.profileFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}