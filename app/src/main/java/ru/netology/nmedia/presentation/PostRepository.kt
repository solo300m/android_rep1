package ru.netology.nmedia.presentation

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun get():LiveData<Post>
    fun like()
}