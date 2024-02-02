package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.data.DataSource.Companion.getEffectsByName
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

            var selectedWeapon by remember { mutableStateOf(SampleData.weapons[1]) }
            var open by remember { mutableStateOf(false) }
            var currentQuery by remember { mutableStateOf("") }

            if (open) {
                Dialog(
                    onDismissRequest = { open = false },
                    content = { WeaponDetails(selectedWeapon) }
                )
            }

            if (displayedWeapons.isEmpty() && currentQuery.isEmpty()) {
                displayedWeapons.clear()
                weapons?.map {
                    displayedWeapons.add(it)
                }
            }

            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TopBar(
                            currentSearch = currentQuery,
                            onQuerySearch = {
                                currentQuery = it
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
                            items(
                                count = displayedWeapons.size,
                                key = {
                                    displayedWeapons[it].compendium_no
                                }
                            ) { index ->
                                WeaponCard(
                                    wpn = displayedWeapons[index],
                                    onClick = {
                                        selectedWeapon = it
                                        open = true },
                                    modifier = Modifier.padding(8.dp))
                            }
                        })
                }
            }
        }

        @Composable
        fun MaterialList(
            materials: List<Material>?,
            effect: Map<String, Effect>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<Material>?,
            onMenuItemSelected: (Int) -> List<Material>?
        ) {
            val displayedMaterials = remember { mutableStateListOf<Material>() }

            val selectedMaterial = remember { mutableStateOf(SampleData.materials[1]) }
            val open = remember { mutableStateOf(false) }
            var currentQuery by remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { MaterialDetails(selectedMaterial.value, getEffectsByName(effect, listOf(selectedMaterial.value.effect_type))) }
                )
            }

            if (displayedMaterials.isEmpty() && currentQuery.isEmpty()) {
                displayedMaterials.clear()
                materials?.map {
                    displayedMaterials.add(it)
                }
            }
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TopBar(
                            currentSearch = currentQuery,
                            onQuerySearch = {
                                currentQuery = it
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
                    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                        modifier = Modifier.padding(contentPadding),
                        content = {
                            items(
                                count = displayedMaterials.size,
                                key = {
                                    displayedMaterials[it].name
                                }
                            ) { index ->
                                MaterialCard(
                                    mat = displayedMaterials[index],
                                    onClick = {
                                        selectedMaterial.value = it
                                        open.value = true },
                                    effect = getEffectsByName(effect, listOf(displayedMaterials[index].effect_type)),
                                    modifier = Modifier.padding(8.dp))
                            }
                        })

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
            var currentQuery by remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { BowDetails(selectedBow.value) }
                )
            }

            if (displayedBows.isEmpty() && currentQuery.isEmpty()) {
                displayedBows.clear()
                bows?.map {
                    displayedBows.add(it)
                }
            }
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TopBar(
                            currentSearch = currentQuery,
                            onQuerySearch = {
                                currentQuery = it
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
                            items(
                                count = displayedBows.size,
                                key = {
                                    displayedBows[it].compendium_no
                                }
                            ) { index ->
                                BowCard(
                                    bow = displayedBows[index],
                                    onClick = {
                                        selectedBow.value = it
                                        open.value = true },
                                    modifier = Modifier.padding(8.dp))
                            }
                        })

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
            var currentQuery by remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { ShieldDetails(selectedShield.value) }
                )
            }


            if (displayedShields.isEmpty() && currentQuery.isEmpty()) {
                displayedShields.clear()
                shields?.map {
                    displayedShields.add(it)
                }
            }
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TopBar(
                            currentSearch = currentQuery,
                            onQuerySearch = {
                                currentQuery = it
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
                            items(
                                count = displayedShields.size,
                                key = {
                                    displayedShields[it].compendium_no
                                }
                            ) { index ->
                                ShieldCard(
                                    shield = displayedShields[index],
                                    onClick = {
                                        selectedShield.value = it
                                        open.value = true },
                                    modifier = Modifier.padding(8.dp))
                            }
                        })

                }
            }

        }

        @Composable
        fun RoastedFoodList(
            roastedFoods: List<RoastedFood>?,
            effect: Map<String, Effect>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<RoastedFood>?,
            onMenuItemSelected: (Int) -> List<RoastedFood>?
        ) {
            val displayed = remember { mutableStateListOf<RoastedFood>() }

            val selected = remember { mutableStateOf(SampleData.roastedFood[1]) }
            val open = remember { mutableStateOf(false) }
            var currentQuery by remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { RoastedFoodDetails(selected.value, getEffectsByName(effect, listOf(selected.value.effect_type))) }
                )
            }

            if (displayed.isEmpty() && currentQuery.isEmpty()) {
                displayed.clear()
                roastedFoods?.map {
                    displayed.add(it)
                }
            }
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TopBar(
                            currentSearch = currentQuery,
                            onQuerySearch = {
                                currentQuery = it
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
                            items(
                                count = displayed.size,
                                key = {
                                    displayed[it].actor_name
                                }
                            ) { index ->
                                RoastedFoodCard(
                                    item = displayed[index],
                                    onClick = {
                                        selected.value = it
                                        open.value = true },
                                    effect = getEffectsByName(effect, listOf(displayed[index].effect_type)),
                                    modifier = Modifier.padding(8.dp))
                            }
                        })

                }
            }
        }

        @Composable
        fun MealList(
            meals: List<Meal>?,
            materials: List<Material>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> Unit,
            onMenuItemSelected: (Int) -> List<Meal>?,
            findList: (Pair<Int,Int>) -> List<Material>
        ) {
            val displayed = remember { mutableStateListOf<Meal>() }

            val selected = remember { mutableStateOf(SampleData.meals[1]) }
            val open = remember { mutableStateOf(false) }
            var currentQuery by remember { mutableStateOf("") }

            if (open.value) {
                Dialog(
                    onDismissRequest = { open.value = false },
                    content = { MealDetails(selected.value, {findList(it)}) }
                )
            }

            if (displayed.isEmpty() && currentQuery.isEmpty()) {
                displayed.clear()
                meals?.map {
                    displayed.add(it)
                }
            }

            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TopBar(
                            currentSearch = currentQuery,
                            onQuerySearch = {
                                currentQuery = it
                                displayed.clear()
                                meals?.map { meal ->
                                    displayed.add(meal)
                                }
                                onQuery(it)
                            },
                            onListEdit = {
                                displayed.clear()
                                onMenuItemSelected(it)?.map { meals ->
                                    displayed.add(meals)
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
                            items(
                                count = displayed.size,
                                key = {
                                    displayed[it]._id
                                }
                            ) {
                            MealCard(
                                item = displayed[it],
                                materials = materials?: emptyList(),
                                onClick = {
                                    selected.value = it
                                    open.value = true },
                                modifier = Modifier.padding(8.dp))
                            }
                        })

                }
            }
        }

        @Composable
        fun ArmorList(
            armor: List<Armor>?,
            effect: Map<String, Effect>?,
            openDrawer: () -> Unit,
            onQuery: (String) -> List<Armor>?,
            onMenuItemSelected: (Int) -> List<Armor>?
        ) {
            val displayed = remember { mutableStateListOf<Armor>() }

            val selected = remember { mutableStateOf(SampleData.armor[1]) }
            var open by remember { mutableStateOf(false) }
            var currentQuery by remember { mutableStateOf("") }

            if (open) {
                Dialog(
                    onDismissRequest = { open = false },
                    content = { ArmorDetails(selected.value, getEffectsByName(effect, selected.value.effect)) }
                )
            }

            if (displayed.isEmpty() && currentQuery.isEmpty()) {
                displayed.clear()
                armor?.map {
                    displayed.add(it)
                }
            }
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TopBar(
                            currentSearch = currentQuery,
                            onQuerySearch = {
                                currentQuery = it
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
                            items(
                                count = displayed.size,
                                key = {
                                    displayed[it].actor_name
                                }
                            ) { index ->
                                ArmorCard(
                                    item = displayed[index],
                                    onClick = {
                                        selected.value = it
                                        open = true },
                                    effect = getEffectsByName(effect, displayed[index].effect),
                                    modifier = Modifier.padding(8.dp))
                            }
                        })
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
            MaterialList(materials = SampleData.materials, openDrawer = {}, onQuery = { SampleData.materials }, onMenuItemSelected = { SampleData. materials }, effect = SampleData.effectMap)
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
            RoastedFoodList(roastedFoods = SampleData.roastedFood, openDrawer = {}, onQuery = { SampleData.roastedFood }, onMenuItemSelected = { SampleData. roastedFood }, effect = SampleData.effectMap)
        }
    }

    @Preview
    @Composable
    fun PreviewMealList() {
        TearsTheme {
            MealList(meals = SampleData.meals, materials = SampleData.materials, openDrawer = {}, onQuery = { SampleData.meals }, onMenuItemSelected = { SampleData. meals }, findList = { SampleData.materials })
        }
    }

    @Preview
    @Composable
    fun PreviewArmorList() {
        TearsTheme {
            ArmorList(armor = SampleData.armor, openDrawer = {}, onQuery = { SampleData.armor }, onMenuItemSelected = { SampleData. armor }, effect = SampleData.effectMap)
        }
    }
}