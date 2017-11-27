package com.track.fueltracking.ui.model

/**
 * Created by Tarun on 11/28/17.
 */

data class LocationModel(
		val results: List<Result>,
		val status: String //OK
)

data class Result(
		val address_components: List<AddressComponent>,
		val formatted_address: String, //Kasturba Rd, Nunegundlapalli, Ambedkar Veedhi, Sampangi Rama Nagar, Bengaluru, Karnataka 560001, India
		val geometry: Geometry,
		val place_id: String, //ChIJ5TX7eHYWrjsRDzyK6Q8Uqw0
		val types: List<String>
)

data class AddressComponent(
		val long_name: String, //Kasturba Road
		val short_name: String, //Kasturba Rd
		val types: List<String>
)

data class Geometry(
		val bounds: Bounds,
		val location: Location,
		val location_type: String, //GEOMETRIC_CENTER
		val viewport: Viewport
)

data class Bounds(
		val northeast: Northeast,
		val southwest: Southwest
)

data class Northeast(
		val lat: Double, //12.970538
		val lng: Double //77.59211689999999
)

data class Southwest(
		val lat: Double, //12.9681541
		val lng: Double //77.5893787
)

data class Viewport(
		val northeast: Northeast,
		val southwest: Southwest
)



data class Location(
		val lat: Double, //12.9693495
		val lng: Double //77.5907445
)