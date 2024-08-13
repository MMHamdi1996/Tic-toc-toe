package com.example.ticgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticgame.databinding.ActivityStartGameBinding

class StartGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStartGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}