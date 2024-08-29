package com.example.ticgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ticgame.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ticTocToeImageView = binding.ticTocToeImageView
        val ticTacToeTextView = binding.ticTacToeTextView

        // Initial states
        ticTocToeImageView.alpha = 0f
        ticTacToeTextView.alpha = 0f
        ticTocToeImageView.translationY = -1000f
        ticTacToeTextView.translationY = 1000f
        ticTocToeImageView.rotation = 360f
        ticTacToeTextView.scaleX = 0f
        ticTacToeTextView.scaleY = 0f

        // Animation for image view
        ticTocToeImageView.animate()
            .translationY(0f)
            .alpha(1f)
            .rotation(0f)
            .setDuration(2000)

        // Animation for text view
        ticTacToeTextView.animate()
            .translationY(0f)
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(2000)
            .setStartDelay(1000)  // Delay the text animation for better effect

        // Launch next activity after animation
        lifecycleScope.launch {
            delay(3000)  // Wait for the animations to complete
            val intent = Intent(this@MainActivity, ChooseModeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)  // Optional: Add fade transition
        }
    }
}
