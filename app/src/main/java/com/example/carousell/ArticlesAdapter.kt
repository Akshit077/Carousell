package com.example.carousell

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carousell.api.DataModel
import com.example.carousell.api.ResponseDataModel

class ArticlesAdapter (private val context: Context?, private val listener: OnItemClickListener
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    var articles = mutableListOf<DataModel>()

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view), View.OnClickListener {

        val title: TextView = view.findViewById(R.id.tv_title)
        val desc: TextView = view.findViewById(R.id.tv_desc)
        val image: ImageView = view.findViewById(R.id.iv_banner)
        val timezone: TextView =view.findViewById(R.id.tv_time_zone)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position:Int=adapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listener.onItemClick(position,url_adapter = articles[position].bannerUrl,
                    title_adapter = articles[position].title,
                    desc_adapter = articles[position].description)
            }
        }

    }

    fun setMovieList(articles: ArrayList<DataModel>) {
        this.articles = articles.toMutableList()
        notifyDataSetChanged()
    }

    interface OnItemClickListener
    {
        fun onItemClick(position: Int,url_adapter:String,title_adapter:String,
                        desc_adapter:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.articles_item,parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = articles[position].title
            desc.text = articles[position].description

            if (context != null) {
                Glide.with(context)
                    .load(articles[position].bannerUrl)
                    .placeholder(R.drawable.ic_baseline_preview_24)
                    .into(image)
            }
            timezone.text= articles[position].timeCreated.toString()
        }
    }

    override fun getItemCount(): Int = articles.size

}