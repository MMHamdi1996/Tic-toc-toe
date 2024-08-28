package com.example.ticgame

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.ticgame.databinding.ActivityOnePlayerGameBinding

class onePlayerGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnePlayerGameBinding
    private val board = Array(3) { arrayOfNulls<String>(3) } // 3x3 board
    private var isPlayerTurn = true // Player starts first

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnePlayerGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBoard()
    }

    private fun setupBoard() {
        // Mapping buttons from the layout
        val buttons = arrayOf(
            arrayOf(binding.bt1, binding.bt2, binding.bt3),
            arrayOf(binding.bt4, binding.bt5, binding.bt6),
            arrayOf(binding.bt7, binding.bt8, binding.bt9)
        )

        // Setting click listeners for all buttons
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].setOnClickListener {
                    if (isPlayerTurn) {
                        if (board[i][j] == null) {
                            board[i][j] = "X" // Player's move
                            animateButton(buttons[i][j], "X", R.drawable.playeronebox)
                            isPlayerTurn = false
                            if (checkWin("X")) {
                                Toast.makeText(this, "برنده شدی", Toast.LENGTH_SHORT).show()
                                resetBoard()
                            } else if (isBoardFull()) {
                                Toast.makeText(this, "بازی مساوی شد", Toast.LENGTH_SHORT).show()
                                resetBoard()
                            } else {
                                Handler().postDelayed({
                                    aiMove(buttons)
                                }, 1000) // Delay AI move by 1 second
                            }
                        }
                    }
                }
            }
        }
    }

    private fun animateButton(button: AppCompatButton, text: String, drawableResId: Int) {
        button.text = text
        button.setBackgroundResource(drawableResId)
        button.isEnabled = false

        // Animation for button click
        ObjectAnimator.ofFloat(button, "scaleX", 1f, 1.2f, 1f).apply {
            duration = 200
        }.start()
        ObjectAnimator.ofFloat(button, "scaleY", 1f, 1.2f, 1f).apply {
            duration = 200
        }.start()
        ObjectAnimator.ofFloat(button, "rotationY", 0f, 360f).apply {
            duration = 200
        }.start()
    }

    private fun aiMove(buttons: Array<Array<AppCompatButton>>) {
        // Simple AI that makes a random move
        var moved = false
        while (!moved) {
            val i = (0..2).random()
            val j = (0..2).random()
            if (board[i][j] == null) {
                board[i][j] = "O" // AI's move
                animateButton(buttons[i][j], "O", R.drawable.playertwobox)
                moved = true
            }
        }
        isPlayerTurn = true
        if (checkWin("O")) {
            Toast.makeText(this, "کامپیوتر برنده شد", Toast.LENGTH_SHORT).show()
            resetBoard()
        } else if (isBoardFull()) {
            Toast.makeText(this, "بازی مساوی شد", Toast.LENGTH_SHORT).show()
            resetBoard()
        }
    }

    private fun checkWin(player: String): Boolean {
        // Checking rows, columns and diagonals for a win
        for (i in 0..2) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true
        return false
    }

    private fun isBoardFull(): Boolean {
        // Checking if all cells are filled
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == null) {
                    return false
                }
            }
        }
        return true
    }

    private fun resetBoard() {
        // Resetting the board for a new game
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = null
            }
        }
        val buttons = arrayOf(
            arrayOf(binding.bt1, binding.bt2, binding.bt3),
            arrayOf(binding.bt4, binding.bt5, binding.bt6),
            arrayOf(binding.bt7, binding.bt8, binding.bt9)
        )
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = ""
                buttons[i][j].isEnabled = true
                buttons[i][j].setBackgroundResource(R.drawable.default_button)
            }
        }
        isPlayerTurn = true
    }
}
