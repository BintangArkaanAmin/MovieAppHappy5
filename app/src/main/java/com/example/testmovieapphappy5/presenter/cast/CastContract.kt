package com.example.testmovieapphappy5.presenter.cast

import com.example.testmovieapphappy5.base.BasePresenter

class CastContract {

    interface castView{
        fun onSuccessCast(castList : ArrayList<String>, msg:String)

        fun onErrorCast(msg:String)
    }

    interface castPresenter:BasePresenter<castView>{
        fun getCast(url:String, limit: Int)
    }
}