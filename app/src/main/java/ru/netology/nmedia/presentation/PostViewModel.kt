package ru.netology.nmedia.presentation

import android.view.View
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
    var selected = MutableLiveData<Post>(empty)
    //private var rmData:MutableList<Post> = mutableListOf()

    fun videoById(post: Post){
        repository.videoById(post)
    }

    fun save(post:Post?){
        if(post == null) {
            edited.value?.let {
                repository.save(it,it)
            }
        }
        else
            repository.save(post,post)
        edited.value = empty
    }
    fun save(){
        edited.value?.let {
            repository.save(it, it)
        }
        edited.value = empty
    }
    fun save(id:Long){
        val ps:Post? = getPostBiId(id)
        val oldPs:Post?
        if(ps != null)
            oldPs = repository.clone(ps)//getPostBiId(id)
        else
            oldPs = null
        if (ps != null && oldPs != null){
            ps.content = edited.value!!.content
            repository.save(ps,oldPs)
        }
        /*edited.value?.let {
            repository.getPost(id)?.let { it1 ->
                it1.content = edited.value!!.content.toString()
                repository.save(it1) }
        }*/
        edited.value = empty
    }

    fun reEdit(post: Post){
        repository.reEdit(post)
    }
    fun edit(post: Post, str:String){
        repository.edit(post, str)
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
    fun getPostBiId(id:Long) = repository.getPost(id)
    //fun save(post: Post) = repository.save(post)
}