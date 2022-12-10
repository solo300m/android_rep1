package ru.netology.nmedia.dto

data class Post (
    val id: Int,
    val author: String,
    val published: String,
    val content: String,
    var likeByMe: Boolean,
    var likes: ThousandView,
    var share: ThousandView,
    var view: ThousandView
)