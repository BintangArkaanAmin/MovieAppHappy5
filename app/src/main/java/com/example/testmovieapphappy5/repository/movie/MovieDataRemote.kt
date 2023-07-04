package com.example.testmovieapphappy5.repository.movie

import android.content.Context
import com.example.testmovieapphappy5.api.APIClient
import com.example.testmovieapphappy5.api.APIInterface
import com.example.testmovieapphappy5.data.detail.DetailMovieResponse
import com.example.testmovieapphappy5.data.list.ListMovieResponse
import com.example.testmovieapphappy5.data.list.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataRemote: MovieDataResource {

    private var apiInterface:APIInterface
    private lateinit var listmovieResponseCall:Call<ListMovieResponse>
    private lateinit var detailmovieResponseCall:Call<DetailMovieResponse>

    constructor(context: Context) {
        this.apiInterface = APIClient.getRetrofit(context)!!.create(APIInterface::class.java)
    }


    override fun getListMovie(url: String, listMovieCallback: MovieDataResource.ListMovieCallback) {
        listmovieResponseCall= apiInterface.getListMovie(url)
        listmovieResponseCall.enqueue(object : Callback<ListMovieResponse>{
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                try {
                    if (response.body()!!.results!!.size > 0){
                        val listMovieResponse = response.body()
                        val movieItem : List<ResultsItem> = listMovieResponse!!.results
                        listMovieCallback.onSuccessListMovie(movieItem,"Berhasil ambil data dari TMDB")
                    }else{
                        listMovieCallback.onErrorListMovie("tidak ada list movie")
                    }
                }catch (e:java.lang.Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                listMovieCallback.onErrorListMovie("tidak ada koneksi internet")
            }

        })
    }

    override fun getDetailMovie(url: String, detailMovieCallBack: MovieDataResource.DetailMovieCallBack) {
        detailmovieResponseCall= apiInterface.getDetailMovie(url)
        detailmovieResponseCall.enqueue(object :Callback<DetailMovieResponse>{
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                try {
                    if (!response.body()!!.id.toString().isEmpty()) {
                        detailMovieCallBack.onSuccessDetailMovie(response.body()!!,"berhasil")
                    }else{
                        detailMovieCallBack.onErrorDetailMovie("tidak ada data")
                    }
                }catch (e:java.lang.Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                detailMovieCallBack.onErrorDetailMovie("tidak ada koneksi")
            }

        })
    }

}