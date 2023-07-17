package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gaming.android.tearsdatabase.MENU_TYPE_BOWS
import com.gaming.android.tearsdatabase.MENU_TYPE_MATERIALS
import com.gaming.android.tearsdatabase.MENU_TYPE_WEAPONS
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.BowCard
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.MaterialCard
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.WeaponCard
import com.gaming.android.tearsdatabase.ui.ViewBuilder.Companion.TopBar
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.BowDetails
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.MaterialDetails
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.WeaponDetails

class ViewLists {
    companion object {
        @Composable
        fun WeaponList(
            weapons: List<Weapon>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<Weapon>?,
            onWeaponMenuItemSelected: (Int) -> List<Weapon>?
        ) {
            val displayedWeapons = remember { mutableStateListOf<Weapon>() }

            val selectedWeapon = remember { mutableStateOf(SampleData.weapons[1]) }
            val open = remember { mutableStateOf(false) }
            val currentQuery = remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { WeaponDetails(selectedWeapon.value) }
                )
            }

            if (weapons != null) {
                if (displayedWeapons.isNullOrEmpty() && currentQuery.value.isEmpty()) {
                    displayedWeapons.clear()
                    weapons.map {
                        displayedWeapons.add(it)
                    }
                }
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onQuerySearch = {
                                    currentQuery.value = it
                                    displayedWeapons.clear()
                                    onQuery(it)?.map { weapon ->
                                        displayedWeapons.add(weapon)
                                    }
                                },
                                onListEdit = {
                                    displayedWeapons.clear()
                                    onWeaponMenuItemSelected(it)?.map { weapon ->
                                        displayedWeapons.add(weapon)
                                    }
                                },
                                onOpenDrawer = { openDrawer() },
                                menuType = MENU_TYPE_WEAPONS
                            )
                        }
                    ) { contentPadding ->
                        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                            modifier = Modifier.padding(contentPadding),
                            content = {
                                items(displayedWeapons.size) { index ->
                                    WeaponCard(wpn = displayedWeapons[index], onClick = {
                                        selectedWeapon.value = it
                                        open.value = true
                                    })
                                }
                            })

                    }
                }
            }
        }

        @Composable
        fun MaterialList(
            materials: List<Material>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<Material>?,
            onMenuItemSelected: (Int) -> List<Material>?
        ) {
            val displayedMaterials = remember { mutableStateListOf<Material>() }

            val selectedMaterial = remember { mutableStateOf(SampleData.materials[1]) }
            val open = remember { mutableStateOf(false) }
            val currentQuery = remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { MaterialDetails(selectedMaterial.value) }
                )
            }

            if (materials != null) {
                if (displayedMaterials.isNullOrEmpty() && currentQuery.value.isEmpty()) {
                    displayedMaterials.clear()
                    materials.map {
                        displayedMaterials.add(it)
                    }
                }
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onQuerySearch = {
                                    currentQuery.value = it
                                    displayedMaterials.clear()
                                    onQuery(it)?.map { material ->
                                        displayedMaterials.add(material)
                                    }
                                },
                                onListEdit = {
                                    displayedMaterials.clear()
                                    onMenuItemSelected(it)?.map { material ->
                                        displayedMaterials.add(material)
                                    }
                                },
                                onOpenDrawer = { openDrawer() },
                                menuType = MENU_TYPE_MATERIALS
                            )
                        }
                    ) { contentPadding ->
                        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),
                            modifier = Modifier.padding(contentPadding),
                            content = {
                                items(displayedMaterials.size) { index ->
                                    MaterialCard(mat = displayedMaterials[index], onClick = {
                                        selectedMaterial.value = it
                                        open.value = true
                                    })
                                }
                            })

                    }
                }
            }
        }

        @Composable
        fun BowList(
            bows: List<Bow>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<Bow>?,
            onMenuItemSelected: (Int) -> List<Bow>?
        ) {
            val displayedBows = remember { mutableStateListOf<Bow>() }

            val selectedBow = remember { mutableStateOf(SampleData.bows[1]) }
            val open = remember { mutableStateOf(false) }
            val currentQuery = remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { BowDetails(selectedBow.value) }
                )
            }

            if (bows != null) {
                if (displayedBows.isNullOrEmpty() && currentQuery.value.isEmpty()) {
                    displayedBows.clear()
                    bows.map {
                        displayedBows.add(it)
                    }
                }
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onQuerySearch = {
                                    currentQuery.value = it
                                    displayedBows.clear()
                                    onQuery(it)?.map { material ->
                                        displayedBows.add(material)
                                    }
                                },
                                onListEdit = {
                                    displayedBows.clear()
                                    onMenuItemSelected(it)?.map { bow ->
                                        displayedBows.add(bow)
                                    }
                                },
                                onOpenDrawer = { openDrawer() },
                                menuType = MENU_TYPE_BOWS
                            )
                        }
                    ) { contentPadding ->
                        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                            modifier = Modifier.padding(contentPadding),
                            content = {
                                items(displayedBows.size) { index ->
                                    BowCard(bow = displayedBows[index], onClick = {
                                        selectedBow.value = it
                                        open.value = true
                                    })
                                }
                            })

                    }
                }
            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewWeaponList() {
        TearsTheme {
            WeaponList(weapons = SampleData.weapons, openDrawer = {}, onQuery = { SampleData.weapons }, onWeaponMenuItemSelected = { SampleData. weapons })
        }
    }

    @Preview
    @Composable
    fun PreviewMaterialList() {
        TearsTheme {
            MaterialList(materials = SampleData.materials, openDrawer = {}, onQuery = { SampleData.materials }, onMenuItemSelected = { SampleData. materials })
        }
    }

    @Preview
    @Composable
    fun PreviewBowList() {
        TearsTheme {
            BowList(bows = SampleData.bows, openDrawer = {}, onQuery = { SampleData.bows }, onMenuItemSelected = { SampleData. bows })
        }
    }
}