package com.example.datalit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.datalit.R
import com.example.datalit.SQlite.DatabaseBook
import com.example.datalit.bookdetail.DetailActivity

class FavoriteAdapter() : BaseAdapter<DatabaseBook, FavoriteAdapter.FavViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.raw_item, parent, false)
        return FavViewHolder(inflater)
    }

    class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val tvtitle = view.findViewById<TextView>(R.id.tv_title)
        val tvauthors = view.findViewById<TextView>(R.id.tv_author)
        val tvpublisher = view.findViewById<TextView>(R.id.tv_publisher)
        val ivbook = view.findViewById<ImageView>(R.id.iv_book)//        Картинка
        val tvcount = view.findViewById<TextView>(R.id.tv_count)
        val tvdate = view.findViewById<TextView>(R.id.tv_date)
        val tvcategory = view.findViewById<TextView>(R.id.tv_category)

        fun bind(bookItem: DatabaseBook) {
//            Левая сторона
            println("AAA bind")
            tvtitle.text = bookItem.title
            if (bookItem.authors != null) {
                tvauthors.text =
                    "Автор: " + bookItem.authors
            } else {
                tvauthors.text = "!Автор не известен!"
            }
            tvpublisher.text = bookItem.publisher
//            Правая сторона
            if (bookItem.pageCount != null && bookItem.pageCount != 0) {
                tvcount.text = bookItem.pageCount.toString()
            } else {
                tvcount.text = "--"
            }
            if (bookItem.publishedDate != null) {
                tvdate.text = bookItem.publishedDate
            } else {
                tvdate.text = "без даты"
            }
            if (bookItem.categories != null) {
                tvcategory.text =
                    "Жанр: " + bookItem.categories
            } else {
                tvcategory.text = "Без категорий"
            }
//Нажатие

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("book", bookItem.toBookItem())
                itemView.context.startActivity(intent)
            }

//Выгрузка картинок

            val url = bookItem.smallThumbnail
            Glide.with(ivbook.context)
                .load(url)
                .placeholder(R.drawable.ic_book)
                .into(ivbook);
        }
    }


    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(items[position])
    }

}
