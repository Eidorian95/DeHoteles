package com.adrianiglesia.dehoteles


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DescriptionFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment_description, container,
            false)

        var description = view.findViewById<TextView>(R.id.tvDescDet) as TextView

        var activityArgs = this.arguments?.getString("DESCRIPTION")

        Log.d("DESCFRAGMENT", activityArgs.toString())

        description.text = activityArgs

        return view
    }


}
