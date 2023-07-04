package com.example.movieapphappy5.repository.cast

class CastRepository : CastDataResource{
    private var castDataResource : CastDataResource

    constructor(castDataResource: CastDataResource) {
        this.castDataResource = castDataResource
    }

    override fun getCast(url: String, castCallback: CastDataResource.CastCallback) {
        castDataResource.getCast(url, castCallback)
    }
}