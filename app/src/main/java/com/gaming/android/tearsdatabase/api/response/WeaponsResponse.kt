package com.gaming.android.tearsdatabase.api.response

import com.gaming.android.tearsdatabase.models.Weapon
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeaponsResponse(
    val weapons: List<Weapon>
)