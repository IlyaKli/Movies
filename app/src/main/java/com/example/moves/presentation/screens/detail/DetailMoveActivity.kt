package com.example.moves.presentation.screens.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_detail_move.*
import javax.inject.Inject

class DetailMoveActivity : AppCompatActivity() {

    private val movie by lazy { intent.getSerializableExtra("Movie") as Movie }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_move)
        launchFragment(DetailMovieFragment.newInstance(movie))
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.detailActivityDetailFragmentContainerView, fragment)
            .commit()
    }

    companion object {
        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailMoveActivity::class.java)
            intent.putExtra("Movie", movie)
            return intent
        }
    }
}