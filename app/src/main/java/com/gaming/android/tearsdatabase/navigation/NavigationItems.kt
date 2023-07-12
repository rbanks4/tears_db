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
                    Icon(
                    painter = painterResource(R.drawable.wooden_stick),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                    ), "Weapons"
                ),
                NavigationItem(
                    Icon(
                        painter = painterResource(R.drawable.wooden_stick),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    ), "Bows"
                ),
                NavigationItem(
                    Icon(
                        painter = painterResource(R.drawable.wooden_stick),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    ), "Shields"
                ),
                NavigationItem(
                    Icon(
                        painter = painterResource(R.drawable.apple),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    ), "Materials"
                )
            )
        }

    }
}