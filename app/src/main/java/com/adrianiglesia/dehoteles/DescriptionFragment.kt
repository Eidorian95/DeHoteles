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

        val view: View = inflater!!.inflate(R.layout.fragment_description, container, false)

        var description = view.findViewById(R.id.tvDescDet) as TextView

        var activityArgs = this.arguments?.getString("DESCRIPTION")

        description.text = activityArgs

        return view
    }


}
