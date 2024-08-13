package com.example.ticgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.example.ticgame.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            delay(3000)
            val intent = Intent(this@MainActivity , StartGameActivity::class.java)
            startActivity(intent)
        }

    }
}