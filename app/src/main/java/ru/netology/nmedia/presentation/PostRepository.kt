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
    fun save(post: Post)
    fun reEdit(post:Post, rmEdit:MutableList<Post>)
}
