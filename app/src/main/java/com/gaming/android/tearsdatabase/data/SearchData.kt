package com.gaming.android.tearsdatabase.data

import android.util.Log
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.viewmodels.*

class SearchData {
    companion object {
        fun <T> queryItems(query: String, viewModel: ItemViewModel<T>, updateSearchList: (List<T>) -> Unit): List<T>? {
            val regex = if(query.isNullOrBlank()) "." else query.lowercase()
            val currentList = viewModel.search(".*$regex.*".toRegex(), viewModel).toSet().toList()

            viewModel.searchString = regex
            updateSearchList(currentList)

            return currentList
        }
    }
}