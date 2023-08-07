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
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.BowCard
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.MaterialCard
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.WeaponCard
import com.gaming.android.tearsdatabase.ui.ViewBuilder.Companion.TopBar
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.ArmorCard
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.MealCard
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.RoastedFoodCard
import com.gaming.android.tearsdatabase.ui.ViewCards.Companion.ShieldCard
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.ArmorDetails
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.BowDetails
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.MaterialDetails
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.MealDetails
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.RoastedFoodDetails
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.ShieldDetails
import com.gaming.android.tearsdatabase.ui.ViewDetails.Companion.WeaponDetails
import com.gaming.android.tearsdatabase.viewmodels.ItemViewModel
import com.gaming.android.tearsdatabase.viewmodels.WeaponsViewModel

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

        @Composable
        fun ShieldList(
            shields: List<Shield>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<Shield>?,
            onMenuItemSelected: (Int) -> List<Shield>?
        ) {
            val displayedShields = remember { mutableStateListOf<Shield>() }

            val selectedShield = remember { mutableStateOf(SampleData.shields[1]) }
            val open = remember { mutableStateOf(false) }
            val currentQuery = remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { ShieldDetails(selectedShield.value) }
                )
            }

            if (shields != null) {
                if (displayedShields.isNullOrEmpty() && currentQuery.value.isEmpty()) {
                    displayedShields.clear()
                    shields.map {
                        displayedShields.add(it)
                    }
                }
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onQuerySearch = {
                                    currentQuery.value = it
                                    displayedShields.clear()
                                    onQuery(it)?.map { material ->
                                        displayedShields.add(material)
                                    }
                                },
                                onListEdit = {
                                    displayedShields.clear()
                                    onMenuItemSelected(it)?.map { shield ->
                                        displayedShields.add(shield)
                                    }
                                },
                                onOpenDrawer = { openDrawer() },
                                menuType = MENU_TYPE_SHIELDS
                            )
                        }
                    ) { contentPadding ->
                        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                            modifier = Modifier.padding(contentPadding),
                            content = {
                                items(displayedShields.size) { index ->
                                    ShieldCard(shield = displayedShields[index], onClick = {
                                        selectedShield.value = it
                                        open.value = true
                                    })
                                }
                            })

                    }
                }
            }
        }

        @Composable
        fun RoastedFoodList(
            roastedFoods: List<RoastedFood>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<RoastedFood>?,
            onMenuItemSelected: (Int) -> List<RoastedFood>?
        ) {
            val displayed = remember { mutableStateListOf<RoastedFood>() }

            val selected = remember { mutableStateOf(SampleData.roastedFood[1]) }
            val open = remember { mutableStateOf(false) }
            val currentQuery = remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { RoastedFoodDetails(selected.value) }
                )
            }

            if (roastedFoods != null) {
                if (displayed.isNullOrEmpty() && currentQuery.value.isEmpty()) {
                    displayed.clear()
                    roastedFoods.map {
                        displayed.add(it)
                    }
                }
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onQuerySearch = {
                                    currentQuery.value = it
                                    displayed.clear()
                                    onQuery(it)?.map { material ->
                                        displayed.add(material)
                                    }
                                },
                                onListEdit = {
                                    displayed.clear()
                                    onMenuItemSelected(it)?.map { shield ->
                                        displayed.add(shield)
                                    }
                                },
                                onOpenDrawer = { openDrawer() },
                                menuType = MENU_TYPE_ROASTED_FOOD
                            )
                        }
                    ) { contentPadding ->
                        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                            modifier = Modifier.padding(contentPadding),
                            content = {
                                items(displayed.size) { index ->
                                    RoastedFoodCard(item = displayed[index], onClick = {
                                        selected.value = it
                                        open.value = true
                                    })
                                }
                            })

                    }
                }
            }
        }

        @Composable
        fun MealList(
            meals: List<Meal>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<Meal>?,
            onMenuItemSelected: (Int) -> List<Meal>?
        ) {
            val displayed = remember { mutableStateListOf<Meal>() }

            val selected = remember { mutableStateOf(SampleData.meals[1]) }
            val open = remember { mutableStateOf(false) }
            val currentQuery = remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { MealDetails(selected.value) }
                )
            }

            if (meals != null) {
                if (displayed.isNullOrEmpty() && currentQuery.value.isEmpty()) {
                    displayed.clear()
                    meals.map {
                        displayed.add(it)
                    }
                }
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onQuerySearch = {
                                    currentQuery.value = it
                                    displayed.clear()
                                    onQuery(it)?.map { material ->
                                        displayed.add(material)
                                    }
                                },
                                onListEdit = {
                                    displayed.clear()
                                    onMenuItemSelected(it)?.map { shield ->
                                        displayed.add(shield)
                                    }
                                },
                                onOpenDrawer = { openDrawer() },
                                menuType = MENU_TYPE_ROASTED_FOOD
                            )
                        }
                    ) { contentPadding ->
                        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                            modifier = Modifier.padding(contentPadding),
                            content = {
                                items(displayed.size) { index ->
                                    MealCard(item = displayed[index], onClick = {
                                        selected.value = it
                                        open.value = true
                                    })
                                }
                            })

                    }
                }
            }
        }

        @Composable
        fun ArmorList(
            armor: List<Armor>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<Armor>?,
            onMenuItemSelected: (Int) -> List<Armor>?
        ) {
            val displayed = remember { mutableStateListOf<Armor>() }

            val selected = remember { mutableStateOf(SampleData.armor[1]) }
            val open = remember { mutableStateOf(false) }
            val currentQuery = remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { ArmorDetails(selected.value) }
                )
            }

            if (armor != null) {
                if (displayed.isNullOrEmpty() && currentQuery.value.isEmpty()) {
                    displayed.clear()
                    armor.map {
                        displayed.add(it)
                    }
                }
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Scaffold(
                        topBar = {
                            TopBar(
                                onQuerySearch = {
                                    currentQuery.value = it
                                    displayed.clear()
                                    onQuery(it)?.map { material ->
                                        displayed.add(material)
                                    }
                                },
                                onListEdit = {
                                    displayed.clear()
                                    onMenuItemSelected(it)?.map { shield ->
                                        displayed.add(shield)
                                    }
                                },
                                onOpenDrawer = { openDrawer() },
                                menuType = MENU_TYPE_ARMOR
                            )
                        }
                    ) { contentPadding ->
                        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                            modifier = Modifier.padding(contentPadding),
                            content = {
                                items(displayed.size) { index ->
                                    ArmorCard(item = displayed[index], onClick = {
                                        selected.value = it
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

    @Preview
    @Composable
    fun PreviewShieldList() {
        TearsTheme {
            ShieldList(shields = SampleData.shields, openDrawer = {}, onQuery = { SampleData.shields }, onMenuItemSelected = { SampleData. shields })
        }
    }

    @Preview
    @Composable
    fun PreviewRoastedFoodList() {
        TearsTheme {
            RoastedFoodList(roastedFoods = SampleData.roastedFood, openDrawer = {}, onQuery = { SampleData.roastedFood }, onMenuItemSelected = { SampleData. roastedFood })
        }
    }

    @Preview
    @Composable
    fun PreviewMealList() {
        TearsTheme {
            MealList(meals = SampleData.meals, openDrawer = {}, onQuery = { SampleData.meals }, onMenuItemSelected = { SampleData. meals })
        }
    }

    @Preview
    @Composable
    fun PreviewArmorList() {
        TearsTheme {
            ArmorList(armor = SampleData.armor, openDrawer = {}, onQuery = { SampleData.armor }, onMenuItemSelected = { SampleData. armor })
        }
    }
}