package com.example.moves.presentation.screens.detail

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
import com.example.moves.R
import com.example.moves.domain.model.Movie
import com.example.moves.presentation.adapters.review.ReviewRAdapter
import com.example.moves.presentation.adapters.trailer.TrailerRAdapter
import com.example.moves.presentation.di.MovieApplication
import com.example.moves.presentation.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_move.*
import javax.inject.Inject

class DetailMoveActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MovieApplication).component
    }

    private val movie by lazy { intent.getSerializableExtra("Movie") as Movie }
    private val detailMoveViewModel by lazy { ViewModelProvider(this, viewModelFactory)[DetailMoveViewModel::class.java] }
    private val trailerRAdapter by lazy { TrailerRAdapter() }
    private val reviewRAdapter by lazy { ReviewRAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_move)
        setMovieInfo()
        observer()
        setTrailerRecyclerView()
        setReviewRecyclerView()
    }

    private fun setMovieInfo() {
        Glide.with(this)
            .load(movie.poster.url)
            .into(posterImageView)

        titleTextView.text = movie.name

        yearTextView.text = movie.year.toString()

        descriptionTextView.text = movie.description

        detailMoveViewModel.movieInfoLoad(movie.id)
    }

    private fun observer() {
        detailMoveViewModel.trailers.observe(this) {
            trailerRAdapter.submitList(it)
        }

        detailMoveViewModel.reviews.observe(this) {
            reviewRAdapter.submitList(it)
        }

        detailMoveViewModel.getFavoriteMovie(movie.id).observe(this) {
            if (it == null) {
                val starOff = ContextCompat.getDrawable(this, android.R.drawable.star_big_off)
                favoriteImageView.setImageDrawable(starOff)
                favoriteImageView.setOnClickListener {
                    detailMoveViewModel.insertMovie(movie)
                }
            } else {
                val starOn = ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_on)
                favoriteImageView.setImageDrawable(starOn)
                favoriteImageView.setOnClickListener {
                    detailMoveViewModel.removeMovie(movie.id)
                }
            }
        }
    }

    private fun setReviewRecyclerView() {
        reviewRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        reviewRecyclerView.adapter = reviewRAdapter
    }

    private fun setTrailerRecyclerView() {
        trailerButtonRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        trailerButtonRecyclerView.adapter = trailerRAdapter

        trailerRAdapter.trailerClickListener = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it.url)
            startActivity(intent)
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