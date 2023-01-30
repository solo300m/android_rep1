package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityAppBinding.inflate
import ru.netology.nmedia.databinding.FragmentViewPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.presentation.PostViewModel

class ViewPostFragment:Fragment() {
    private val viewModel:PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    var idPost:Long = 0L
    @RequiresApi(33)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPostBinding.inflate(
            inflater,
            container,
            false
        )


        binding.greetings.text = arguments?.getString("title")
        binding.content.text = viewModel.selected.value?.content
        //binding.content.text = arguments?.getString("content")
        binding.published.text = arguments?.getString("published")
        idPost = arguments?.getLong("id")!!
        arguments?.let {binding.iconAvatar.setImageResource(it.getInt("avatar"))}

        binding.ok.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.editPost.setOnClickListener{

            val post: Post? = viewModel.getPostBiId(idPost)
            val bundle = Bundle()
            if(post != null){
                bundle.putString("content",post.content)
                bundle.putLong("id",post.id)
            }
            findNavController().navigate(R.id.action_viewPostFragment_to_editPost,bundle)
        }

        binding.deletePost.setOnClickListener {
            viewModel.removeById(idPost)
            findNavController().navigateUp()
        }
        return binding.root
    }
}