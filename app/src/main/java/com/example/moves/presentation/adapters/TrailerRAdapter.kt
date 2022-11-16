package com.example.moves.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moves.R
import com.example.moves.domain.model.Trailer
import kotlinx.android.synthetic.main.trailer_item.view.*

class TrailerRAdapter : RecyclerView.Adapter<TrailerRAdapter.TrailerViewHolder>() {

    lateinit var setOnClickTrailerListener: OnClickTrailerListener

    fun setOnClickTrailerListener(setOnClickTrailerListener: OnClickTrailerListener) {
        this.setOnClickTrailerListener = setOnClickTrailerListener
    }

    var trailers: List<Trailer> = ArrayList<Trailer>()

    fun setTrailer(trailers: List<Trailer>) {
        this.trailers = trailers
        notifyDataSetChanged()
        Log.d("listTrailers", "${trailers.size}")
    }

    class TrailerViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trailerTextView = itemView.trailerTextView

        val trailerCardView = itemView.trailerCardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.trailer_item,
            parent,
            false
        )
        return TrailerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = trailers[position]

        holder.trailerTextView.text = trailer.name

        holder.trailerCardView.setOnClickListener {
            if (setOnClickTrailerListener != null) setOnClickTrailerListener.onclick(trailer)
        }
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    interface OnClickTrailerListener {
        fun onclick(trailer: Trailer)
    }
}