package com.example.moves.presentation.adapters.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moves.R
import com.example.moves.domain.model.Trailer

class TrailerRAdapter : ListAdapter<Trailer, TrailerViewHolder>(TrailerDiffCallback()) {

    var trailerClickListener: ((trailer: Trailer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.trailer_item,
            parent,
            false
        )
        return TrailerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = getItem(position)

        holder.trailerTextView.text = trailer.name

        holder.trailerCardView.setOnClickListener {
            trailerClickListener?.invoke(trailer)
        }
    }
}