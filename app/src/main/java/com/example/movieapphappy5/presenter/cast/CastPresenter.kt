package com.example.movieapphappy5.presenter.cast

import android.view.View
import com.example.movieapphappy5.repository.cast.CastDataResource
import com.example.movieapphappy5.repository.cast.CastRepository

class CastPresenter : CastContract.castPresenter {

    private var castRepository : CastRepository
    private lateinit var castView: CastContract.castView

    constructor(castRepository: CastRepository) {
        this.castRepository = castRepository
    }


    override fun getCast(url: String) {
        castRepository.getCast(url, object :CastDataResource.CastCallback{
            override fun onSuccessCast(castList: ArrayList<String>, msg: String) {
                castView.onSuccessCast(castList, msg)
            }

            override fun onErrorCast(msg: String) {
                castView.onErrorCast(msg)
            }

        })
    }

    override fun onAttachView(view: CastContract.castView) {
        castView = view
    }

    override fun onDettachView() {
    }
}