package com.adrianiglesia.dehoteles

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_hotel_details.*

class DetailActivity : AppCompatActivity() {

    var main_picture:ImageView? = null
    var name:TextView? = null
    var address:TextView? = null
    var stars:RatingBar? = null
    var rating:TextView? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var btMaps: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.viewpager)
        setupViewPager(viewPager!!)

        btMaps = findViewById(R.id.btMaps)
        tabLayout = findViewById(R.id.tabs)
        tabLayout!!.setupWithViewPager(viewPager)
        main_picture = findViewById(R.id.ivFotoDet)
        name = findViewById(R.id.tvNameDet)
        address = findViewById(R.id.tvAddressDet)
        stars = findViewById(R.id.starDet)
        rating = findViewById(R.id.tvRatingDet)


        val id = intent.getStringExtra("IDHOTEL")
        Log.d("ID",id)
        getHotelDetails("https://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/hotels/${id}")




    }

     private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DescriptionFragment(), "Descripción")
        adapter.addFragment(ReviewFragment(), "Reseñas")
        viewPager.adapter = adapter
    }

    class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val fragmentList = ArrayList<Fragment>()
        private val fragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList[position]
        }
    }


    fun getHotelDetails(url:String){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String>{
                response ->

            val gson = Gson()
            val hotel = gson.fromJson(response, HotelDetails::class.java)

            val bundle = Bundle()
            val descriptionFragment = DescriptionFragment()
            val rv = ReviewFragment()

            Picasso.get().load(hotel.hotel?.main_picture).fit().into(main_picture)

            name?.text = hotel.hotel?.name
            address?.text = hotel.hotel?.address
            rating?.text = hotel.hotel?.rating.toString()
            stars?.rating = hotel.hotel?.stars!!



            btMaps?.setOnClickListener {
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("LONGITUDE", hotel.hotel.geo_location.longitude)
                intent.putExtra("LATITUDE", hotel.hotel.geo_location.latitude)
                startActivity(intent)
            }

            bundle.putString("DESCRIPTION", hotel.hotel?.description)
            descriptionFragment.arguments = bundle
            bundle.putString("ID", hotel.id)
            rv.arguments = bundle

            var ftRev : FragmentTransaction = supportFragmentManager.beginTransaction()
            ftRev.replace(R.id.fragmentReview, rv)
            ftRev.commit()

            var ftDescription : FragmentTransaction = supportFragmentManager.beginTransaction()
            ftDescription.replace(R.id.fragmentDescription, descriptionFragment)
            ftDescription.commit()

        }, Response.ErrorListener {

        })

        queue.add(request)

        return
    }

}
