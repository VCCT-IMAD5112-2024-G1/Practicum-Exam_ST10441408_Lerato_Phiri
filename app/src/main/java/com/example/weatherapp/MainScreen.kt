package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainScreen : AppCompatActivity() {

    private lateinit var dayInput: EditText
    private lateinit var minNo: EditText
    private lateinit var maxNo: EditText
    private lateinit var averageButton: Button
    private lateinit var clearButton: Button
    private lateinit var resultId: TextView

    private val daysOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val minTemps = arrayOf(12, 15, null, null, null, 10, 10)
    private val maxTemps = arrayOf(25, 29, null, null, null, 18, 16)
    private val conditions = arrayOf("Sunny", "Sunny", null, null, null, "Raining", "Cold")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)

        dayInput = findViewById(R.id.dayInput)
        minNo = findViewById(R.id.minNo)
        maxNo = findViewById(R.id.maxNo)
        averageButton = findViewById(R.id.averageButton)
        clearButton = findViewById(R.id.clearButton)
        resultId = findViewById(R.id.resultsId)

        averageButton.setOnClickListener { calculateAverage() }
        clearButton.setOnClickListener { clearData() }


        val detailScreen: Button = findViewById(R.id.detailScreen)
        detailScreen.setOnClickListener {
            val intent = Intent(this@MainScreen, DetailView::class.java)
            startActivity(intent)
        }

        val exitButton2: Button = findViewById(R.id.exitButton2)
        exitButton2.setOnClickListener {
            finishAffinity()
        }
    }

    private fun calculateAverage() {
        val day = dayInput.text.toString().capitalize()
        val minTemp = minNo.text.toString().toIntOrNull()
        val maxTemp = maxNo.text.toString().toIntOrNull()

        val dayIndex = daysOfWeek.indexOf(day)
        if (dayIndex == -1 || minTemp == null || maxTemp == null || minTemp < 1 || minTemp > 50 || maxTemp < 1 || maxTemp > 50) {
            Toast.makeText(
                this,
                "Please enter a valid day and temperatures between 1-50",
                Toast.LENGTH_SHORT
            ).show()
            return


            minTemps[dayIndex] = minTemp
            maxTemps[dayIndex] = maxTemp

            val averageTemp = ((minTemp ?: 0) + (maxTemp ?: 0)) / 2
            val weatherCondition = when (averageTemp) {
                in 0..10 -> "Cold"
                in 11..20 -> "Cloudy"
                in 21..30 -> "Partly Cloudy"
                else -> "Sunny"
            }

            conditions[dayIndex] = weatherCondition

            resultId.text = "$day: Average Temperature = $averageTemp°C, Weather: $weatherCondition"
        }

     fun clearData() {
        dayInput.text.clear()
        minNo.text.clear()
        maxNo.text.clear()
        resultId.text = ""
    }

        detailScreen.setOnClickListener {
            val intent = Intent(this@MainScreen, DetailView::class.java)
            intent.putExtra("daysOfWeek", daysOfWeek)
            intent.putExtra("minTemps", minTemps.map { it ?: 0 }.toIntArray())
            intent.putExtra("maxTemps", maxTemps.map { it ?: 0 }.toIntArray())
            intent.putExtra("conditions", conditions.map { it ?: "Unknown" }.toTypedArray())
            startActivity(intent)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
