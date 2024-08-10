package com.example.ticgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val ticTocToeImageView = binding.ticTocToeImageView
        val ticTacToeTextView = binding.ticTacToeTextView

        ticTocToeImageView.translationY = -1000f
        ticTacToeTextView.translationY = 1000f

        ticTocToeImageView.animate().translationY(0f).duration = 2000
        ticTacToeTextView.animate().translationY(0f).duration = 2000
    }
}