package com.example.movieapphappy5.di

import android.content.Context
import com.example.movieapphappy5.repository.cast.CastDataRemote
import com.example.movieapphappy5.repository.cast.CastRepository

class CastRepositoryInject {

    companion object{
        fun provideTO(context: Context): CastRepository{
            return CastRepository(CastDataRemote(context))
        }
    }
}