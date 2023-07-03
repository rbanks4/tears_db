package com.gaming.android.tearsdatabase

interface ListUpdater {
    fun update(weapons: List<Weapon>)

    fun getList(): List<Weapon>
}