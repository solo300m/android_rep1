package ru.netology.nmedia.dto

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.activity.NewPostActivity

class NewPostResultContract:ActivityResultContract<Unit, String?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, NewPostActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if(resultCode == Activity.RESULT_OK){
            return  intent?.getStringExtra(Intent.EXTRA_TEXT)
        }else{
            return null
        }
    }
}