package com.project.skinsolve.view.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.project.skinsolve.R
import com.project.skinsolve.view.ViewModelFactory
import com.project.skinsolve.view.login.LoginActivity
import com.project.skinsolve.view.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoutingActivity : AppCompatActivity() {

    private lateinit var routingViewModel: RoutingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        routingViewModel = ViewModelProvider(this, ViewModelFactory(this))[RoutingViewModel::class.java]

        lifecycleScope.launch {
            delay(3000)
            routingViewModel.isLogin().observe(this@RoutingActivity) { isLogin ->
                if (isLogin) {
                    val intent = Intent(this@RoutingActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@RoutingActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}