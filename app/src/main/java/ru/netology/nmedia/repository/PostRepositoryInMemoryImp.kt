package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.ThousandView
import ru.netology.nmedia.presentation.PostRepository

class PostRepositoryInMemoryImp : PostRepository {

    private var posts = listOf(
        Post(
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
        ),
        Post(
            2,
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
            ThousandView(15)
        ),
        Post(
            3,
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
            ThousandView(10)
        ),
        Post(
            4,
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
            ThousandView(10)
        ),
        Post(
            5,
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
            ThousandView(10)
        ),
        Post(
            6,
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
            ThousandView(10)
        )
    )
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
}