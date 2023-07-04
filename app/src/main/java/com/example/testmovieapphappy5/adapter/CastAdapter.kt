package com.example.testmovieapphappy5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovieapphappy5.databinding.CastItemBinding

class CastAdapter(private val listCast: MutableList<String>) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CastItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CastItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCast.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listCast[position]){
                binding.tvName.text = this
            }
        }
    }

    fun delete(){
        val size = listCast.size
        if (size>0){
            for (i in 0 until size){
                listCast.removeAt(0)
            }
            notifyItemRangeChanged(0,size)
        }
    }
}