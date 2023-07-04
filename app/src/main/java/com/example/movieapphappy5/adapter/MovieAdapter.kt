package com.example.testmovieapphappy5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmovieapphappy5.data.list.ResultsItem
import com.example.testmovieapphappy5.databinding.MovieItemBinding

class MovieAdapter(private val listMovie: MutableList<ResultsItem>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: MovieItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listMovie[position]){
                Glide.with(holder.itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + posterPath)
                    .into(binding.moviePoster)
                binding.movieTitle.text = originalTitle
                binding.movieReleaseDate.text = releaseDate
                binding.movieDesc.text = overview
            }
        }
    }

    fun delete(){
        val size = listMovie.size
        if (size>0){
            for (i in 0 until size){
                listMovie.removeAt(0)
            }
            notifyItemRangeChanged(0,size)
        }
    }
}



