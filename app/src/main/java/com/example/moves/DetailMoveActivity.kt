package com.example.moves

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_move.*

class DetailMoveActivity : AppCompatActivity() {

    private val detailMoveViewModel by lazy { ViewModelProvider(this)[DetailMoveViewModel::class.java] }
    private val trailerRAdapter by lazy { TrailerRAdapter() }
    private val reviewRAdapter by lazy { ReviewRAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_move)

        val movie: Movie = intent.getSerializableExtra("Movie") as Movie

        Glide.with(this)
            .load(movie.poster.url)
            .into(posterImageView)

        titleTextView.text = movie.name

        yearTextView.text = movie.year.toString()

        descriptionTextView.text = movie.description

        detailMoveViewModel.movieInfoLoad(movie.id)

        trailerButtonRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        trailerButtonRecyclerView.adapter = trailerRAdapter

        reviewRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        reviewRecyclerView.adapter = reviewRAdapter

        detailMoveViewModel.trailers.observe(this) {
            trailerRAdapter.setTrailer(it)
        }

        detailMoveViewModel.reviews.observe(this) {
            reviewRAdapter.setReviewList(it)
        }

        trailerRAdapter.setOnClickTrailerListener(object : TrailerRAdapter.OnClickTrailerListener {
            override fun onclick(trailer: Trailer) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailer.url)
                startActivity(intent)
            }
        })

        val starOff = ContextCompat.getDrawable(this, android.R.drawable.star_big_off)
        val starOn = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_on)


        detailMoveViewModel.getFavoriteMovie(movie.id).observe(this) {
            if (it == null) {
                favoriteImageView.setImageDrawable(starOff)
                favoriteImageView.setOnClickListener {
                    detailMoveViewModel.insertMovie(movie)
                }
            } else {
                favoriteImageView.setImageDrawable(starOn)
                favoriteImageView.setOnClickListener {
                    detailMoveViewModel.removeMovie(movie.id)
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailMoveActivity::class.java)
            intent.putExtra("Movie", movie)
            return intent
        }
    }
}