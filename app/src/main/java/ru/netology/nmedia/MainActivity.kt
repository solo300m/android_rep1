package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.ThousandView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
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

        }

    }
}