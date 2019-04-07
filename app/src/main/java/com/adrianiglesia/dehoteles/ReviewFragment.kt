package com.adrianiglesia.dehoteles


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adrianiglesia.dehoteles.RecyclerView.ReviewsCustomAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson


class ReviewFragment : Fragment() {

    var listReviews: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager?= null
    var mainAdapter: ReviewsCustomAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_review, container, false)


        var args = this.arguments?.getString("ID")
        Log.d("ACTIVITYARGS", args.toString())

         getReviews(args)

        listReviews = view.findViewById(R.id.listReviews)
        listReviews?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        listReviews?.layoutManager = layoutManager


        return view
    }

    fun getReviews(id:String?){
        val queue = Volley.newRequestQueue(context)
        val url = "https://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/hotels/$id"

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String>{
                response ->

            val gson = Gson()

                val reviews = gson.fromJson(response, HotelDetails::class.java)

                   mainAdapter = ReviewsCustomAdapter(reviews.hotel.reviews)
                   listReviews?.adapter = mainAdapter

        }, Response.ErrorListener {

        })

        queue.add(request)

        return
    }


}
