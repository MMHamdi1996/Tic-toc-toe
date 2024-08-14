package com.example.ticgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.ticgame.databinding.ActivityGameBinding
import com.example.ticgame.databinding.ActivityStartGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun btClick(view: View) {
        var cellId = 0
        val btSelected = view as Button

        when (btSelected.id) {
            binding.bt1.id ->
                cellId = 1

            binding.bt2.id ->
                cellId = 2

            binding.bt3.id ->
                cellId = 3

            binding.bt4.id ->
                cellId = 4

            binding.bt5.id ->
                cellId = 5

            binding.bt6.id ->
                cellId = 6

            binding.bt7.id ->
                cellId = 7

            binding.bt8.id ->
                cellId = 8

            binding.bt9.id ->
                cellId = 9
        }

        playGame(cellId , btSelected)
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    fun playGame(cellId: Int, btSelected: Button) {
        if (activePlayer == 1) {
            btSelected.text = "x"
            btSelected.setBackgroundResource(R.color.green)
            player1.add(cellId)
            activePlayer = 2
        }
        else{
            btSelected.text = "o"
            btSelected.setBackgroundResource(R.color.yellow)
            player2.add(cellId)
            activePlayer = 1
        }
        btSelected.isEnabled = false

    }
}