package com.obilet.android.assignment

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import com.google.android.material.button.MaterialButton
import com.obilet.android.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainActivityViewModel>()

    private lateinit var navController: NavController

    private lateinit var splashScreen: SplashScreen


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen = installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeObservers()
        splashScreen.apply {
            setKeepOnScreenCondition {
                viewModel.showSplashScreen
            }
            setOnExitAnimationListener {
                it.remove()
            }
        }

    }

    private fun subscribeObservers() {
        viewModel.showErrorDialog.observe(this) { uiText ->
            val message = uiText?.asString(this)
            message?.let {
                showErrorDialog(it)
            }
        }
    }

    private fun setupNavigation() {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
//        navController = navHostFragment.navController
    }

    private fun showErrorDialog(message: String) {
        val dialog = Dialog(this)
        dialog.apply {
            setContentView(R.layout.dialog_error_with_okay_button)
            setCanceledOnTouchOutside(false)
            setCancelable(false)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.attributes?.windowAnimations = R.style.Theme_OBilet_DialogStyle
            window?.decorView?.setBackgroundResource(android.R.color.transparent)
            val okayBtn = findViewById<MaterialButton>(R.id.okayBtn)
            val errorMessageTv = findViewById<TextView>(R.id.errorMessageTv)
            okayBtn.setOnClickListener {
                finishAffinity()
            }
            errorMessageTv.text = message

        }
        dialog.show()

    }
}