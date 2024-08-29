package com.example.ticgame

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ticgame.databinding.ActivityTwoPlayerGameBinding

class twoPlayerGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTwoPlayerGameBinding
    private var player1Score = 0
    private var player2Score = 0
    private val winningScore = 3

    private var activePlayer = 1
    private val player1 = mutableListOf<Int>()
    private val player2 = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoPlayerGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        // تنظیمات ظاهری
        binding.root.setBackgroundResource(R.drawable.background) // اضافه کردن پس‌زمینه

        // تنظیمات دکمه‌ها
        val textSize = 36f // افزایش سایز متن
        with(binding) {
            val buttons = listOf(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9)
            buttons.forEach {
                it.setBackgroundResource(R.drawable.default_button)
                it.textSize = textSize
            }
        }
    }

    fun btClick(view: View) {
        val btSelected = view as Button
        val cellId = when (btSelected.id) {
            binding.bt1.id -> 1
            binding.bt2.id -> 2
            binding.bt3.id -> 3
            binding.bt4.id -> 4
            binding.bt5.id -> 5
            binding.bt6.id -> 6
            binding.bt7.id -> 7
            binding.bt8.id -> 8
            binding.bt9.id -> 9
            else -> return
        }

        playGame(cellId, btSelected)
        checkWinner()
    }

    private fun playGame(cellId: Int, btSelected: Button) {
        if (activePlayer == 1) {
            btSelected.text = "X"
            btSelected.setBackgroundResource(R.drawable.playeronebox)
            player1.add(cellId)
            activePlayer = 2
        } else {
            btSelected.text = "O"
            btSelected.setBackgroundResource(R.drawable.playertwobox)
            player2.add(cellId)
            activePlayer = 1
        }
        btSelected.isEnabled = false

        // انیمیشن دکمه
        btSelected.animate().apply {
            duration = 200
            rotationYBy(360f)
        }.start()
    }

    private fun checkWinner() {
        val winPatterns = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(3, 6, 9),
            listOf(1, 5, 9),
            listOf(3, 5, 7)
        )

        val winner = when {
            winPatterns.any { pattern -> pattern.all { player1.contains(it) } } -> 1
            winPatterns.any { pattern -> pattern.all { player2.contains(it) } } -> 2
            else -> null
        }

        when (winner) {
            1 -> {
                player1Score++
                if (player1Score == winningScore) {
                    showFinalWinnerDialog("بازیکن شماره یک برنده نهایی شد")
                } else {
                    Toast.makeText(this, "بازیکن شماره یک این دور رو برد", Toast.LENGTH_SHORT).show()
                }
                resetGame()
            }
            2 -> {
                player2Score++
                if (player2Score == winningScore) {
                    showFinalWinnerDialog("بازیکن شماره دو برنده نهایی شد")
                } else {
                    Toast.makeText(this, "بازیکن شماره دو این دور رو برد", Toast.LENGTH_SHORT).show()
                }
                resetGame()
            }
            else -> if (player1.size + player2.size == 9) {
                Toast.makeText(this, "بازی مساوی شد", Toast.LENGTH_SHORT).show()
                resetGame()
            }
        }
    }

    private fun resetGame() {
        player1.clear()
        player2.clear()
        activePlayer = 1

        with(binding) {
            val buttons = listOf(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9)
            buttons.forEach {
                it.text = ""
                it.isEnabled = true
                it.setBackgroundResource(R.drawable.default_button)
                it.textSize = 36f // تنظیم سایز متن
            }
        }
    }

    private fun showFinalWinnerDialog(message: String) {
        val dialog = Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.winner_dialogue)
        }
        dialog.findViewById<TextView>(R.id.winnerResult).text = message
        dialog.findViewById<Button>(R.id.exit).setOnClickListener {
            startActivity(Intent(this, ChooseModeActivity::class.java))
        }
        dialog.findViewById<Button>(R.id.againGame).setOnClickListener {
            finish()
            startActivity(Intent(this, twoPlayerGameActivity::class.java))
        }
        dialog.show()
    }
}
