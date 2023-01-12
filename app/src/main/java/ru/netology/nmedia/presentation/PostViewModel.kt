package ru.netology.nmedia.presentation

import androidx.annotation.Nullable
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
    strVideo = "",
    likes = ThousandView(0),
    share = ThousandView(0),
    view = ThousandView(0)
)

class PostViewModel:ViewModel() {
    private val repository:PostRepository = PostRepositoryInMemoryImp()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    var rmData:MutableList<Post> = mutableListOf()

    fun videoById(post: Post){
        repository.videoById(post)
    }

    fun save(post:Post?){
        if(post == null) {
            edited.value?.let {
                repository.save(it)
            }
        }
        else
            repository.save(post)
        edited.value = empty
    }
    fun save(){
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun reEdit(post: Post){
        repository.reEdit(post, rmData)
    }
    fun edit(post: Post){
        //edited.value = post
        if(rmData.isEmpty())
            rmData.add(post)
        else{
            for(i:Int in rmData.indices){
                if(rmData[i].id == post.id) {
                    rmData[i].content = post.content
                    return
                }
            }
            rmData.add(post)
        }
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
    //fun save(post: Post) = repository.save(post)
}