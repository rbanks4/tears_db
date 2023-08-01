package com.gaming.android.tearsdatabase.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gaming.android.tearsdatabase.R

const val WEAPONS_KEY = "Weapons"
const val BOWS_KEY = "Bows"
const val SHIELDS_KEY = "Shields"
const val MATERIALS_KEY = "Materials"
const val ROASTED_CHILLED_KEY = "Roasted/Chilled Food"
const val RECIPES_KEY = "Recipes"
const val ARMOR_KEY = "Armor"

class NavigationItems {
    companion object {
        @Composable
        fun getNavItems(): Map<String, NavigationItem> {
            return mapOf(
                WEAPONS_KEY to NavigationItem(
                    R.drawable.wooden_stick, WEAPONS_KEY
                ),
                BOWS_KEY to NavigationItem(
                    R.drawable.travelers_bow, BOWS_KEY
                ),
                SHIELDS_KEY to NavigationItem(
                    R.drawable.hylian_shield, SHIELDS_KEY
                ),
                MATERIALS_KEY to NavigationItem(
                    R.drawable.apple, MATERIALS_KEY
                ),
                ROASTED_CHILLED_KEY to NavigationItem(
                    R.drawable.roasted_porgy, ROASTED_CHILLED_KEY
                ),
                RECIPES_KEY to NavigationItem(
                    R.drawable.mushroom_skewer, RECIPES_KEY
                ),
                ARMOR_KEY to NavigationItem(
                    R.drawable.hylian_hood, ARMOR_KEY
                )
            )
        }

    }
}