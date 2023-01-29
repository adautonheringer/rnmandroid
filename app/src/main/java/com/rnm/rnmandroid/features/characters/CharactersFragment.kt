package com.rnm.rnmandroid.features.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.MaterialElevationScale
import com.rnm.rnmandroid.MainViewModel
import com.rnm.rnmandroid.R
import com.rnm.rnmandroid.databinding.CharacterHolderBinding
import com.rnm.rnmandroid.databinding.CharactersFragmentBinding
import com.rnm.rnmandroid.features.characterdetails.CharacterDetailsFragmentArgs
import com.rnm.rnmandroid.features.characters.model.Character
import kotlinx.coroutines.launch

class CharactersFragment : Fragment(), CharactersAdapterListener {

    private val charactersAdapter = CharactersAdapter(this)
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CharactersFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.recyclerView.apply {
            adapter = charactersAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    Log.d("testando", "state: ${it}")
                    charactersAdapter.updateList(it.characters)
                    if (it.character != null) {

                        exitTransition = MaterialElevationScale(false).apply {
                            duration = 300L
                        }
                        reenterTransition = MaterialElevationScale(true).apply {
                            duration = 300L
                        }

                        val extras = FragmentNavigatorExtras(
                            it.sharedView!!.findViewById<View>(R.id.root) to getString(R.string.root_transition_name_detail),
                        )
                        val directions = CharactersFragmentDirections.nextAction(it.character.name)
                        findNavController().navigate(directions, extras)

                    }
                }
            }
        }
        if (charactersAdapter.itemCount == 0) viewModel.getCharacters()
    }


    override fun onCharacterClicked(cardView: View, character: Character) {
        Log.d("testando transition", "image_container transitionName: ${cardView.findViewById<View>(R.id.image_container).transitionName}")
        viewModel.goToDetails(cardView, character)
    }

    override fun getNextCharacters(page: Int?) {
        viewModel.getCharacters(page)
    }
}