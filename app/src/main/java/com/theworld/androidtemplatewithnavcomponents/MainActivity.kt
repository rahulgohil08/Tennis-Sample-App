package com.theworld.androidtemplatewithnavcomponents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.theworld.androidtemplatewithnavcomponents.databinding.ActivityMainBinding
import com.theworld.androidtemplatewithnavcomponents.utils.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private val context = this
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPrefManager = SharedPrefManager(context)

        init()
        clickListeners()
    }


    /*---------------------------------------------- Init -----------------------------------------------------*/

    private fun init() {

        setSupportActionBar(binding.includeContent.toolbarInclude.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener(this)

    }


    /*----------------------------------------- Click Listeners -------------------------------*/

    private fun clickListeners() {


    }

    /*---------------------------------------------- On Navigate Up -----------------------------------------------------*/

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    /*-------------------------------------------- On Destination Changed -----------------------------------------------------*/


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {

            /*R.id.dashboardFragment -> {
                displayToolbar(true)
            }

            else -> {
                displayToolbar(true)
            }*/
        }
    }


    /*-------------------------------------------- Display Toolbar -----------------------------------------------------*/

    private fun displayToolbar(isVisible: Boolean) {
        binding.includeContent.toolbarInclude.toolbar.isVisible = isVisible
    }

}