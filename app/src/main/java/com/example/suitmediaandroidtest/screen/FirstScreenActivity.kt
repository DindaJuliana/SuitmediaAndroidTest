package com.example.suitmediaandroidtest.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediaandroidtest.R
import com.example.suitmediaandroidtest.databinding.ActivityFirstscreenBinding

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnNextHandler()
        btnCheckPalindrome()
    }

    /*
    * private fun btnCheckPalindrome
    * -> button to check input, it's palindrome or not, if it is so will show up
    *    "it's palindrome", reverse "not palindrome"
    * */
    private fun btnCheckPalindrome() {
        binding.btnCheck.setOnClickListener {
            // get edit text palindrome value
            val word = binding.etPalindrome.text.toString().trim()
            // check if reversedWord is same to word
            if (word == word.reversed()) {
                Toast.makeText(this, "It's Palindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Palindrome", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*
    * private fun btnNextHandler
    * -> button next in first screen listener, to move to second screen
    *    with transfer data name which inputted in first screen
    * */
    private fun btnNextHandler() {
        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Fill the name", Toast.LENGTH_SHORT).show()
            } else {
                // create intent and add data
                val intent = Intent(this, SecondScreenActivity::class.java).apply {
                    putExtra("name", if (name.isNotEmpty()) name else "John Doe")
                    putExtra("username", "")
                }
                startActivity(intent)
            }
        }
    }
}