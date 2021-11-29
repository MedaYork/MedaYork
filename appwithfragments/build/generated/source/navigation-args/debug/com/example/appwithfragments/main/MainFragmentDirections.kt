package com.example.appwithfragments.main

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.appwithfragments.R

public class MainFragmentDirections private constructor() {
  public companion object {
    public fun actionMainFragmentToListTourPlaceFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_mainFragment_to_listTourPlaceFragment)
  }
}
