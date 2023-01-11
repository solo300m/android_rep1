package ru.netology.nmedia.adapter

import android.system.Os.remove
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CartPostBinding
import ru.netology.nmedia.dto.Post


//typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit
typealias OnViewListener = (post: Post) -> Unit
typealias OnRemoveListener = (post: Post) -> Unit

interface OnInteractionListener {
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onSave(post: Post){}
    fun onReEdit(post: Post){}
    fun onLike(post:Post){}
    fun onShare(post: Post){}
    fun onView(post: Post){}
}

class PostAdapter(
    //private val onLikeListener: OnLikeListener,
    //private val onShareListener: OnShareListener,
    //private val onViewListener: OnViewListener,
    private val onInteractionListener: OnInteractionListener
) :
    ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    /*var list = emptyList<Post>()
    set(value){
        field = value
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CartPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(
            binding,
            onInteractionListener
            /*onLikeListener,*/
            /* onShareListener,
             onViewListener,*/
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)/*list[position]*/
        holder.bind(post)
    }


}

class PostViewHolder(
    private val binding: CartPostBinding,
    //private val onLikeListener: OnLikeListener, /* = (post: ru.netology.nmedia.dto.Post) -> kotlin.Unit */
    //private val onShareListener: OnShareListener, /* = (post: ru.netology.nmedia.dto.Post) -> kotlin.Unit */
    //private val onViewListener: OnViewListener, /* = (post: ru.netology.nmedia.dto.Post) -> kotlin.Unit */
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            greetings.text = post.author
            published.text = post.published
            content.text = post.content
            //likeCount.text = post.likes.toString()
            shareCount.text = post.share.toString()
            viewsCount.text = post.view.toString()
            like.isChecked = post.likeByMe
            like.text = "${post.likes}"

            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            sharesimbol.setOnClickListener {
                onInteractionListener.onShare(post)
                //onShareListener(post)
                /*post.share++*/
                shareCount.text = post.share.toString()
            }

            views.setOnClickListener {
                //onViewListener(post)
                /*post.view++*/
                onInteractionListener.onView(post)
                viewsCount.text = post.view.toString()
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener {
                            item ->
                        when(item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            R.id.reedit -> {
                                onInteractionListener.onReEdit(post)
                                content.text = post.content
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }


        }
    }

}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}
