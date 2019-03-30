package com.adrianiglesia.dehoteles

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.borjabravo.readmoretextview.ReadMoreTextView

import kotlinx.android.synthetic.main.activity_hotel_details.*

class HotelDetails : AppCompatActivity() {


    var main_picture:ImageView? = null
    var name:TextView? = null
    var address:TextView? = null
    var description:ReadMoreTextView? = null
    var stars:RatingBar? = null
    var rating:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)
        setSupportActionBar(toolbar)

        main_picture = findViewById(R.id.ivFotoDet)
        name = findViewById(R.id.tvNameDet)
        address = findViewById(R.id.tvAddressDet)
        description = findViewById(R.id.rdDescrDet)
        stars = findViewById(R.id.starDet)
        rating = findViewById(R.id.tvRatingDet)

        val id = intent.getStringExtra("IDHOTEL")
        Log.d("ID",id)
        getHotelDetails("https://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/hotels/${id}")

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }


    fun getHotelDetails(url:String){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String>{
                response ->

            val gson = Gson()
            val hotel = gson.fromJson(response, HotelDetail::class.java)
            Log.d("ID","ID"+hotel.id)
            Log.d("NAME", "Hotel:"+hotel.hotel?.name)

           Picasso.get().load(hotel.hotel?.main_picture).fit().into(main_picture)

            name?.text = hotel.hotel?.name
            address?.text = hotel.hotel?.address
            description?.text = hotel.hotel?.description
            rating?.text = hotel.hotel?.rating.toString()
            stars?.rating = hotel.hotel?.stars!!


        }, Response.ErrorListener {

        })

        queue.add(request)

        return
    }

}
