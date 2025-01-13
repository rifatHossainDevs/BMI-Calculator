package com.esports.mybmi.starter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.esports.mybmi.databinding.ActivityMainBinding
import java.text.DecimalFormat
import com.esports.mybmi.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var category: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculateBmi.setOnClickListener {
            val height = (binding.height.text.toString()).toDouble()
            val weight = (binding.weight.text.toString()).toDouble()
            resetUI()
            calculate(height, weight)
            binding.btnSeeSuggestion.visibility = View.VISIBLE
        }

        binding.btnSeeSuggestion.setOnClickListener {
            val intent = Intent(this@MainActivity, SuggestionActivity::class.java)
            intent.putExtra("name", category)
            startActivity(intent)
        }

    }



    @SuppressLint("SetTextI18n")
    private fun calculate(height: Double, weight: Double) {
        val result: Double = weight / (height * height)
        val df = DecimalFormat("#.##")
        val formattedNumber: String = df.format(result)
        binding.tvResult.text = formattedNumber

        binding.speedometer.setSpeed(result.toInt(), 2000L)



        when (result.toInt()) {
            in 0..18 -> {

                binding.underweightImageView.setImageResource(R.drawable.baseline_arrow_circle_right_24)
                /*val drawable = ContextCompat.getDrawable(this, R.drawable.baseline_arrow_circle_right_24)
                binding.underweightImageView.setImageDrawable(drawable)*/
                binding.tvUnderWeight.setTextColor(Color.RED)
                Toast.makeText(
                    this@MainActivity,
                    "You are Underweight ${result.toInt()}",
                    Toast.LENGTH_LONG
                ).show()
                category = "under"
            }

            in 24 downTo 19 -> {
                binding.healthyImageView.setImageResource(R.drawable.baseline_arrow_circle_right_24)
                binding.tvHealthy.setTextColor(Color.RED)
                Toast.makeText(
                    this@MainActivity,
                    "You are Healthy ${result.toInt()}",
                    Toast.LENGTH_LONG
                ).show()
                category = "healthy"
            }

            in 29 downTo 25 -> {
                binding.overweightImageView.setImageResource(R.drawable.baseline_arrow_circle_right_24)
                binding.tvOverWeight.setTextColor(Color.RED)
                Toast.makeText(
                    this@MainActivity,
                    "You are Overweight ${result.toInt()}",
                    Toast.LENGTH_LONG
                ).show()
                val category = "over"
            }

            in 40 downTo 30 -> {
                binding.obeseImageView.setImageResource(R.drawable.baseline_arrow_circle_right_24)
                binding.tvObese.setTextColor(Color.RED)
                Toast.makeText(
                    this@MainActivity,
                    "You are Obese ${result.toInt()}",
                    Toast.LENGTH_LONG
                ).show()
                category = "obese"
            }

            else -> {
                binding.severelyImageView.setImageResource(R.drawable.baseline_arrow_circle_right_24)
                binding.tvSeverelyObese.setTextColor(Color.RED)
                Toast.makeText(
                    this@MainActivity,
                    "You are Severely Obese ${result.toInt()}",
                    Toast.LENGTH_LONG
                ).show()
                category = "severely"
            }
        }

    }

    private fun resetUI() {
        binding.apply {
            underweightImageView.setImageResource(R.drawable.arrow_black)
            healthyImageView.setImageResource(R.drawable.arrow_black)
            overweightImageView.setImageResource(R.drawable.arrow_black)
            obeseImageView.setImageResource(R.drawable.arrow_black)
            severelyImageView.setImageResource(R.drawable.arrow_black)

            tvUnderWeight.setTextColor(Color.BLACK)
            tvHealthy.setTextColor(Color.BLACK)
            tvOverWeight.setTextColor(Color.BLACK)
            tvObese.setTextColor(Color.BLACK)
            tvSeverelyObese.setTextColor(Color.BLACK)
        }
    }

}