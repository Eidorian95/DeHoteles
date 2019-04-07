data class Test(
    val hotel: Hotel,
    val id: String,
    val price: Price
) {
    data class Price(
        val base: Int,
        val best: Int,
        val currency: Currency,
        val final_price: Boolean
    ) {
        data class Currency(
            val code: String,
            val mask: String,
            val ratio: Double
        )
    }

    data class Hotel(
        val address: String,
        val amenities: List<Amenity>,
        val city: City,
        val description: String,
        val geo_location: GeoLocation,
        val id: String,
        val main_picture: String,
        val name: String,
        val rating: Double,
        val reviews: List<Review>,
        val stars: Int
    ) {
        data class City(
            val administrative_division: AdministrativeDivision,
            val code: String,
            val country: Country,
            val id: String,
            val name: String
        ) {
            data class AdministrativeDivision(
                val code: String,
                val id: String,
                val name: String
            )

            data class Country(
                val code: String,
                val id: String,
                val name: String
            )
        }

        data class GeoLocation(
            val latitude: Double,
            val longitude: Double
        )

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
                val type: String
            )
        }

        data class Amenity(
            val description: String,
            val id: String
        )
    }
}