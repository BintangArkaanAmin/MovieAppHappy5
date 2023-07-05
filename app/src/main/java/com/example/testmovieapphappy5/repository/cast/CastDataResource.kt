package com.example.testmovieapphappy5.repository.cast

import androidx.annotation.NonNull


interface CastDataResource {
    fun getCast(url:String, limit: Int,@NonNull castCallback: CastCallback)

    interface CastCallback {
        fun onSuccessCast(castList : ArrayList<String>, msg:String)

        fun onErrorCast(msg:String)
    }


}