package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter

import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.dto.EditPostResultContract
import ru.netology.nmedia.dto.NewPostResultContract
import ru.netology.nmedia.dto.Post

import ru.netology.nmedia.presentation.PostViewModel

class FeedFragment : Fragment() {
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(
            inflater,
            container,
            false
        )

        val intentR = registerForActivityResult(EditPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result.content)
            viewModel.save(result)
        }
        val adapter = PostAdapter(
            object : OnInteractionListener {
                override fun onVideo(post: Post) {
                    viewModel.videoById(post)
                    if (post.strVideo.isNullOrBlank()) {
                       /* Toast.makeText(
                            this@FeedFragment,
                            "Content is empty...",
                            Toast.LENGTH_SHORT
                        ).show()*/
                        Toast.makeText(context,"message...",Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.strVideo))
                        startActivity(intent)
                    }
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.id)

                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val shareIntent =
                        Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(shareIntent)
                }

                override fun onView(post: Post) {
                    viewModel.viewById(post.id)
                    viewModel.selected.value = post
                    if(!post.content.isNullOrBlank()){
                        val bandle = Bundle()
                        bandle.putString("content", post.content)
                        bandle.putString("title",post.author)
                        bandle.putString("published",post.published)
                        bandle.putInt("avatar",R.drawable.ic_netology)
                        bandle.putLong("id",post.id)
                        findNavController().navigate(R.id.action_feedFragment_to_viewPostFragment, bandle)
                    }
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onEdit(post: Post) {
                    val str = post.content
                    viewModel.edit(post, str)
                    val bundle = Bundle()
                    bundle.putString("content",post.content)
                    bundle.putLong("id",post.id)
                    findNavController().navigate(R.id.action_feedFragment_to_editPost2, bundle)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onSave(post: Post) {
                    viewModel.save()
                }

                override fun onReEdit(post: Post) {
                    viewModel.reEdit(post)
                }
            })
        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)/*adapter.list = posts*/
        }
        viewModel.edited.observe(viewLifecycleOwner) { post ->
            if (post.id == 0L) {
                return@observe
            }

        }
        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        binding.fab.setOnClickListener {
           // newPostLauncher.launch()
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }
        return binding.root
    }
}

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intentR = registerForActivityResult(EditPostResultContract()){
                result ->
            result ?:return@registerForActivityResult
            viewModel.changeContent(result.content)
            viewModel.save(result)
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostAdapter (
            object : OnInteractionListener{
                override fun onVideo(post: Post) {
                    viewModel.videoById(post)
                    if(post.strVideo.isNullOrBlank()) {
                        Toast.makeText(
                            this@FeedFragment,
                            "Content is empty..." ,
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.strVideo))
                        startActivity(intent)
                    }
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.id)

                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(intent,getString(R.string.chooser_share_post))
                    startActivity(shareIntent)
                }

                override fun onView(post: Post) {
                    viewModel.viewById(post.id)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onEdit(post: Post) {

                    viewModel.edit(post)

                    intentR.launch(post)

                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onSave(post: Post) {
                    viewModel.save()
                }

                override fun onReEdit(post: Post) {
                    viewModel.reEdit(post)
                }
            })
        binding.list.adapter = adapter
        viewModel.data.observe(this){
            posts-> adapter.submitList(posts)/*adapter.list = posts*/
            }
        viewModel.edited.observe(this){
            post ->
            if(post.id == 0L){
                return@observe
            }

        }


        val newPostLauncher = registerForActivityResult(NewPostResultContract()){
            result ->
            result ?:return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        binding.fab.setOnClickListener{
            newPostLauncher.launch()
        }

    }*/

