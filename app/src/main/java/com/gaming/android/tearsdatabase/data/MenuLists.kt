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
            ),
            MenuItem(
                text = R.string.sort_mat_sell_dec,
                action = SORT_SELLING_DEC
            ),
            MenuItem(
                text = R.string.sort_mat_sell_inc,
                action = SORT_SELLING_INC
            ),
            MenuItem(
                text = R.string.sort_mat_buy_dec,
                action = SORT_BUYING_DEC
            ),
            MenuItem(
                text = R.string.sort_mat_buy_inc,
                action = SORT_BUYING_INC
            )
        )

        val bowsMenuList = listOf(
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
            ),
            MenuItem(
                text = R.string.sort_drw_high_low,
                action = SORT_DRAWING_TIME_DEC
            ),
            MenuItem(
                text = R.string.sort_drw_low_high,
                action = SORT_DRAWING_TIME_INC
            ),
            MenuItem(
                text = R.string.sort_rld_high_low,
                action = SORT_RELOAD_TIME_DEC
            ),
            MenuItem(
                text = R.string.sort_rld_low_high,
                action = SORT_RELOAD_TIME_INC
            ),
            MenuItem(
                text = R.string.sort_rng_high_low,
                action = SORT_RANGE_DEC
            ),
            MenuItem(
                text = R.string.sort_rng_low_high,
                action = SORT_RANGE_INC
            ),
        )

        val shieldsMenuList = listOf(
            MenuItem(
                text = R.string.sort_dur_high_low,
                action = SORT_DURABILITY_DEC
            ),
            MenuItem(
                text = R.string.sort_dur_low_high,
                action = SORT_DURABILITY_INC
            ),
            MenuItem(
                text = R.string.sort_slp_high_low,
                action = SORT_SLIPPERINESS_DEC
            ),
            MenuItem(
                text = R.string.sort_slp_low_high,
                action = SORT_SLIPPERINESS_INC
            )
        )

        val roastedFoodMenuList = listOf(
            MenuItem(
                text = R.string.sort_id_low_high,
                action = SORT_ID_INC
            )
        )

        val mealsMenuList = listOf(
            MenuItem(
                text = R.string.sort_id_low_high,
                action = SORT_ID_INC
            )
        )
    }
}