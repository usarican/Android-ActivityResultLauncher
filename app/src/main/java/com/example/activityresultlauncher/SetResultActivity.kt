package com.example.activityresultlauncher

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activityresultlauncher.databinding.ActivitySetResultBinding

class SetResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetResultBinding
    private var informationText : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        bundle?.let { informationText = it.getString(GetResultActivity.ACTION_STRING) }
        initListener()
        initUI()
    }

    private fun initUI() {
        binding.getInformationTextView.text = informationText
    }

    private fun initListener() {
        binding.apply {
            resultButton.setOnClickListener {
                val text = resultTextView.editableText.toString()
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(RESULT_STRING,text)
                })
                finish()
            }
        }
    }

    companion object {
        const val RESULT_STRING = "ResultString"
    }
}