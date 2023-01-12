package ru.netology.nmedia.dto

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.activity.EditPost
import ru.netology.nmedia.activity.NewPostActivity
import java.util.*

class EditPostResultContract:ActivityResultContract<Post,Post?>() {

    var inputOBJ:Post? = null

    override fun createIntent(context: Context, input: Post): Intent {
        inputOBJ = input
        val intent = Intent(context, EditPost::class.java).apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, input.content)
            type = "text/plain"
        }
        return intent
    }
    override fun parseResult(resultCode: Int, intent: Intent?): Post? {
        if(resultCode == Activity.RESULT_OK){
            val str = intent?.getStringExtra(Intent.EXTRA_TEXT)
            val rezPost = inputOBJ?.copy(content = str.toString())
            return rezPost
        }else{
            return null
        }
    }

}