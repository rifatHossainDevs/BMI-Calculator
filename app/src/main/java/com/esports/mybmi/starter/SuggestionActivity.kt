package com.esports.mybmi.starter

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.esports.mybmi.Variables
import com.esports.mybmi.WebViewActivity
import com.esports.mybmi.data.LocalDatabase
import com.esports.mybmi.data.Suggestion
import com.esports.mybmi.databinding.ActivitySuggestionBinding

@Suppress("IMPLICIT_CAST_TO_ANY")
class SuggestionActivity : AppCompatActivity() {
    lateinit var binding: ActivitySuggestionBinding
    lateinit var suggestion: Suggestion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
enableEdgeToEdge()
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.getStringExtra("name")
        suggestion = when (intent) {
            "under" -> {
                LocalDatabase.underWeight
            }

            "healthy" -> {
                LocalDatabase.healthy
            }

            "over" -> {
                LocalDatabase.overWeight
            }

            "obese" -> {
                LocalDatabase.obese
            }

            "severely" -> {
                LocalDatabase.SeverelyObese
            }

            else -> {
                LocalDatabase.nothing
            }

        }

        binding.apply {
            categoryTv.text = suggestion.category
            goalTv.text = suggestion.goal
            mealPlanTv.text = suggestion.mealPlan
            exerciseTv.text = suggestion.exercise
            resourceTv.setOnClickListener {
                val resourceIntent = Intent(this@SuggestionActivity, WebViewActivity::class.java)
                resourceIntent.putExtra(
                    Variables.LINK,
                    "https://www.who.int/europe/news-room/fact-sheets/item/a-healthy-lifestyle---who-recommendations"
                )
                startActivity(resourceIntent)
            }
        }

    }
}