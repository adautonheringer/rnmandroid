package com.rnm.rnmandroid.features.characterdetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.transition.ArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.rnm.rnmandroid.MainViewModel
import com.rnm.rnmandroid.R
import com.rnm.rnmandroid.databinding.CharacterDetailsFragmentBinding
import kotlinx.coroutines.launch


class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: CharacterDetailsFragmentBinding
    private val episodesAdapter = EpisodesAdapter()
    private lateinit var circularProgressDrawable: CircularProgressDrawable
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.my_nav_host_fragment
            duration = 300L
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.TRANSPARENT)
            setPathMotion(ArcMotion())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailsFragmentBinding.inflate(inflater, container, false)
        circularProgressDrawable = CircularProgressDrawable(binding.image.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CharacterDetailsFragment.viewModel
            drawable = circularProgressDrawable
            character = this@CharacterDetailsFragment.viewModel.mainState.value.character
            recyclerView.apply {
                adapter = episodesAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    episodesAdapter.updateList(it.episodes)
                    if (it.character == null) {
                        findNavController().navigate(R.id.next_action)
                    }
                }
            }
        }
    }
}