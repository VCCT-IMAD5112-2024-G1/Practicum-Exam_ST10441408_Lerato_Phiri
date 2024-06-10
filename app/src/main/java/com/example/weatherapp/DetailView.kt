package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class DetailView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_view)

        val detailTextView = findViewById<TextView>(R.id.detailTextView)


        val minTemps = intent.getIntegerArrayListExtra("minTemps")
        val maxTemps = intent.getIntegerArrayListExtra("maxTemps")

        val details = StringBuilder()
        val daysOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        for (i in daysOfWeek.indices) {
            details.append("${daysOfWeek[i]}: Min = ${minTemps?.get(i)}, Max = ${maxTemps?.get(i)}\n")
        }

        detailTextView.text = details.toString()



        val backToMain: Button = findViewById(R.id.backToMain)

        // Code to change from DetailView to MainActivity
        backToMain.setOnClickListener {
            val intent = Intent(this@DetailView, MainActivity::class.java)
            startActivity(intent)
        }

        val daysOfWeek = intent.getStringArrayExtra("daysOfWeek") ?: arrayOf()
        val minTemps = intent.getIntArrayExtra("minTemps") ?: intArrayOf()
        val maxTemps = intent.getIntArrayExtra("maxTemps") ?: intArrayOf()
        val conditions = intent.getStringArrayExtra("conditions") ?: arrayOf()

        val detailsTextView: TextView = findViewById(R.id.detailsTextView)
        val backToMainButton: Button = findViewById(R.id.backToMain)
        val details = StringBuilder()
        for (i in daysOfWeek.indices) {
            details.append("${daysOfWeek[i]}: Min Temp = ${minTemps[i]}°C, Max Temp = ${maxTemps[i]}°C, Weather = ${conditions[i]}\n")
        }
        detailsTextView.text = details.toString()

        backToMainButton.setOnClickListener {
            val intent = Intent(this@DetailView, MainActivity::class.java)
            startActivity(intent)
    }
}



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
