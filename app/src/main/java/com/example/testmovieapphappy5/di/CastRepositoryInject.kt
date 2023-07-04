package com.example.testmovieapphappy5.di

import android.content.Context
import com.example.testmovieapphappy5.repository.cast.CastDataRemote
import com.example.testmovieapphappy5.repository.cast.CastRepository

class CastRepositoryInject {

    companion object{
        fun provideTO(context: Context): CastRepository{
            return CastRepository(CastDataRemote(context))
        }
    }
}