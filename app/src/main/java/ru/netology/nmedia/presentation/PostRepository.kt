package ru.netology.nmedia.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll():LiveData<List<Post>>
    fun likeById(id:Long)
    fun shareById(id:Long)
    fun viewById(id: Long)
    fun removeById(id: Long)
    fun save(post: Post, oldPost: Post)
    fun edit(post: Post, str:String)
    fun reEdit(post:Post)
    fun videoById(post: Post)
    fun getPost(id: Long): Post?
    fun clone(post:Post):Post?
}
