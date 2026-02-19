package com.example.everyonecoloringv3

import android.content.res.ColorStateList
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.everyonecoloringv3.core.ui.BaseActivity
import com.example.everyonecoloringv3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isDarkMode = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        applyNavigationBarStyle(isDarkMode)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        val navController = navHost.navController

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)

        setupCustomBottomBar(navController)
    }

    private fun setupCustomBottomBar(navController: androidx.navigation.NavController) {
        val options = navOptions {
            launchSingleTop = true
            restoreState = true
            popUpTo(navController.graph.startDestinationId) { saveState = true }
        }
        binding.navHome.setOnClickListener {
            if (navController.currentDestination?.id != R.id.homeFragment) {
                navController.navigate(R.id.homeFragment, null, options)
            }
        }
        binding.navUser.setOnClickListener {
            if (navController.currentDestination?.id != R.id.userFragment) {
                navController.navigate(R.id.userFragment, null, options)
            }
        }
        binding.navCreate.setOnClickListener {
            if (navController.currentDestination?.id != R.id.createPixelFragment) {
                navController.navigate(R.id.createPixelFragment, null, options)
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateBottomBarSelection(destination.id)
        }
    }

    private fun updateBottomBarSelection(destinationId: Int) {
        val selected = ContextCompat.getColor(this, R.color.primaryTextAndIcon)
        val unselected = ContextCompat.getColor(this, R.color.colorMuted)
        val selectedTint = ColorStateList.valueOf(selected)
        val unselectedTint = ColorStateList.valueOf(unselected)
        binding.navHome.imageTintList = if (destinationId == R.id.homeFragment) selectedTint else unselectedTint
        binding.navUser.imageTintList = if (destinationId == R.id.userFragment) selectedTint else unselectedTint
        binding.navCreate.imageTintList = if (destinationId == R.id.createPixelFragment) selectedTint else unselectedTint
    }

    private fun applyNavigationBarStyle(isDarkMode: Boolean) {
        window.navigationBarColor = ContextCompat.getColor(this, R.color.primaryBackground)
        val decorView = window.decorView
        var flags = decorView.systemUiVisibility
        flags = if (!isDarkMode) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
        decorView.systemUiVisibility = flags
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        return navHost.navController.navigateUp() || super.onSupportNavigateUp()
    }
}