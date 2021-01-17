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

        /*   holder.itemView.setOnClickListener(object :View.OnClickListener{
               override fun onClick(view: View?) {
                   val activity = view!!.context as AppCompatActivity
                   val detailFragment = DetailFragment()
                   activity.supportFragmentManager.beginTransaction().replace(R.id.rv_books, detailFragment)
               }
           })*/
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val tvtitle = view.findViewById<TextView>(R.id.tv_title)
        val tvauthors = view.findViewById<TextView>(R.id.tv_author)
        val tvpublisher = view.findViewById<TextView>(R.id.tv_publisher)
        val ivbook = view.findViewById<ImageView>(R.id.iv_book)//        Картинка
        val tvcount = view.findViewById<TextView>(R.id.tv_count)
        val tvdate = view.findViewById<TextView>(R.id.tv_date)
        val tvcategory = view.findViewById<TextView>(R.id.tv_category)
        fun bind(data: BookItem) {
//            Левая сторона
            tvtitle.text = data.volumeInfo.title
            if (data.volumeInfo.authors != null) {
                tvauthors.text = "Автор: " + data.volumeInfo.authors.joinToString(separator = ", ")
            } else {
                tvauthors.text = "!Автор не известен!"
            }
            tvpublisher.text = data.volumeInfo.publisher
//            Правая сторона
            if (data.volumeInfo.pageCount != null && data.volumeInfo.pageCount != 0) {
                tvcount.text = data.volumeInfo.pageCount.toString()
            } else {
                tvcount.text = "--"
            }
            if (data.volumeInfo.publishedDate != null) {
                tvdate.text = data.volumeInfo.publishedDate
            } else {
                tvdate.text = "без даты"
            }
            if (data.volumeInfo.categories != null) {
                tvcategory.text =
                    "Жанр: " + data.volumeInfo.categories.joinToString(separator = "||")
            } else {
                tvcategory.text = "Без категорий"
            }
//Нажатие

//Выгрузка картинок

            val url = data.volumeInfo.imageLinks.smallThumbnail.replace("http:", "https:")

            println("image ${url}")
            Glide.with(ivbook.context)
                .load(url)
                .placeholder(R.drawable.ic_book)
                .into(ivbook);
        }

        override fun onClick(view: View?) {
            TODO("Not yet implemented")
        }

    }

}