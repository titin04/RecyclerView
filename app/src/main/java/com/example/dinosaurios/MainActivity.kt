package com.example.dinosaurios

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dinosaurios.databinding.ActivityMainBinding
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide status bar to avoid system clock overlaying the UI
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.hide(android.view.WindowInsets.Type.statusBars())

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.myToolbar)

        val navHostFragment = try {
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_content_ppal) as? NavHostFragment
        } catch (e: Exception) {
            Log.e(TAG, "Error buscando NavHostFragment", e)
            null
        }

        val actualNavHostFragment = if (navHostFragment != null) {
            navHostFragment
        } else {
            try {
                val created = NavHostFragment.create(R.navigation.nav_graph)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_ppal, created)
                    .setPrimaryNavigationFragment(created)
                    .commitNow()
                created
            } catch (e: Exception) {
                Log.e(TAG, "No se pudo crear NavHostFragment programáticamente", e)
                finish()
                return
            }
        }

        navController = actualNavHostFragment.navController

        val drawerLayout = binding.myDrawer
        val navView = binding.myNavView

        // conectar bottom navigation
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_nav)
        bottomNav?.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.aboutFragment,
                R.id.crudFragment
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_logout -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    true
                }

                else -> {
                    NavigationUI.onNavDestinationSelected(item, navController)
                    drawerLayout.closeDrawers()
                    true
                }
            }
        }

        try {
            val username = intent.getStringExtra("username") ?: "Invitado"

            val headerView = try {
                if (navView.headerCount > 0) {
                    navView.getHeaderView(0)
                } else {
                    // Inflar el header manualmente para asegurarnos de que exista
                    navView.inflateHeaderView(R.layout.nav_header)
                }
            } catch (_: Exception) {
                Log.e(TAG, "Error obteniendo/infalando header del NavigationView")
                null
            }

            val txtName = headerView?.findViewById<TextView>(R.id.txt_name)
            if (txtName != null) {
                txtName.text = username
            } else {
                Log.w(TAG, "txt_name no encontrado en el header del NavigationView")
            }
        } catch (_: Exception) {
            Log.e(TAG, "Error inicializando header/username")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_logout -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }

            R.id.action_settings -> {
                try {
                    navController.navigate(R.id.settingsFragment)
                } catch (_: Exception) {
                    Toast.makeText(this, "No se pudo abrir Settings", Toast.LENGTH_SHORT).show()
                }
                true
            }

            R.id.action_search -> {
                try {
                    navController.navigate(R.id.shareFragment)
                } catch (_: Exception) {
                    Toast.makeText(this, "No se pudo abrir Search", Toast.LENGTH_SHORT).show()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
