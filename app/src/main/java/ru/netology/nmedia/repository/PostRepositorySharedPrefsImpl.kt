package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.ThousandView
import ru.netology.nmedia.presentation.PostRepository

class PostRepositorySharedPrefsImpl(context: Context) : PostRepository {
    private val gson = Gson()
    private val prefs = context.getSharedPreferences("repo",Context.MODE_PRIVATE)
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val key = "posts"
    private var nextId = 1L
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init{
        prefs.getString(key, null)?.let{
            posts = gson.fromJson(it, type)
            data.value = posts
        }
    }
    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
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
        sync()
    }

    override fun shareById(id: Long) {
        for(i in posts.indices){
            if(posts[i].id == id){
                posts[i].share++
            }
        }
        data.value = posts
        sync()
    }

    override fun viewById(id: Long) {
        for(i in posts.indices){
            if(posts[i].id == id) {
                posts[i].view++
            }
        }
        data.value = posts
        sync()
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
        sync()
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
            sync()
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
            sync()
            return
        }
        posts = posts.map{
            if(it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
        sync()
    }

    override fun reEdit(post: Post, rmData: MutableList<Post>) {
        val id:Long = post.id
        //val cont:String = post.content
        val rmPost:List<Post> = rmData.filter { it.id == id }
        if(!rmPost.isEmpty()) {
            for (i: Int in posts.indices) {
                if (posts[i].id == id) {
                    posts[i].content = rmPost[0].content
                }
            }
        }
        data.value = posts
        sync()
    }

    override fun videoById(post: Post) {

    }
    private fun sync() {
        with(prefs.edit()) {
            putString(key, gson.toJson(posts))
            apply()
        }
    }
}
