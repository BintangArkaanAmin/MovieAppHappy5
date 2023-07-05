package com.example.testmovieapphappy5.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testmovieapphappy5.R
import com.example.testmovieapphappy5.adapter.CastAdapter
import com.example.testmovieapphappy5.adapter.MovieAdapter
import com.example.testmovieapphappy5.api.Server
import com.example.testmovieapphappy5.data.detail.DetailMovieResponse
import com.example.testmovieapphappy5.data.list.ResultsItem
import com.example.testmovieapphappy5.databinding.ActivityDetailBinding
import com.example.testmovieapphappy5.di.CastRepositoryInject
import com.example.testmovieapphappy5.di.MovieRepositoryInject
import com.example.testmovieapphappy5.presenter.cast.CastContract
import com.example.testmovieapphappy5.presenter.cast.CastPresenter
import com.example.testmovieapphappy5.presenter.movie.MovieContract
import com.example.testmovieapphappy5.presenter.movie.MoviePresenter
import com.example.testmovieapphappy5.repository.cast.CastRepository

class DetailActivity : AppCompatActivity(),MovieContract.movieView, CastContract.castView {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var moviePresenter: MoviePresenter
    private lateinit var castPresenter: CastPresenter
    private var castList: ArrayList<String> = ArrayList()
    private lateinit var castAdapter: CastAdapter
    private lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
    }

    private fun setView() {
        id = intent.getStringExtra("id").toString()

        castAdapter = CastAdapter(castList)
        binding.rvDetail.layoutManager = LinearLayoutManager(this)
        binding.rvDetail.adapter = castAdapter


        moviePresenter = MoviePresenter(MovieRepositoryInject.provideTo(this))
        moviePresenter.onAttachView(this)
        moviePresenter.getDetailMovie(id + Server.URL_DETAIL_MOVIE)

        castPresenter = CastPresenter(CastRepositoryInject.provideTO(this))
        castPresenter.onAttachView(this)
        castPresenter.getCast(id + Server.URL_CAST_MOVIE,6)
    }

    override fun onSuccessDetailMovie(detailMovieResponse: DetailMovieResponse, msg: String) {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + detailMovieResponse.posterPath)
            .into(binding.ivDetail)
        binding.tvDetailTitle.text = detailMovieResponse.title
        binding.tvDetailDesc.text = detailMovieResponse.overview
        binding.tvDetailRelease.text = detailMovieResponse.releaseDate


    }

    override fun onErrorDetailMovie(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessCast(castList: ArrayList<String>, msg: String) {
        castAdapter.delete()
        this.castList.clear()
        this.castList.addAll(castList)
        castAdapter.notifyDataSetChanged()
    }

    override fun onErrorCast(msg: String) {
    }

    override fun onSuccessListMovie(movieList: List<ResultsItem>, msg: String) {

    }

    override fun onErrorListMovie(msg: String) {
    }

}