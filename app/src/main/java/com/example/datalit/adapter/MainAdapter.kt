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
import com.example.datalit.bookdetail.DetailActivity
import com.example.datalit.model.BookItem


class MainAdapter() :
    BaseAdapter<BookItem, MainAdapter.MyViewHolder>() {


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


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvtitle = view.findViewById<TextView>(R.id.tv_title)
        val tvauthors = view.findViewById<TextView>(R.id.tv_author)
        val tvpublisher = view.findViewById<TextView>(R.id.tv_publisher)
        val ivbook = view.findViewById<ImageView>(R.id.iv_book)//        Картинка
        val tvcount = view.findViewById<TextView>(R.id.tv_count)
        val tvdate = view.findViewById<TextView>(R.id.tv_date)
        val tvcategory = view.findViewById<TextView>(R.id.tv_category)
        fun bind(bookItem: BookItem) {
//            Левая сторона
            tvtitle.text = bookItem.volumeInfo?.title
            if (bookItem.volumeInfo?.authors != null) {
                tvauthors.text =
                    "Автор: " + bookItem.volumeInfo.authors.joinToString(separator = ", ")
            } else {
                tvauthors.text = "!Автор не известен!"
            }
            tvpublisher.text = bookItem.volumeInfo?.publisher
//            Правая сторона
            if (bookItem.volumeInfo?.pageCount != null && bookItem.volumeInfo.pageCount != 0) {
                tvcount.text = bookItem.volumeInfo.pageCount.toString()
            } else {
                tvcount.text = "--"
            }
            if (bookItem.volumeInfo?.publishedDate != null) {
                tvdate.text = bookItem.volumeInfo.publishedDate
            } else {
                tvdate.text = "без даты"
            }
            if (bookItem.volumeInfo?.categories != null) {
                tvcategory.text =
                    "Жанр: " + bookItem.volumeInfo.categories.joinToString(separator = "||")
            } else {
                tvcategory.text = "Без категорий"
            }
//Нажатие

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("book", bookItem)
                itemView.context.startActivity(intent)


                println("Click")
            }

//Выгрузка картинок

            val url = bookItem.volumeInfo?.imageLinks?.smallThumbnail?.replace("http:", "https:")

            println("image ${url}")
            Glide.with(ivbook.context)
                .load(url)
                .placeholder(R.drawable.ic_book)
                .into(ivbook);
        }

    }

}