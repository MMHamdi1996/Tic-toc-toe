package com.example.ticgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticgame.databinding.ActivityChooseModeBinding
import com.example.ticgame.databinding.ActivityOnePlayerGameBinding
import com.example.ticgame.databinding.ActivityTwoPlayerGameBinding

class ChooseModeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.oneNdGameButton.setOnClickListener {
            val intent = Intent(this , onePlayerGameActivity::class.java)
            startActivity(intent)
        }

        binding.twoNdGameButton.setOnClickListener {
            val intent = Intent(this , twoPlayerGameActivity::class.java)
            startActivity(intent)
        }
    }
}