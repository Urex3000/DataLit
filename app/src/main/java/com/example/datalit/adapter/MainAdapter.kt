package com.example.datalit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datalit.R
import com.example.datalit.model.BookItem

class MainAdapter : BaseAdapter<BookItem, MainAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.raw_item, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvtitle = view.findViewById<TextView>(R.id.tv_title)

        fun bind(data: BookItem) {
            tvtitle.text = data.volumeInfo.title
        }

    }

}