package com.gaming.android.tearsdatabase.viewmodels.interfaces

import com.gaming.android.tearsdatabase.models.BattleItem

interface BattleItemViewModel<T> {

    fun searchItemsWithSubtype(items: List<BattleItem<T>>?, regex: String): List<T>? {
        var finalList: List<T>? = listOf()
        items?.let { list ->
            val nameList = list.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }.map { it.get() }
            val subList = list.filter {
                var found = false
                var check = false
                if(it.sub_type.isEmpty()) {
                    false
                } else {
                    for (i in it.sub_type) {
                        check = i.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                        if (check) {
                            found = check
                            continue
                        }
                    }
                    found
                }
            }.map { it.get() }
            val subList2 = list.filter {
                var found = false
                var check = false
                if(it.sub_type2.isEmpty()) {
                    false
                } else {
                    for (i in it.sub_type2) {
                        check = i.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                        if (check) {
                            found = check
                            continue
                        }
                    }
                    found
                }
            }.map { it.get() }

            finalList = nameList + subList + subList2
        }

        return finalList
    }
}