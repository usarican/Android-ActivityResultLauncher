package com.example.activityresultlauncher

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import com.example.activityresultlauncher.databinding.ActivityMainBinding

class GetResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private val getResultForActivityResult = registerForActivityResult(GetResultActivityContract()) { result ->
        if (result != null) {
                binding.resultTextView.text = result
            }
        }

    private fun initListener() {
        binding.apply {
            resultButton.setOnClickListener {
                getResultForActivityResult.launch("Come From Get Result Activity")
            }
        }
    }

    companion object {
        const val ACTION_STRING = "ActionString"
    }
}

class GetResultActivityContract : ActivityResultContract<String,String?>(){
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context,SetResultActivity::class.java).apply {
            putExtra(GetResultActivity.ACTION_STRING,input)
        }
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return if (resultCode == Activity.RESULT_OK){
            intent?.getStringExtra(SetResultActivity.RESULT_STRING)
        } else null
    }

}