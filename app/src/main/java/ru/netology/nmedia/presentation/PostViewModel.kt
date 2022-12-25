package ru.netology.nmedia.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.ThousandView
import ru.netology.nmedia.repository.PostRepositoryInMemoryImp
private val empty = Post(
    id=0,
    content = "",
    author = "",
    published = "",
    likeByMe = false,
    likes = ThousandView(0),
    share = ThousandView(0),
    view = ThousandView(0)
)

class PostViewModel:ViewModel() {
    private val repository:PostRepository = PostRepositoryInMemoryImp()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save(){
        edited.value?.let{
            repository.save(it)
        }
        edited.value = empty
    }
    fun edit(post: Post){
        edited.value = post
    }
    fun changeContent(content:String){
        val text = content.trim()
        if(edited.value?.content == text){
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun likeById(id:Long) = repository.likeById(id)
    fun shareById(id:Long) = repository.shareById(id)
    fun viewById(id: Long) = repository.viewById(id)
    fun removeById(id: Long) = repository.removeById(id)
}