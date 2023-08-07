package com.gaming.android.tearsdatabase.viewmodels

import android.content.Context
import com.gaming.android.tearsdatabase.data.SearchData.Companion.queryItems
import com.gaming.android.tearsdatabase.data.SortData.Companion.onSortMenuItemSelected


const val SEARCH_LIST = "search_list"
const val SEARCH_STRING = "search_string"
interface ItemViewModel<T> {
    var items: List<T>?
    var searchList: List<T>?
    var searchString: String?

    fun getCurrent(): List<T>? {
        return searchList?:items
    }

    fun setup(list: List<T>, ctx: Context)

    fun update(list: List<T>) {
        searchList = list
    }

    fun sort(choice: Int, list: List<T>?): List<T>?

    fun search(regex: Regex, viewModel: ItemViewModel<T>): List<T>

    fun query(query: String): List<T>? {
        return queryItems(query, this)
    }
    fun onItemSelected(item: Int): List<T>? {
        return onSortMenuItemSelected(item, this)
    }

}