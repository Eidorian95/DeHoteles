package com.adrianiglesia.dehoteles.RecyclerView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adrianiglesia.dehoteles.HotelDetails
import com.adrianiglesia.dehoteles.R

class ReviewsCustomAdapter(items: List<HotelDetails.Hotel.Review>?):RecyclerView.Adapter<ReviewsCustomAdapter.ViewHolder> () {

    var items:List<HotelDetails.Hotel.Review>? = null


    init {
        this.items = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ReviewsCustomAdapter.ViewHolder {

            val vista = LayoutInflater.from(parent?.context).inflate(R.layout.template_review,parent, false)
            val viewHolder = ReviewsCustomAdapter.ViewHolder(vista)

            return viewHolder


    }

    override fun getItemCount(): Int {
        if(items == null)
        return 0
        else
        return items?.count()!!

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)

        holder?.user?.text = item?.user?.name +", "+ item?.user?.country
        holder?.good?.text = item?.comments?.good
        holder?.bad?.text = item?.comments?.bad
    }

    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){

        var user: TextView?=null
        var good: TextView? = null
        var bad: TextView? = null

        init {
            user = vista.findViewById(R.id.tvUserFrag)
            good = vista.findViewById(R.id.tvGoodFrag)
            bad = vista.findViewById(R.id.tvBadFrag)

        }

    }
}