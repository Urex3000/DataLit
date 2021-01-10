package com.example.datalit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        val tvauthors = view.findViewById<TextView>(R.id.tv_author)
        val tvpublisher = view.findViewById<TextView>(R.id.tv_publisher)
        val ivbook = view.findViewById<ImageView>(R.id.iv_book)
        fun bind(data: BookItem) {
            tvtitle.text = data.volumeInfo.title
            if (data.volumeInfo.authors != null) {
                tvauthors.text = data.volumeInfo.authors.joinToString(separator = ", ")
            } else {
                tvauthors.text = "!Автор не известен!"
            }
            tvpublisher.text = data.volumeInfo.publisher
            val url = data.volumeInfo.imageLinks.smallThumbnail

            Glide.with(ivbook)
                .load(url)
                .placeholder(R.drawable.ic_book)
                .into(ivbook)
        }

    }

}