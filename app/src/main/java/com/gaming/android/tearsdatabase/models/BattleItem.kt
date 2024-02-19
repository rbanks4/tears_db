package com.gaming.android.tearsdatabase.models

interface BattleItem<T> {
    val name: String

    val sub_type: List<String>

    val sub_type2: List<String>

    fun get(): T
}