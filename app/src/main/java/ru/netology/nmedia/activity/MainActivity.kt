package ru.netology.nmedia.activity

import android.content.ClipData.newIntent
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityEditPostBinding
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CartPostBinding
import ru.netology.nmedia.dto.EditPostResultContract
import ru.netology.nmedia.dto.NewPostResultContract
import ru.netology.nmedia.dto.Post

import ru.netology.nmedia.presentation.PostViewModel
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.activity.EditPost as ActivityEditPost

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel:PostViewModel by viewModels()

        val intentR = registerForActivityResult(EditPostResultContract()){
                result ->
            result ?:return@registerForActivityResult
            viewModel.changeContent(result.content)
            viewModel.save(result)
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostAdapter (
            /*{viewModel.likeById(it.id)},*/
            /*{viewModel.shareById(it.id)},
            {viewModel.viewById(it.id)},*/
            object : OnInteractionListener{
                override fun onVideo(post: Post) {

                    viewModel.videoById(post)
                    if(post.strVideo.isNullOrBlank()) {
                        Toast.makeText(
                            this@MainActivity,
                            "Content is empty..." ,
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.strVideo))
                        startActivity(intent)
                    }
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.id)

                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(intent,getString(R.string.chooser_share_post))
                    startActivity(shareIntent)
                }

                override fun onView(post: Post) {
                    viewModel.viewById(post.id)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onEdit(post: Post) {

                    viewModel.edit(post)

                    intentR.launch(post)

                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onSave(post: Post) {
                    viewModel.save()
                }

                override fun onReEdit(post: Post) {
                    viewModel.reEdit(post)
                }
            })
        binding.list.adapter = adapter
        viewModel.data.observe(this){
            posts-> adapter.submitList(posts)/*adapter.list = posts*/
            }
        viewModel.edited.observe(this){
            post ->
            if(post.id == 0L){
                return@observe
            }

        }


        val newPostLauncher = registerForActivityResult(NewPostResultContract()){
            result ->
            result ?:return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        binding.fab.setOnClickListener{
            newPostLauncher.launch()
        }

    }

}