package com.example.testmovieapphappy5.presenter

import com.example.testmovieapphappy5.base.BasePresenter
import com.example.testmovieapphappy5.data.detail.DetailMovieResponse
import com.example.testmovieapphappy5.data.list.ResultsItem

class MovieContract {

    interface movieView{
        fun onSuccessListMovie(movieList : List<ResultsItem>, msg:String)

        fun onErrorListMovie(msg:String)

        fun onSuccessDetailMovie(detailMovieResponse: DetailMovieResponse, msg: String)
        fun onErrorDetailMovie(msg:String)
    }

    interface moviePresenter:BasePresenter<movieView>{
         fun getListMovie(url: String)
        fun getDetailMovie(url: String)
    }
}