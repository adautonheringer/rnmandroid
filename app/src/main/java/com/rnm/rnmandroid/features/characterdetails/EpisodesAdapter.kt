package com.rnm.rnmandroid.features.characterdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rnm.rnmandroid.databinding.EpisodeHolderBinding
import com.rnm.rnmandroid.features.characterdetails.model.Episode

class EpisodesAdapter :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {

    private var episodes = listOf<Episode>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            EpisodeHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    fun updateList(episodes: List<Episode>) {
        if (episodes != this.episodes) {
            this.episodes = episodes
            notifyDataSetChanged()
        }
    }

    inner class EpisodesViewHolder(
        val binding: EpisodeHolderBinding,
    ) : ViewHolder(binding.root) {

        fun bind(episode: Episode) {
            binding.episode = episode
        }
    }
}