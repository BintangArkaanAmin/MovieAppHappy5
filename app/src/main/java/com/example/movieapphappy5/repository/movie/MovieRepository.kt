package com.example.movieapphappy5.repository.movie


class MovieRepository : MovieDataResource {

    private var movieDataResource : MovieDataResource

    constructor(movieDataResource: MovieDataResource) {
        this.movieDataResource = movieDataResource
    }


    override fun getListMovie(url: String, listMovieCallback: MovieDataResource.ListMovieCallback) {
        movieDataResource.getListMovie(url,listMovieCallback)
    }

    override fun getDetailMovie(
        url: String,
        detailMovieCallBack: MovieDataResource.DetailMovieCallBack
    ) {
        movieDataResource.getDetailMovie(url,detailMovieCallBack)
    }

}