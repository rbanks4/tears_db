package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.models.Weapon
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeaponsResponse(
    val weapons: List<Weapon>
)