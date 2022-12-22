package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CartPostBinding
import ru.netology.nmedia.dto.Post


typealias OnLikeListener = (post:Post) -> Unit

class PostAdapter (private val onLikeListener: OnLikeListener):
    ListAdapter<Post,PostViewHolder>(PostDiffCallback()){
    /*var list = emptyList<Post>()
    set(value){
        field = value
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CartPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)/*list[position]*/
        holder.bind(post)
    }

    /*override fun getItemCount(): Int {
        return list.size
    }*/

}

class PostViewHolder (
    private val binding: CartPostBinding,
    private val onLikeListener: OnLikeListener /* = (post: ru.netology.nmedia.dto.Post) -> kotlin.Unit */
        ):RecyclerView.ViewHolder(binding.root){
            fun bind(post: Post){
                binding.apply {
                    greetings.text = post.author
                    published.text = post.published
                    content.text = post.content
                    likeCount.text = post.likes.toString()
                    shareCount.text = post.share.toString()
                    viewsCount.text = post.view.toString()
                    if(post.likeByMe){
                        like.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }
                    else{
                        like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    }
                    like.setOnClickListener {
                        onLikeListener(post)
                    }
                    sharesimbol.setOnClickListener {
                        post.share++
                        shareCount.text = post.share.toString()
                    }

                    views.setOnClickListener {
                        post.view++
                        viewsCount.text = post.view.toString()
                    }
                }
            }

}

class PostDiffCallback : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}
