package com.example.testmovieapphappy5.api

import com.example.movieapphappy5.data.cast.CastResponse
import com.example.testmovieapphappy5.data.detail.DetailMovieResponse
import com.example.testmovieapphappy5.data.list.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIInterface {

    @GET
    fun getListMovie(
        @Url url:String
    ) : Call<ListMovieResponse>

    @GET fun getDetailMovie(
        @Url url:String
    ): Call<DetailMovieResponse>

    @GET fun getCastMovie(
        @Url url:String
    ): Call<CastResponse>
}