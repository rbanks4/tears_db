package com.gaming.android.tearsdatabase.api

//"myhobbydb", "totk"
data class DataRequest(
    val dataSource: String = "myhobbydb",
    val database: String = "totk",
    val collection: String
    )