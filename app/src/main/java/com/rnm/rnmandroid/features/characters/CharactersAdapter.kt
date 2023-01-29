package com.rnm.rnmandroid.features.characters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.rnm.rnmandroid.databinding.CharacterHolderBinding
import com.rnm.rnmandroid.features.characters.model.Character

class CharactersAdapter(private val listener: CharactersAdapterListener) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private var characters = listOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        if (position == itemCount - 7) listener.getNextCharacters((itemCount / 20) + 1)
        holder.bind(characters[position])
    }

    fun updateList(characters: List<Character>) {
        if (this.characters != characters) {
            this.characters = characters
            notifyDataSetChanged()
        }
    }

    inner class CharacterViewHolder(
        private val binding: CharacterHolderBinding,
        listener: CharactersAdapterListener
    ) : ViewHolder(binding.root) {

        private var circularProgressDrawable: CircularProgressDrawable

        init {
            binding.listener = listener
            circularProgressDrawable = CircularProgressDrawable(binding.image.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            }

        }

        fun bind(character: Character) {
            binding.character = character
            binding.drawable = circularProgressDrawable
        }


    }
}