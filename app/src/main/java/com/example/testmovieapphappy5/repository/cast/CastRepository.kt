package com.example.testmovieapphappy5.repository.cast

class CastRepository : CastDataResource{
    private var castDataResource : CastDataResource

    constructor(castDataResource: CastDataResource) {
        this.castDataResource = castDataResource
    }

    override fun getCast(url: String, limit: Int, castCallback: CastDataResource.CastCallback) {
        castDataResource.getCast(url, limit, castCallback)
    }
}