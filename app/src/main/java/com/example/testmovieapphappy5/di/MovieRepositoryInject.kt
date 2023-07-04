package com.example.testmovieapphappy5.di

import android.content.Context
import com.example.testmovieapphappy5.repository.movie.MovieDataRemote
import com.example.testmovieapphappy5.repository.movie.MovieRepository

class MovieRepositoryInject {
    companion object{
        fun provideTo(context: Context): MovieRepository {
            return MovieRepository(MovieDataRemote(context))
        }
    }
}