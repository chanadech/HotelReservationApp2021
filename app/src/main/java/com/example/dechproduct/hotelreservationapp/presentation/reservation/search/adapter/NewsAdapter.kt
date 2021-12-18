package com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dechproduct.hotelreservationapp.data.model.Article
import com.example.dechproduct.hotelreservationapp.databinding.NewsListItemBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    //DiftUtil for calculate min number of updates to convert one list into another -> help about improve performance
    //it also calculate the difference between two list
    // and output a list of update operations that converts the first list into the second one
    private val callback = object : DiffUtil.ItemCallback<Article>() {              // in <> use for add the object type of the list item that we want to compare

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean { //use for decide whether two obj represent the same item in old and new list -> we can use value of one property for this comparison
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean { //use for decide whether two obj are the same data
           return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder { //use for get binding obj for the layout
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
        Log.i("MYTAG","came here vh")


    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) { //use for get the current article instance and pass it into the bind function of the viewholder
        val article = differ.currentList[position]
        holder.bind(article)
        Log.i("MYTAG","came here bv")

    }

    override fun getItemCount(): Int { //use for return the number of the list
        return differ.currentList.size
        Log.i("MYTAG","came here gc")


    }

    inner class NewsViewHolder(
        val binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article){
            Log.i("MYTAG","came here ${article.title}")

            binding.tvTitle.text  = article.title
            binding.tvDescription.text  = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source.name

            Glide.with(binding.ivArticleImage.context)
                .load(article.urlToImage)
                .into(binding.ivArticleImage)

            binding.root.setOnClickListener{
                onItemClickListener?.let {
                    it(article)
                }
            }

        }
    }
    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) ->Unit){
        onItemClickListener = listener
    }

}


