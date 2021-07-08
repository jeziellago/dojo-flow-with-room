package dev.dojo.jokesflow.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.dojo.jokesflow.databinding.JokeItemBinding
import dev.dojo.jokesflow.domain.Joke


class JokesAdapter(
    private val onJokeClick: (Joke) -> Unit
) : ListAdapter<Joke, JokesAdapter.ViewHolder>(JokeDiffUtil()) {

    inner class ViewHolder(
        private val binding: JokeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onJokeClick(getItem(adapterPosition)) }
        }

        fun bind(joke: Joke) {
            binding.txtJokeContent.text = joke.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesAdapter.ViewHolder {
        return ViewHolder(
            JokeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JokesAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class JokeDiffUtil : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem == newItem
        }

    }
}