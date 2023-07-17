package com.gaming.android.tearsdatabase.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gaming.android.tearsdatabase.R


class NavigationItems {
    companion object {
        @Composable
        fun getNavItems(): List<NavigationItem> {
            return listOf(
                NavigationItem(
                    R.drawable.wooden_stick, "Weapons"
                ),
                NavigationItem(
                    R.drawable.travelers_bow, "Bows"
                ),
                NavigationItem(
                    R.drawable.wooden_stick, "Shields"
                ),
                NavigationItem(
                    R.drawable.apple, "Materials"
                )
            )
        }

    }
}