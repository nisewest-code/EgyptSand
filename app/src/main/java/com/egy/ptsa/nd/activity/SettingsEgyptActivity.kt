package com.egy.ptsa.nd.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.egy.ptsa.nd.R
import com.egy.ptsa.nd.databinding.ActivitySettingsEgyptBinding
import com.egy.ptsa.nd.router.Router
import com.egy.ptsa.nd.str.Pref
import com.egy.ptsa.nd.utils.Bucket
import com.egy.ptsa.nd.utils.PrefEgyptSand
import com.egy.ptsa.nd.utils.SoundService

class SettingsEgyptActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsEgyptBinding
    private var isMusic = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsEgyptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener { Router.back(this) }
        isMusic = Bucket.isMusic
        update()
        binding.btnMusic.setOnClickListener {
            if (isMusic) {
                stopService(Intent(this, SoundService::class.java))
                Bucket.isMusic = false
            } else {
                startService(Intent(this, SoundService::class.java))
                Bucket.isMusic = true
            }
            isMusic = !isMusic
            update()
        }
    }

    private fun update(){
        if (isMusic){
           binding.btnMusic.text = getString(R.string.off)
        } else {
            binding.btnMusic.text = getString(R.string.on)
        }
    }
}