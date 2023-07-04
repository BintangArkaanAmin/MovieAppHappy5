package com.example.movieapphappy5.repository.cast

import androidx.annotation.NonNull


interface CastDataResource {
    fun getCast(url:String,@NonNull castCallback: CastCallback)

    interface CastCallback {
        fun onSuccessCast(castList : ArrayList<String>, msg:String)

        fun onErrorCast(msg:String)
    }


}