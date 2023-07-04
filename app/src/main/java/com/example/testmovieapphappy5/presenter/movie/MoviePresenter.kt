package com.example.testmovieapphappy5.presenter.movie

import com.example.testmovieapphappy5.data.detail.DetailMovieResponse
import com.example.testmovieapphappy5.data.list.ResultsItem
import com.example.testmovieapphappy5.repository.movie.MovieDataResource
import com.example.testmovieapphappy5.repository.movie.MovieRepository

class MoviePresenter : MovieContract.moviePresenter {

    private var movieRepository: MovieRepository
    private lateinit var movieView: MovieContract.movieView

    constructor(movieRepository: MovieRepository) {
        this.movieRepository = movieRepository
    }

    override fun getListMovie(url: String) {
        movieRepository.getListMovie(url, object : MovieDataResource.ListMovieCallback{
            override fun onSuccessListMovie(movieList: List<ResultsItem>, msg: String) {
                movieView.onSuccessListMovie(movieList, msg)
            }

            override fun onErrorListMovie(msg: String) {
                movieView.onErrorListMovie(msg)
            }

        })
    }

    override fun getDetailMovie(url: String) {
        movieRepository.getDetailMovie(url, object : MovieDataResource.DetailMovieCallBack{
            override fun onSuccessDetailMovie(
                detailMovieResponse: DetailMovieResponse,
                msg: String
            ) {
                movieView.onSuccessDetailMovie(detailMovieResponse, msg)
            }

            override fun onErrorDetailMovie(msg: String) {
                movieView.onErrorDetailMovie(msg)
            }

        })
    }

    override fun onAttachView(view: MovieContract.movieView) {
        movieView = view
    }

    override fun onDettachView() {
    }

}