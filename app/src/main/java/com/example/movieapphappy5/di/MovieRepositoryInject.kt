package com.example.testmovieapphappy5.di

import android.content.Context
import com.example.testmovieapphappy5.repository.MovieDataRemote
import com.example.testmovieapphappy5.repository.MovieRepository

class MovieRepositoryInject {
    companion object{
        fun provideTo(context: Context): MovieRepository{
            return MovieRepository(MovieDataRemote(context))
        }
    }
}