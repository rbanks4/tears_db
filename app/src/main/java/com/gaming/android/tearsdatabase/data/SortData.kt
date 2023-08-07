package com.gaming.android.tearsdatabase.data

import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.viewmodels.*

class SortData {
    companion object {
        fun <T> onSortMenuItemSelected(choice: Int, viewModel: ItemViewModel<T>): List<T>? {
            val listUpdate: List<T>?
            val list =
                if(!viewModel.searchList.isNullOrEmpty())
                    viewModel.searchList
                else viewModel.items

            listUpdate = viewModel.sort(choice, list)
            return if (!listUpdate.isNullOrEmpty()) {
                viewModel.update(listUpdate)
                listUpdate
            } else {
                viewModel.items
            }
        }
    }
}