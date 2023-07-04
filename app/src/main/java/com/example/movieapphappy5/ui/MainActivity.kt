package com.example.testmovieapphappy5.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmovieapphappy5.adapter.MovieAdapter
import com.example.testmovieapphappy5.api.Server
import com.example.testmovieapphappy5.data.detail.DetailMovieResponse
import com.example.testmovieapphappy5.data.list.ResultsItem
import com.example.testmovieapphappy5.databinding.ActivityMainBinding
import com.example.testmovieapphappy5.di.MovieRepositoryInject
import com.example.movieapphappy5.presenter.movie.MovieContract
import com.example.movieapphappy5.presenter.movie.MoviePresenter

class MainActivity : AppCompatActivity(), MovieContract.movieView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviePresenter: MoviePresenter
    private var movieList: ArrayList<ResultsItem> = ArrayList()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
    }

    private fun setView() {
        movieAdapter = MovieAdapter(movieList)
        binding.rvMovieList.layoutManager = LinearLayoutManager(this)
        binding.rvMovieList.adapter = movieAdapter

        moviePresenter = MoviePresenter(MovieRepositoryInject.provideTo(this))
        moviePresenter.onAttachView(this)
        moviePresenter.getListMovie(Server.URL_LIST_MOVIE)
    }

    override fun onSuccessListMovie(movieList: List<ResultsItem>, msg: String) {
        movieAdapter.delete()
        this.movieList.clear()
        this.movieList.addAll(movieList)
        movieAdapter.notifyDataSetChanged()
    }

    override fun onErrorListMovie(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDetailMovie(detailMovieResponse: DetailMovieResponse, msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDetailMovie(msg: String) {
        TODO("Not yet implemented")
    }
}

