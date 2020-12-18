package com.jehddie.differentiator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jehddie.differentiator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.termsEnteredButton.setOnClickListener {
            val intent = Intent(this, CalculateActivity::class.java)

            // Gets the number of terms selected
            val numberOfTerms = binding.numberOfTermsEditText.text.toString().toIntOrNull()

            // Makes sure it's a valid number
            if (numberOfTerms == null) {
                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (numberOfTerms == 0) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Gets the function selected, true for differentiation
            val functionSelected = when (binding.functionsOption.checkedRadioButtonId) {
                R.id.option_differentiate -> true
                else -> false
            }

            //Integration function not implemented
            if (!functionSelected) {
                Toast.makeText(this, "Integration not implemented", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            intent.putExtra("numberOfTerms", numberOfTerms)
            intent.putExtra("selectedFunction", functionSelected)
            startActivity(intent)
        }
    }
}