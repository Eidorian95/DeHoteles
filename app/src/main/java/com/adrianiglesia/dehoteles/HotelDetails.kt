package com.adrianiglesia.dehoteles

data class HotelDetails(
    val hotel: Hotel,
    val id: String

) {

    data class Hotel(
        val address: String,
        val amenities: List<Amenity>,
        val description: String,
        val geo_location: GeoLocation,
        val id: String,
        val main_picture: String,
        val name: String,
        val rating: Double,
        val reviews: List<Review>,
        val stars: Float
        ) {

        data class Review(
            val comments: Comments,
            val user: User
        ) {
            data class User(
                val country: String,
                val first_name: String,
                val last_name: String,
                val name: String
            )

            data class Comments(
                val good: String,
                val bad: String,
                val type: String
            )
        }

        data class GeoLocation(
            val latitude: Double,
            val longitude: Double
        )

        data class Amenity(
            val description: String,
            val id: String
        )
    }
}