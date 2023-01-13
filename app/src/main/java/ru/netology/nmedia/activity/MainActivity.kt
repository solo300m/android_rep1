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
                    /*val intentRed = Intent(this@MainActivity,ActivityEditPost::class.java).apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    startActivity(intentRed)*/
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
           /* with(binding.content){
                requestFocus()
                setText(post.content)
                binding.undo.visibility = View.VISIBLE
            }*/
        }

       // binding.save.setOnClickListener{
            /*with(binding.content){
                if(text.isNullOrBlank()){
                    Toast.makeText(
                        this@MainActivity,
                        "Content can't be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.undo.visibility = View.INVISIBLE
            }*/
        //}

        /*binding.undo.setOnClickListener {
            with(binding.content){
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.undo.visibility = View.INVISIBLE
            }
        }*/
        val newPostLauncher = registerForActivityResult(NewPostResultContract()){
            result ->
            result ?:return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        binding.fab.setOnClickListener{
            newPostLauncher.launch()
        }
            /*Закоментированный текс сохранен для последующего изучения и отработки на других проектах*/
            /*Для обучения эволюции проекта*/
            /*binding.container.removeAllViews()
            posts.map { post ->
                CartPostBinding.inflate(layoutInflater, binding.container, true).apply {
                    greetings.text = post.author
                    published.text = post.published
                    content.text = post.content
                    likeCount.text = post.likes.toString()
                    shareCount.text = post.share.toString()
                    viewsCount.text = post.view.toString()
                    like.setImageResource(
                        if(post.likeByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                    )
                    like.setOnClickListener{
                        viewModel.likeById(post.id)
                    }
                    sharesimbol.setOnClickListener {
                        post.share++
                        shareCount.text = post.share.toString()
                    }

                    views.setOnClickListener {
                        post.view++
                        viewsCount.text = post.view.toString()
                    }
                }.root*/

            /*with(binding) {
                greetings.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = post.likes.toString()
                shareCount.text = post.share.toString()
                viewsCount.text = post.view.toString()
                //testTest?.text = test.toString()

                if (post.likeByMe) {
                    like.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
                like.setOnClickListener {
                    post.likeByMe = !post.likeByMe
                    if(post.likeByMe) post.likes++ else post.likes--
                    like.setImageResource(if(post.likeByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
                    likeCount.text = post.likes.toString()
                }

                sharesimbol.setOnClickListener {
                    post.share++
                    shareCount.text = post.share.toString()
                }

                views.setOnClickListener {
                    post.view++
                    viewsCount.text = post.view.toString()
                }

            }*/
        }
        /*val post = Post(
            1,
            "Нетология. Университет интернет-профессий будущего",
            "21 мая в 18:36",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с " +
                    "интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, " +
                    "аналитике и управлению. Мы растём сами и помогаем расти студентам: " +
                    "от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, " +
                    "что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. " +
                    "Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            false,
            ThousandView(10),
            ThousandView(999),
        ThousandView(15000000)
        )
        //val test = ThousandView(12)

        with(binding){
            greetings.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = post.likes.toString()
            shareCount.text = post.share.toString()
            viewsCount.text = post.view.toString()
            //testTest?.text = test.toString()

            if(post.likeByMe){
                like.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            like.setOnClickListener {
                post.likeByMe = !post.likeByMe
                if(post.likeByMe) post.likes++ else post.likes--
                like.setImageResource(if(post.likeByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
                likeCount.text = post.likes.toString()
            }

            sharesimbol.setOnClickListener {
                post.share++
                shareCount.text = post.share.toString()
            }

            views.setOnClickListener {
                post.view++
                viewsCount.text = post.view.toString()
            }

        }*/

    //}
}