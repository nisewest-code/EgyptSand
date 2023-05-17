package com.egy.ptsa.nd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.egy.ptsa.nd.databinding.ActivitySelectBackgroundBinding
import com.egy.ptsa.nd.router.Router

class SelectBackgroundActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBackgroundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBackgroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.play.setOnClickListener {
            Router.routerForClass(this, GamePlayEgyptActivity::class.java)
        }
        binding.back.setOnClickListener { Router.back(this) }
    }
}