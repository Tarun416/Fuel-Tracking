package com.track.fueltracking.ui.map

/**
 * Created by Tarun on 11/30/17.
 */

data class NearBySearchResponse(
		val html_attributions: List<String>,
		val next_page_token: String, //CqQCHgEAAHXs6azZ-2POG4QPeEShy75eShGDdm8pSVmCJJ8XynOyG6FTX1l_hIYwtiNnnrv2R8LhSIvOBPh812GkDvw5dJC2xwrt6P_wmX0fDtlrlb2vCqC3e4cNBkfIFY8-6ODRRyBuzBPZRRkg6Nz-LFg2B2y48TWWq1ut1JKsXmd7hk19q5X-miTDaKUllBqsHXiGA7shsZ16fgWA423r3tjX6WbXeQNlH-c-jt9fBFgu0EUhnYSSQBX2xgeaLBawMZE08iT8ZPcKqwlrbjjeaJx9pITkNlqzsAwP8KoQoRlxVkl8XceX6JeCBhD1NlGT5CpFQAhqp7ULZgkT119HMoOTR7jAgLpmLlVicCsX3SfSxzOfwvujJvHWBY0dj8Yh60-xghIQzSkO8m9J4XaskjsqoAydGRoUOLw6CSHMGqDuPzwR8jzgdfa41wk
		val results: List<Result>,
		val status: String //OK
)

data class Result(
		val geometry: Geometry,
		val icon: String, //https://maps.gstatic.com/mapfiles/place_api/icons/shopping-71.png
		val id: String, //91ae80fada2f9e6c777396c918924e3e9531ddfc
		val name: String, //Tibet Mall
		val opening_hours: OpeningHours,
		val photos: List<Photo>,
		val place_id: String, //ChIJK8BZqFsUrjsR8gqjkqPvsEk
		val rating: Double, //3.7
		val reference: String, //CmRRAAAA3NuVwdBsnMwvoWsmmKM6LBVq-uqm5pfmSA_rKStiS83oJAdWmMNsz0utNFM3MLY5bdQRl168yQArXU1zlhg5dBYn0NUDcDYpyzrbac3CZn1aXJWRQGhD4mpFZ74AiwrLEhAj9r1zezWxvs5DbF0LrrUcGhQJPEazIAGUxQrYqEpjLKlYDdqQMQ
		val scope: String, //GOOGLE
		val types: List<String>,
		val vicinity: String//47, Koramangala Industrial Layout, Koramangala Bengaluru
)

data class OpeningHours(
		val open_now: Boolean, //true
		val weekday_text: List<Any>
)

data class Photo(
		val height: Int, //2340
		val html_attributions: List<String>,
		val photo_reference: String, //CmRaAAAABviwAqcJW2Edj29cERWI_MncEBf4LTJLLS5zaVj6-kHfJxy8qLSQwL0FDNjoqdmbrdmSjA0Xd9_M6r2FShB13zWVGAMJNT-B1A-lCDL_QzGSl-IUJ-X697jBwEdKynMPEhDHuR-gmc9jCXyqtVGejG2CGhTwlZkUTM366hkDvX44YDId02K_Aw
		val width: Int //4160
)

data class Geometry(
		val location: Location,
		val viewport: Viewport
)

data class Viewport(
		val northeast: Northeast,
		val southwest: Southwest
)

data class Southwest(
		val lat: Double, //12.9328945697085
		val lng: Double //77.6146771197085
)

data class Northeast(
		val lat: Double, //12.9355925302915
		val lng: Double //77.61737508029151
)

data class Location(
		val lat: Double, //12.9341967
		val lng: Double //77.6160531
)