package ru.netology.nmedia.presentation

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepositoryInMemoryImp

class PostViewModel:ViewModel() {
    private val repository:PostRepository = PostRepositoryInMemoryImp()
    val data = repository.getAll()
    fun likeById(id:Long) = repository.likeById(id)
}