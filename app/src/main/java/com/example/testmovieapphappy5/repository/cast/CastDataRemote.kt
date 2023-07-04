package com.example.testmovieapphappy5.repository.cast

import android.content.Context
import com.example.testmovieapphappy5.data.cast.CastItem
import com.example.testmovieapphappy5.data.cast.CastResponse
import com.example.testmovieapphappy5.api.APIClient
import com.example.testmovieapphappy5.api.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CastDataRemote : CastDataResource{

    private var apiInterface: APIInterface
    private lateinit var castResponseCall: Call<CastResponse>

    constructor(context: Context) {
        this.apiInterface = APIClient.getRetrofit(context)!!.create(APIInterface::class.java)
    }
    override fun getCast(url: String, castCallback: CastDataResource.CastCallback) {
        castResponseCall = apiInterface.getCastMovie(url)
        castResponseCall.enqueue(object :Callback<CastResponse>{
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                try {
                    if (response.body()!!.cast!!.size > 0){
                        val castResponse = response.body()
                        var castName : ArrayList<String> = ArrayList()
                        val castItem : List<CastItem> = castResponse!!.cast
                        var count = 0
                        for(item in castItem) {
                            if (count < 3){
                                var inisialname = ""
                                for (name:String in item.originalName.split(" ")){
                                    inisialname+=name.get(0)
                                }
                                castName.add(item.originalName + " (" + inisialname + ")")
                            }
                            count++
                        }
                        castCallback.onSuccessCast(castName, "Berhasil")
                    }else{
                        castCallback.onErrorCast("tidak ada data")
                    }
                }catch (e : java.lang.Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                castCallback.onErrorCast("tidak ada koneksi")
            }
        })
    }

}