package io.getstream.streamchat1.ui.auth

import android.content.Context
import io.getstream.streamchat1.R

data class Country(
  val phoneCode: Int,
  val countryCode: String
){
  override fun toString() = countryCode
}

fun Context.getCountries(): List<Country>{
  val listCountries = mutableListOf<Country>()

  resources.getStringArray(R.array.countries).forEach {
    val temp = it.split(",")
    val country = Country(temp[0].toInt(), temp[1])
    listCountries.add(country)
  }

  return listCountries
}