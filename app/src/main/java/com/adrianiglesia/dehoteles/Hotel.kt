package com.adrianiglesia.dehoteles



class ListaHoteles{

    var items:ArrayList<Hotel>? = null
}

class HotelDetail{

    var id:String = ""
    var hotel:Hotel? = null


}

class Hotel{

    var id:String = ""
    var stars:Float = 0.0f
    var name:String = ""
    var rating:Float = 0.0f
    var main_picture:String = ""
    var description:String = ""
    var address:String = ""
}




