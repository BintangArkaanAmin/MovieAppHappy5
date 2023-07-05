package com.example.testmovieapphappy5.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmovieapphappy5.di.CastRepositoryInject
import com.example.testmovieapphappy5.presenter.cast.CastContract
import com.example.testmovieapphappy5.presenter.cast.CastPresenter
import com.example.testmovieapphappy5.ui.DetailActivity
import com.example.testmovieapphappy5.api.Server
import com.example.testmovieapphappy5.data.list.ResultsItem
import com.example.testmovieapphappy5.databinding.MovieItemBinding

class MovieAdapter(private val listMovie: MutableList<ResultsItem>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(), CastContract.castView {

    private var castList: ArrayList<String> = ArrayList()
    private lateinit var castAdapter: CastAdapter

    inner class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listMovie[position]) {
                Glide.with(holder.itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + posterPath)
                    .into(binding.moviePoster)
                binding.movieTitle.text = originalTitle
                binding.movieReleaseDate.text = releaseDate
                binding.movieDesc.text = overview


                castAdapter = CastAdapter(castList)
                binding.rvCast.layoutManager = LinearLayoutManager(holder.itemView.context)
                binding.rvCast.adapter = castAdapter

                var castPresenter =
                    CastPresenter(CastRepositoryInject.provideTO(holder.itemView.context))
                castPresenter.onAttachView(this@MovieAdapter)
                castPresenter.getCast(id.toString() + Server.URL_CAST_MOVIE, 3)
                binding.root.setOnClickListener {
                    itemView.context.startActivity(
                        Intent(
                            itemView.context,
                            DetailActivity::class.java
                        ).putExtra("id", id.toString())
                    )
                }
            }
        }
    }

    fun delete() {
        val size = listMovie.size
        if (size > 0) {
            for (i in 0 until size) {
                listMovie.removeAt(0)
            }
            notifyItemRangeChanged(0, size)
        }
    }

    override fun onSuccessCast(castList: ArrayList<String>, msg: String) {
        castAdapter.delete()
        this.castList.clear()
        this.castList.addAll(castList)
        castAdapter.notifyDataSetChanged()
    }

    override fun onErrorCast(msg: String) {
    }
}



