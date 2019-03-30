package com.adrianiglesia.dehoteles.RecyclerView


import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.adrianiglesia.dehoteles.Hotel
import com.adrianiglesia.dehoteles.HotelDetail
import com.adrianiglesia.dehoteles.R
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class AdaptadorCustom(items: ArrayList<Hotel>?, var listener: ClickListener, var longClickListener: LongClickListener):RecyclerView.Adapter<AdaptadorCustom.ViewHolder> (){


    var items:ArrayList<Hotel>? = null


    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.template_main_layout,parent, false)


        val viewHolder = ViewHolder(vista, listener, longClickListener)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)
        val imageUrl= item?.main_picture

        Picasso.get().load(imageUrl).fit().into(holder.main_picture)
        holder.name?.text = item?.name
        holder.stars?.rating = item?.stars!!
        holder.rating?.text = item?.rating.toString()

    }

    class ViewHolder(vista: View, listener:ClickListener, longClickListener: LongClickListener):RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener{

        var vista = vista
        var main_picture:ImageView? = null
        var name:TextView?=null
        var stars:RatingBar? = null
        var rating: TextView? = null


        var listener:ClickListener? = null
        var longListener:LongClickListener? = null

        init {
            main_picture = vista.findViewById(R.id.ivMainPic)
            name = vista.findViewById(R.id.tvTituloTemp)
            stars = vista.findViewById(R.id.rbStars)
            rating = vista.findViewById(R.id.tvRating)


            this.listener = listener
            this.longListener = longListener

            vista.setOnLongClickListener(this)
            vista.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClick(v!!,adapterPosition)

            return true
        }


    }


}