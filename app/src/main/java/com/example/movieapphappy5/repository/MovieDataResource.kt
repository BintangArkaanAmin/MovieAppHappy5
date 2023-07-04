package com.example.testmovieapphappy5.repository

import androidx.annotation.NonNull
import com.example.testmovieapphappy5.data.detail.DetailMovieResponse
import com.example.testmovieapphappy5.data.list.ResultsItem

interface MovieDataResource {

    fun getListMovie(url: String, @NonNull listMovieCallback: ListMovieCallback)
    fun getDetailMovie(url: String, @NonNull detailMovieCallBack: DetailMovieCallBack)

    interface ListMovieCallback{
        fun onSuccessListMovie(movieList : List<ResultsItem>,msg:String)

        fun onErrorListMovie(msg:String)
    }

    interface DetailMovieCallBack{
        fun onSuccessDetailMovie(detailMovieResponse: DetailMovieResponse,msg: String)
        fun onErrorDetailMovie(msg:String)
    }
}