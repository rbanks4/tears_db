package com.gaming.android.tearsdatabase.data

import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.models.MenuItem

class MenuLists {
    companion object {
        val weaponsMenuList = listOf(
            MenuItem(
                text = R.string.sort_dmg_high_low,
                action = SORT_DAMAGE_DEC
            ),
            MenuItem(
                text = R.string.sort_dmg_low_high,
                action = SORT_DAMAGE_INC
            ),
            MenuItem(
                text = R.string.sort_dur_high_low,
                action = SORT_DURABILITY_DEC
            ),
            MenuItem(
                text = R.string.sort_dur_low_high,
                action = SORT_DURABILITY_INC
            )
        )

        val materialsMenuList = listOf(
            MenuItem(
                text = R.string.sort_mat_hp,
                action = SORT_HP_DEC
            ),
            MenuItem(
                text = R.string.sort_mat_add_damage_dec,
                action = SORT_DAMAGE_DEC
            ),
            MenuItem(
                text = R.string.sort_mat_add_damage_inc,
                action = SORT_DAMAGE_INC
            )
        )
    }
}