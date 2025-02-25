package com.gaming.android.tearsdatabase.api.response

import com.gaming.android.tearsdatabase.models.Subtype
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubtypeResponse(
    val subtypes: List<Subtype>
)
