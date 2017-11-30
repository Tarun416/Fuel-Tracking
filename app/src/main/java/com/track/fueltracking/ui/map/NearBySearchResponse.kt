package com.track.fueltracking.ui.map

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Tarun on 11/30/17.
 */

data class NearBySearchResponse(
		val html_attributions: List<String>,
		val next_page_token: String, //CqQCHgEAAHXs6azZ-2POG4QPeEShy75eShGDdm8pSVmCJJ8XynOyG6FTX1l_hIYwtiNnnrv2R8LhSIvOBPh812GkDvw5dJC2xwrt6P_wmX0fDtlrlb2vCqC3e4cNBkfIFY8-6ODRRyBuzBPZRRkg6Nz-LFg2B2y48TWWq1ut1JKsXmd7hk19q5X-miTDaKUllBqsHXiGA7shsZ16fgWA423r3tjX6WbXeQNlH-c-jt9fBFgu0EUhnYSSQBX2xgeaLBawMZE08iT8ZPcKqwlrbjjeaJx9pITkNlqzsAwP8KoQoRlxVkl8XceX6JeCBhD1NlGT5CpFQAhqp7ULZgkT119HMoOTR7jAgLpmLlVicCsX3SfSxzOfwvujJvHWBY0dj8Yh60-xghIQzSkO8m9J4XaskjsqoAydGRoUOLw6CSHMGqDuPzwR8jzgdfa41wk
		val results: List<Result>,
		val status: String //OK
) : Parcelable {
	constructor(source: Parcel) : this(
			source.createStringArrayList(),
			source.readString(),
			ArrayList<Result>().apply { source.readList(this, Result::class.java.classLoader) },
			source.readString()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeStringList(html_attributions)
		writeString(next_page_token)
		writeList(results)
		writeString(status)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<NearBySearchResponse> = object : Parcelable.Creator<NearBySearchResponse> {
			override fun createFromParcel(source: Parcel): NearBySearchResponse = NearBySearchResponse(source)
			override fun newArray(size: Int): Array<NearBySearchResponse?> = arrayOfNulls(size)
		}
	}
}

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
) : Parcelable {
	constructor(source: Parcel) : this(
			source.readParcelable<Geometry>(Geometry::class.java.classLoader),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readParcelable<OpeningHours>(OpeningHours::class.java.classLoader),
			source.createTypedArrayList(Photo.CREATOR),
			source.readString(),
			source.readDouble(),
			source.readString(),
			source.readString(),
			source.createStringArrayList(),
			source.readString()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeParcelable(geometry, 0)
		writeString(icon)
		writeString(id)
		writeString(name)
		writeParcelable(opening_hours, 0)
		writeTypedList(photos)
		writeString(place_id)
		writeDouble(rating)
		writeString(reference)
		writeString(scope)
		writeStringList(types)
		writeString(vicinity)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Result> = object : Parcelable.Creator<Result> {
			override fun createFromParcel(source: Parcel): Result = Result(source)
			override fun newArray(size: Int): Array<Result?> = arrayOfNulls(size)
		}
	}
}

data class OpeningHours(
		val open_now: Boolean, //true
		val weekday_text: List<Any>
) : Parcelable {
	constructor(source: Parcel) : this(
			1 == source.readInt(),
			ArrayList<Any>().apply { source.readList(this, Any::class.java.classLoader) }
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeInt((if (open_now) 1 else 0))
		writeList(weekday_text)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<OpeningHours> = object : Parcelable.Creator<OpeningHours> {
			override fun createFromParcel(source: Parcel): OpeningHours = OpeningHours(source)
			override fun newArray(size: Int): Array<OpeningHours?> = arrayOfNulls(size)
		}
	}
}

data class Photo(
		val height: Int, //2340
		val html_attributions: List<String>,
		val photo_reference: String, //CmRaAAAABviwAqcJW2Edj29cERWI_MncEBf4LTJLLS5zaVj6-kHfJxy8qLSQwL0FDNjoqdmbrdmSjA0Xd9_M6r2FShB13zWVGAMJNT-B1A-lCDL_QzGSl-IUJ-X697jBwEdKynMPEhDHuR-gmc9jCXyqtVGejG2CGhTwlZkUTM366hkDvX44YDId02K_Aw
		val width: Int //4160
) : Parcelable {
	constructor(source: Parcel) : this(
			source.readInt(),
			source.createStringArrayList(),
			source.readString(),
			source.readInt()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeInt(height)
		writeStringList(html_attributions)
		writeString(photo_reference)
		writeInt(width)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Photo> = object : Parcelable.Creator<Photo> {
			override fun createFromParcel(source: Parcel): Photo = Photo(source)
			override fun newArray(size: Int): Array<Photo?> = arrayOfNulls(size)
		}
	}
}

data class Geometry(
		val location: Location,
		val viewport: Viewport
) : Parcelable {
	constructor(source: Parcel) : this(
			source.readParcelable<Location>(Location::class.java.classLoader),
			source.readParcelable<Viewport>(Viewport::class.java.classLoader)
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeParcelable(location, 0)
		writeParcelable(viewport, 0)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Geometry> = object : Parcelable.Creator<Geometry> {
			override fun createFromParcel(source: Parcel): Geometry = Geometry(source)
			override fun newArray(size: Int): Array<Geometry?> = arrayOfNulls(size)
		}
	}
}

data class Viewport(
		val northeast: Northeast,
		val southwest: Southwest
) : Parcelable {
	constructor(source: Parcel) : this(
			source.readParcelable<Northeast>(Northeast::class.java.classLoader),
			source.readParcelable<Southwest>(Southwest::class.java.classLoader)
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeParcelable(northeast, 0)
		writeParcelable(southwest, 0)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Viewport> = object : Parcelable.Creator<Viewport> {
			override fun createFromParcel(source: Parcel): Viewport = Viewport(source)
			override fun newArray(size: Int): Array<Viewport?> = arrayOfNulls(size)
		}
	}
}

data class Southwest(
		val lat: Double, //12.9328945697085
		val lng: Double //77.6146771197085
) : Parcelable {
	constructor(source: Parcel) : this(
			source.readDouble(),
			source.readDouble()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeDouble(lat)
		writeDouble(lng)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Southwest> = object : Parcelable.Creator<Southwest> {
			override fun createFromParcel(source: Parcel): Southwest = Southwest(source)
			override fun newArray(size: Int): Array<Southwest?> = arrayOfNulls(size)
		}
	}
}

data class Northeast(
		val lat: Double, //12.9355925302915
		val lng: Double //77.61737508029151
) : Parcelable {
	constructor(source: Parcel) : this(
			source.readDouble(),
			source.readDouble()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeDouble(lat)
		writeDouble(lng)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Northeast> = object : Parcelable.Creator<Northeast> {
			override fun createFromParcel(source: Parcel): Northeast = Northeast(source)
			override fun newArray(size: Int): Array<Northeast?> = arrayOfNulls(size)
		}
	}
}

data class Location(
		val lat: Double, //12.9341967
		val lng: Double //77.6160531
) : Parcelable {
	constructor(source: Parcel) : this(
			source.readDouble(),
			source.readDouble()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeDouble(lat)
		writeDouble(lng)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Location> = object : Parcelable.Creator<Location> {
			override fun createFromParcel(source: Parcel): Location = Location(source)
			override fun newArray(size: Int): Array<Location?> = arrayOfNulls(size)
		}
	}
}