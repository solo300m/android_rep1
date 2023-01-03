package ru.netology.nmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val published: String,
    var content: String,
    var likeByMe: Boolean,
    var likes: ThousandView,
    var share: ThousandView,
    var view: ThousandView
)