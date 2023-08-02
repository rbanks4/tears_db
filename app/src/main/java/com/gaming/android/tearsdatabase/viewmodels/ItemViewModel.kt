package com.gaming.android.tearsdatabase.viewmodels


const val SEARCH_LIST = "search_list"
const val SEARCH_STRING = "search_string"
interface ItemViewModel<T> {
    var items: List<T>?
    var searchList: List<T>?
    var searchString: String?

    fun getCurrent(): List<T>? {
        return searchList?:items
    }

    fun sort(choice: Int, list: List<T>?): List<T>?

    fun search(regex: Regex, viewModel: ItemViewModel<T>): List<T>

}