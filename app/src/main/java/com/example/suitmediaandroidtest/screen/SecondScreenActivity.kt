package com.example.suitmediaandroidtest.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediaandroidtest.databinding.ActivitySecondscreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get all data intent
        val name = intent.getStringExtra("name") ?: ""
        val username = intent.getStringExtra("username") ?: ""

        // Check the intent name, to display the data
        binding.tvName.text = if (name.isNotEmpty()) name else "John Doe"

        if (username.isNotEmpty()) {
            binding.tvUsername.visibility = View.INVISIBLE
            binding.tvSelected.visibility = View.VISIBLE
            binding.tvSelected.text = username
        } else {
            binding.tvUsername.visibility = View.VISIBLE
            binding.tvSelected.visibility = View.INVISIBLE
        }

        // Invoke the function
        btnBackHandler()
        btnChooseUser()
    }

    private fun btnBackHandler() {
        binding.btnBack.setOnClickListener {
            finish() // Close the current activity and return to the previous one
        }
    }

    private fun btnChooseUser() {
        // Move to ThirdScreenActivity when btn_choose is clicked
        binding.btnChoose.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java).also {
                it.putExtra("name", binding.tvName.text.toString())
            }
            startActivity(intent)
        }
    }
}
