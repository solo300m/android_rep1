package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityEditPostBinding
import ru.netology.nmedia.databinding.ActivityIntentHendlerBinding

class EditPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let{
            if(it.action != Intent.ACTION_SEND){
                return@let
            }

            var text = it.getStringExtra(Intent.EXTRA_TEXT)

            binding.edited.text = Editable.Factory.getInstance().newEditable(text)
        }
        binding.ok.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            if(binding.edited.text.isNullOrBlank()){
                setResult(Activity.RESULT_CANCELED,intent)
            }else{
                val editContent = binding.edited.text.toString()
                //intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,editContent)
                setResult(Activity.RESULT_OK,intent)
            }
            finish()
        }
    }
}