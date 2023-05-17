package com.egy.ptsa.nd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.egy.ptsa.nd.databinding.ActivityMainBinding
import com.egy.ptsa.nd.router.Router

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.play.setOnClickListener {
            Router.routerForClass(this, SelectBackgroundActivity::class.java)
        }

        binding.settings.setOnClickListener {
            Router.routerForClass(this, SettingsEgyptActivity::class.java)
        }

        binding.exit.setOnClickListener {
            Router.back(this)
        }
    }
}