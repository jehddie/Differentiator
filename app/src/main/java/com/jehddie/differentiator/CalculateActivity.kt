package com.jehddie.differentiator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.jehddie.differentiator.databinding.ActivityCalculateBinding
import kotlin.math.abs

class CalculateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculateBinding

    //private var basesList = mutableListOf<Int>()
    private var powersList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gets data from previous activity
        val numberOfTerms = intent.getIntExtra("numberOfTerms", 1)
        val functionSelected = intent.getBooleanExtra("selectedFunction", true)

        // Adds items to the list
        postToList(numberOfTerms)

        // Uses the lists above to create the layout
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = TermsAdapter(basesList, powersList)
        recyclerView.adapter = TermsAdapter(powersList)

        
        // Scrollbar for answers text
        //binding.answerText.movementMethod = ScrollingMovementMethod()
        //binding.answerText.setHorizontallyScrolling(true)

        // Sends individual terms to function and merges result
        binding.calculateButton.setOnClickListener {

            var answer = ""

            // Loop through all views in the recyclerView
            for (i: Int in 0 until recyclerView.size) {

                // Gets and stores details of each item in each view
                val rv = recyclerView[i]
                var baseI = rv.findViewById<TextView>(R.id.base_term_edit_text).text.toString()
                var powerI = rv.findViewById<TextView>(R.id.power_text_view).text.toString()

                if (baseI == "") baseI = "0"
                if (powerI == "") powerI = "0"

                // TODO Implement integration
                val result = differentiate(baseI.toInt(), powerI.toInt())

                // Handles unusual cases
                val resultText = when {
                    i == 0 -> {
                        if (result[0] != 0) {
                            "${result[0]}X^${result[1]}"
                        } else
                            "0"
                    }
                    result[0] < 0 -> {
                        if (result[1] != 0) {
                            " - ${abs(result[0])}X^${result[1]}"
                        } else
                            " - ${abs(result[0])}"
                    }
                    result[0] == 0 -> {
                        " + 0"
                    }
                    result[1] == 0 -> {
                        " + ${result[0]}"
                    }
                    else -> " + ${result[0]}X^${result[1]}"
                }

                // Adds results to answer
                answer += resultText
            }
            binding.answerText.text = answer
        }
    }

    // Adds items to the list
    //private fun addToList(base: Int, power: Int) {
    private fun addToList(power: Int) {
        //basesList.add(base)
        powersList.add(power)
    }

    // Posts items to the list
    private fun postToList(numberOfTerms: Int) {
        for (i: Int in 1..numberOfTerms) {
            //addToList(0, numberOfTerms + 1 - i)
            addToList(numberOfTerms + 1 - i)
        }
    }

    // Differentiates a single term
    private fun differentiate(base: Int, power: Int): List<Int> {
        return listOf(base * power, power - 1)
    }

    // Integrates a single term
    private fun integrate(base: Int, power: Int): List<Float> {
        return listOf((base / (power + 1)).toFloat(), (power + 1).toFloat())
    }


    /**
     *if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
    Html.fromHtml(
    "${result[0]}<sup>${result[1]}</sup>",
    Html.FROM_HTML_MODE_LEGACY
    )
    } else
    Html.fromHtml("${result[0]}<sup>${result[1]}</sup>")
     */
}