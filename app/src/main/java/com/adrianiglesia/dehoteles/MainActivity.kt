package com.adrianiglesia.dehoteles

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.adrianiglesia.dehoteles.RecyclerView.MainCustomAdapter
import com.adrianiglesia.dehoteles.RecyclerView.ClickListener
import com.adrianiglesia.dehoteles.RecyclerView.LongClickListener
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lista: RecyclerView? = null

    var layoutManager: RecyclerView.LayoutManager?= null

    var mainAdapter: MainCustomAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        getHotels()


        lista = findViewById(R.id.listaHoteles)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun getHotels(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/hotels?fbclid=IwAR3sb554HDeZZgbzqk_x4YKDNBDmykxRvixE5Jyf8Gk6EPOLGu7Fub_MZcs"

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String>{
                response ->
            val gson = Gson()
            val hotels = gson.fromJson(response, Hotels::class.java)
            Log.d("HOTELES", "Cantidad Hoteles: ${hotels.items.size.toString()}")

             mainAdapter = MainCustomAdapter(hotels.items!!, object: ClickListener {

                override  fun  onClick(vista: View, index:Int){



                    val intent = Intent(applicationContext, DetailActivity::class.java)
                    intent.putExtra("IDHOTEL", hotels.items!!.get(index).id)
                    startActivity(intent)



                }
            }, object : LongClickListener {
                override fun longClick(vista: View, index: Int) {

                }
            })
            lista?.adapter = mainAdapter

        }, Response.ErrorListener {

        })

        queue.add(request)

        return
    }
}
