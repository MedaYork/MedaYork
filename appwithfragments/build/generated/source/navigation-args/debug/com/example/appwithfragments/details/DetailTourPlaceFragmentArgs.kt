package com.example.appwithfragments.details

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.example.appwithfragments.models.TourPlace
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class DetailTourPlaceFragmentArgs(
  public val detailPlace: TourPlace
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(TourPlace::class.java)) {
      result.putParcelable("detailPlace", this.detailPlace as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(TourPlace::class.java)) {
      result.putSerializable("detailPlace", this.detailPlace as Serializable)
    } else {
      throw UnsupportedOperationException(TourPlace::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DetailTourPlaceFragmentArgs {
      bundle.setClassLoader(DetailTourPlaceFragmentArgs::class.java.classLoader)
      val __detailPlace : TourPlace?
      if (bundle.containsKey("detailPlace")) {
        if (Parcelable::class.java.isAssignableFrom(TourPlace::class.java) ||
            Serializable::class.java.isAssignableFrom(TourPlace::class.java)) {
          __detailPlace = bundle.get("detailPlace") as TourPlace?
        } else {
          throw UnsupportedOperationException(TourPlace::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__detailPlace == null) {
          throw IllegalArgumentException("Argument \"detailPlace\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"detailPlace\" is missing and does not have an android:defaultValue")
      }
      return DetailTourPlaceFragmentArgs(__detailPlace)
    }
  }
}
