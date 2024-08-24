package com.example.ticgame

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ticgame.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    // متغیرهای مربوط به امتیازات
    var player1Score = 0
    var player2Score = 0
    val winningScore = 3

    private lateinit var binding: ActivityGameBinding
    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun btClick(view: View) {
        var cellId = 0
        val btSelected = view as Button

        when (btSelected.id) {
            binding.bt1.id -> cellId = 1
            binding.bt2.id -> cellId = 2
            binding.bt3.id -> cellId = 3
            binding.bt4.id -> cellId = 4
            binding.bt5.id -> cellId = 5
            binding.bt6.id -> cellId = 6
            binding.bt7.id -> cellId = 7
            binding.bt8.id -> cellId = 8
            binding.bt9.id -> cellId = 9
        }

        playGame(cellId, btSelected)
        checkWinner()
    }

    fun playGame(cellId: Int, btSelected: Button) {
        if (activePlayer == 1) {
            btSelected.text = "x"
            btSelected.setBackgroundResource(R.drawable.playeronebox)
            player1.add(cellId)
            activePlayer = 2
        } else {
            btSelected.text = "o"
            btSelected.setBackgroundResource(R.drawable.playertwobox)
            player2.add(cellId)
            activePlayer = 1
        }
        btSelected.isEnabled = false
    }

    fun checkWinner() {
        var winner = -1

        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) winner = 1
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) winner = 2

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) winner = 1
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) winner = 2

        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) winner = 1
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) winner = 2

        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) winner = 1
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) winner = 2

        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) winner = 1
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) winner = 2

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) winner = 1
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) winner = 2

        if (winner == 1) {
            player1Score += 1
            resetGame()

            if (player1Score == winningScore) {
                showFinalWinnerDialog("بازیکن شماره یک برنده نهایی شد")
            } else {
                Toast.makeText(this, "بازیکن شماره یک این دور رو برد", Toast.LENGTH_SHORT).show()
            }

        } else if (winner == 2) {
            player2Score += 1
            resetGame()

            if (player2Score == winningScore) {
                showFinalWinnerDialog("بازیکن شماره دو برنده نهایی شد")
            } else {
                Toast.makeText(this, "بازیکن شماره دو این دور رو برد", Toast.LENGTH_SHORT).show()
            }

        } else if (player1.size + player2.size == 9) {
            Toast.makeText(this, "بازی مساوی شد", Toast.LENGTH_SHORT).show()
            resetGame()
        }
    }

    fun resetGame() {
        player1.clear()
        player2.clear()
        activePlayer = 1

        for (i in 1..9) {
            val button = when (i) {
                1 -> binding.bt1
                2 -> binding.bt2
                3 -> binding.bt3
                4 -> binding.bt4
                5 -> binding.bt5
                6 -> binding.bt6
                7 -> binding.bt7
                8 -> binding.bt8
                9 -> binding.bt9
                else -> binding.bt1
            }
            button.text = ""
            button.isEnabled = true
            button.setBackgroundResource(android.R.drawable.btn_default)
        }
    }

    fun showFinalWinnerDialog(message: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.winner_dialogue)
        dialog.findViewById<TextView>(R.id.winnerResult).text = message
        dialog.findViewById<Button>(R.id.exit).setOnClickListener {
            val intent = Intent(this, StartGameActivity::class.java)
            startActivity(intent)
        }
        dialog.findViewById<Button>(R.id.againGame).setOnClickListener {
            finish()
            startActivity(intent)
        }
        dialog.show()
    }
}
