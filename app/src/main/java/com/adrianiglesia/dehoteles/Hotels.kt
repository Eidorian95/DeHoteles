package com.adrianiglesia.dehoteles

data class Hotels(
    val items: List<Item>

) {

    data class Item(
        val address: String,
        val amenities: List<Amenity>,
        val id: String,
        val main_picture: String,
        val name: String,
        val rating: Double,
        val stars: Float
    ) {

        data class Amenity(
            val description: String,
            val id: String
        )
    }
}