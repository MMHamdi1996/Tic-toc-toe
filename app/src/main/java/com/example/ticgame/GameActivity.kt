package com.example.ticgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticgame.databinding.ActivityGameBinding
import com.example.ticgame.databinding.ActivityStartGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}