package com.example.appwithfragments.listPOI

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.appwithfragments.R
import com.example.appwithfragments.models.TourPlace
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class ListTourPlaceFragmentDirections private constructor() {
  private data class ActionListTourPlaceFragmentToDetailTourPlaceFragment(
    public val detailPlace: TourPlace
  ) : NavDirections {
    public override fun getActionId(): Int =
        R.id.action_listTourPlaceFragment_to_detailTourPlaceFragment

    @Suppress("CAST_NEVER_SUCCEEDS")
    public override fun getArguments(): Bundle {
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
  }

  public companion object {
    public fun actionListTourPlaceFragmentToDetailTourPlaceFragment(detailPlace: TourPlace):
        NavDirections = ActionListTourPlaceFragmentToDetailTourPlaceFragment(detailPlace)
  }
}
