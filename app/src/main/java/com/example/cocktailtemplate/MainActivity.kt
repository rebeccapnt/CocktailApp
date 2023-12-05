package com.example.cocktailtemplate

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.cocktailtemplate.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var progressIndicator: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        // Mise à jour du titre dans la toolbar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = destination.label
        }

        progressIndicator = binding.progressIndicator

        bottomNav = binding.bottomNav
        bottomNav.setupWithNavController(navController)
    }

    fun updateTitle(title: String) {
        binding.toolbar.title = title
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null) || super.onSupportNavigateUp()
    }

    fun enableProgressBar() {
        Log.i("Main Activity", "Enable progressBar")
        progressIndicator.visibility = View.VISIBLE
        binding.nestedScrollView.visibility = View.GONE
    }

    fun disableProgressBar() {
        Log.i("Main Activity", "Disable progressBar")
        progressIndicator.visibility = View.GONE
        binding.nestedScrollView.visibility = View.VISIBLE
    }
}
