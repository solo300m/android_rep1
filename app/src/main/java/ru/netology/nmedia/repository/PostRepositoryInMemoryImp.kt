package ru.netology.nmedia.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.ThousandView
import ru.netology.nmedia.presentation.PostRepository

class PostRepositoryInMemoryImp : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
        id=nextId++,
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
        ThousandView(15000000),
            "https://youtu.be/zVt3B427PPU"
        ),
        Post(
            id=nextId++,
            "Нетология. Университет интернет-профессий будущего",
            "20 апреля в 18:36",
            "RecyclerView вводит дополнительный уровень абстракции между Adapterи LayoutManager, " +
                    "чтобы иметь возможность обнаруживать изменения набора данных в пакетах во время расчета макета. " +
                    "Это избавляет LayoutManager от отслеживания изменений адаптера для расчета анимации. " +
                    "Это также помогает повысить производительность, поскольку все привязки представлений выполняются одновременно, " +
                    "и исключаются ненужные привязки. " +
                    "Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            false,
            ThousandView(5),
            ThousandView(5),
            ThousandView(15),
            "https://youtu.be/UEspHbiCkAc"
        ),
        Post(
            id=nextId++,
            "Нетология. Университет интернет-профессий будущего",
            "20 апреля в 18:36",
            "RecyclerView вводит дополнительный уровень абстракции между Adapterи LayoutManager, " +
                    "чтобы иметь возможность обнаруживать изменения набора данных в пакетах во время расчета макета. " +
                    "Это избавляет LayoutManager от отслеживания изменений адаптера для расчета анимации. " +
                    "Это также помогает повысить производительность, поскольку все привязки представлений выполняются одновременно, " +
                    "и исключаются ненужные привязки. " +
                    "Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            false,
            ThousandView(2),
            ThousandView(3),
            ThousandView(10),
            "https://youtu.be/P86JdaapRjo"
        ),
        Post(
            id=nextId++,
            "Нетология. Университет интернет-профессий будущего",
            "20 апреля в 18:36",
            "RecyclerView вводит дополнительный уровень абстракции между Adapterи LayoutManager, " +
                    "чтобы иметь возможность обнаруживать изменения набора данных в пакетах во время расчета макета. " +
                    "Это избавляет LayoutManager от отслеживания изменений адаптера для расчета анимации. " +
                    "Это также помогает повысить производительность, поскольку все привязки представлений выполняются одновременно, " +
                    "и исключаются ненужные привязки. " +
                    "Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            false,
            ThousandView(2),
            ThousandView(3),
            ThousandView(10),
            "https://youtu.be/Ckom3gf57Yw"
        ),
        Post(
            id=nextId++,
            "Нетология. Университет интернет-профессий будущего",
            "20 апреля в 18:36",
            "RecyclerView вводит дополнительный уровень абстракции между Adapterи LayoutManager, " +
                    "чтобы иметь возможность обнаруживать изменения набора данных в пакетах во время расчета макета. " +
                    "Это избавляет LayoutManager от отслеживания изменений адаптера для расчета анимации. " +
                    "Это также помогает повысить производительность, поскольку все привязки представлений выполняются одновременно, " +
                    "и исключаются ненужные привязки. " +
                    "Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            false,
            ThousandView(2),
            ThousandView(3),
            ThousandView(10),
            "https://youtu.be/tAGnKpE4NCI"
        ),
        Post(
            id=nextId++,
            "Нетология. Университет интернет-профессий будущего",
            "20 апреля в 18:36",
            "RecyclerView вводит дополнительный уровень абстракции между Adapterи LayoutManager, " +
                    "чтобы иметь возможность обнаруживать изменения набора данных в пакетах во время расчета макета. " +
                    "Это избавляет LayoutManager от отслеживания изменений адаптера для расчета анимации. " +
                    "Это также помогает повысить производительность, поскольку все привязки представлений выполняются одновременно, " +
                    "и исключаются ненужные привязки. " +
                    "Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            false,
            ThousandView(2),
            ThousandView(3),
            ThousandView(10),
            "https://youtu.be/8LhkyyCvUHk"
        )
    ).reversed()
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id:Long) {
        posts = posts.map {
            if(it.id != id) it
            else {
                if(it.likeByMe){
                    it.likes.number--
                }else{
                    it.likes.number++
                }
                it.copy(likeByMe = !it.likeByMe)

            }
        }
        data.value = posts
    }
    override fun shareById(id: Long){
        for(i in posts.indices){
            if(posts[i].id == id){
                posts[i].share++
                //posts[i] = it.copy()
                //data.value = posts
            }
        }
        data.value = posts
    }

    override fun viewById(id: Long) {
        for(i in posts.indices){
            if(posts[i].id == id) {
                posts[i].view++
            }
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun reEdit(post: Post,rmData:MutableList<Post>) {
        val id:Long = post.id
        val cont:String = post.content
        val rmPost:List<Post> = rmData.filter { it.id == id }
        if(!rmPost.isEmpty()) {
            for (i: Int in posts.indices) {
                if (posts[i].id == id) {
                    posts[i].content = rmPost[0].content
                }
            }
            //posts = posts
        }
        data.value = posts
    }

    override fun save(post: Post) {
        if(post.id == 0L && post.content.isNullOrBlank()){
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likeByMe = false,
                    published = "now",
                    content = "",
                    likes = ThousandView(0),
                    share = ThousandView(0),
                    view = ThousandView(0),
                    strVideo = ""
                )
            )+ posts
            data.value = posts
            return
        }
        else if(post.id == 0L && !post.content.isNullOrBlank()){
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likeByMe = false,
                    published = "now",
                    content = post.content,
                    likes = ThousandView(0),
                    share = ThousandView(0),
                    view = ThousandView(0),
                    strVideo = ""
                )
            )+ posts
            data.value = posts
            return
        }
        posts = posts.map{
            if(it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }
    override fun videoById(post: Post){


    }
}