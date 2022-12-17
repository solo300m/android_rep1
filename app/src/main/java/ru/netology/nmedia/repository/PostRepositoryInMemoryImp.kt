package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.ThousandView
import ru.netology.nmedia.presentation.PostRepository

class PostRepositoryInMemoryImp : PostRepository {

    private var post = Post(
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

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> =data

    override fun like() {
        post = post.copy(likeByMe = !post.likeByMe)
        data.value = post
    }
}