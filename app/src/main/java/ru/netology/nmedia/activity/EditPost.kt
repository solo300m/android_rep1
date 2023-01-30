package ru.netology.nmedia.activity

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import ru.netology.nmedia.databinding.FragmentEditPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.presentation.PostViewModel
import ru.netology.nmedia.util.AndroidUtils

class EditPost : Fragment() {

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    var id:Long = 0L
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditPostBinding.inflate(
            inflater,
            container,
            false
        )
        arguments?.let{
            var text = it.getString("content")
            id = it.getLong("id")
            binding.edited.text = Editable.Factory.getInstance().newEditable(text)

        }

        binding.ok.setOnClickListener {
            /*val intent = Intent(this,FeedFragment::class.java)
            if(binding.edited.text.isNullOrBlank()){
                setResult(Activity.RESULT_CANCELED,intent)
            }else{
                val editContent = binding.edited.text.toString()
                //intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,editContent)
                setResult(Activity.RESULT_OK,intent)
            }
            finish()*/
            viewModel.changeContent(binding.edited.text.toString())

            //val post: Post? = viewModel.getPostBiId(id)
            /*if(post != null)
                viewModel.edit(post)*/
            viewModel.save(id)
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        return binding.root
    }
}