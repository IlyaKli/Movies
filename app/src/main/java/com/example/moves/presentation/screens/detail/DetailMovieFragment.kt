package com.example.moves.presentation.screens.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import javax.inject.Inject

class DetailMovieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as MovieApplication).component
    }

    private val detailMoveViewModel by lazy { ViewModelProvider(this, viewModelFactory)[DetailMoveViewModel::class.java] }
    private val trailerRAdapter by lazy { TrailerRAdapter() }
    private val reviewRAdapter by lazy { ReviewRAdapter() }


    private val movie by lazy { requireArguments().getSerializable(ARG_MOVIE) as Movie }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        detailMoveViewModel.trailers.observe(viewLifecycleOwner) {
            trailerRAdapter.submitList(it)
        }

        detailMoveViewModel.reviews.observe(viewLifecycleOwner) {
            reviewRAdapter.submitList(it)
        }

        detailMoveViewModel.getFavoriteMovie(movie.id).observe(viewLifecycleOwner) {
            if (it == null) {
                val starOff = ContextCompat.getDrawable(requireContext(), android.R.drawable.star_big_off)
                favoriteImageView.setImageDrawable(starOff)
                favoriteImageView.setOnClickListener {
                    detailMoveViewModel.insertMovie(movie)
                }
            } else {
                val starOn = ContextCompat.getDrawable(requireContext(), android.R.drawable.btn_star_big_on)
                favoriteImageView.setImageDrawable(starOn)
                favoriteImageView.setOnClickListener {
                    detailMoveViewModel.removeMovie(movie.id)
                }
            }
        }
    }

    private fun setReviewRecyclerView() {
        reviewRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )

        reviewRecyclerView.adapter = reviewRAdapter
    }

    private fun setTrailerRecyclerView() {
        trailerButtonRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
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
        private const val ARG_MOVIE = "movie"

        @JvmStatic
        fun newInstance(movie: Movie) =
            DetailMovieFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOVIE, movie)
                }
            }
    }
}