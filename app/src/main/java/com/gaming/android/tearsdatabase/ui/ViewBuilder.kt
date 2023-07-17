package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.data.MenuLists
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.navigation.NavigationItems
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewLists.Companion.BowList
import com.gaming.android.tearsdatabase.ui.ViewLists.Companion.MaterialList
import com.gaming.android.tearsdatabase.ui.ViewLists.Companion.WeaponList
import com.gaming.android.tearsdatabase.ui.ViewLists.Companion.ShieldList
import kotlinx.coroutines.launch

class ViewBuilder {
    companion object {
        @Composable
        fun TopBar(
            onQuerySearch: (String) -> Unit,
            onListEdit: (Int) -> Unit,
            onOpenDrawer: () -> Unit,
            menuType: Int
        ) {
            var textState by remember { mutableStateOf("") }
            val ctx = LocalContext.current
            Row(modifier = Modifier.padding(end = 8.dp)) {
                IconButton(
                    onClick = { onOpenDrawer() },
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = ctx.getString(R.string.menu_box_icon_description),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                TextField(
                    value = textState,
                    enabled = true,
                    onValueChange = { query ->
                        textState = query
                        onQuerySearch(query)
                    },
                    modifier = Modifier.width(1000.dp),
                    readOnly = false,
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.search_vector),
                            contentDescription = ctx.getString(R.string.search_description),
                            modifier = Modifier
                                .size(20.dp),
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
                        )
                    },
                    trailingIcon = {
                        MenuBox(
                            onListEdit = { onListEdit(it) },
                            type = menuType
                        )
                    },
                    supportingText = { Text(ctx.getString(R.string.search_details)) }
                )
            }

        }

        @Composable
        fun MenuBox(onListEdit: (Int) -> Unit, type: Int) {
            var expanded by remember { mutableStateOf(false) }
            val ctx = LocalContext.current
            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .size(20.dp)
            ) {
                Box {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = ctx.getString(R.string.menu_box_icon_description)
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.wrapContentSize(Alignment.TopEnd)
                    ) {
                        when (type) {
                            MENU_TYPE_WEAPONS -> MenuLists.weaponsMenuList.forEach {
                                DropdownMenuItem(
                                    text = { Text(ctx.getString(it.text)) },
                                    onClick = { onListEdit(it.action) }
                                )
                            }
                            MENU_TYPE_MATERIALS -> MenuLists.materialsMenuList.forEach {
                                DropdownMenuItem(
                                    text = { Text(ctx.getString(it.text)) },
                                    onClick = { onListEdit(it.action) }
                                )
                            }
                            MENU_TYPE_BOWS -> MenuLists.bowsMenuList.forEach {
                                DropdownMenuItem(
                                    text = { Text(ctx.getString(it.text)) },
                                    onClick = { onListEdit(it.action) }
                                )
                            }
                            MENU_TYPE_SHIELDS -> MenuLists.shieldsMenuList.forEach {
                                DropdownMenuItem(
                                    text = { Text(ctx.getString(it.text)) },
                                    onClick = { onListEdit(it.action) }
                                )
                            }
                        }

                    }
                }
            }
        }

        @Composable
        fun CreateDrawer(
            weapons: List<Weapon>?,
            materials: List<Material>?,
            bows: List<Bow>?,
            shields: List<Shield>?,
            onQueryWeapon: (String) -> List<Weapon>?,
            onWeaponMenuItemSelected: (Int) -> List<Weapon>?,
            onQueryMaterial: (String) -> List<Material>?,
            onMaterialMenuItemSelected: (Int) -> List<Material>?,
            onQueryBow: (String) -> List<Bow>?,
            onBowMenuItemSelected: (Int) -> List<Bow>?,
            onQueryShield: (String) -> List<Shield>?,
            onShieldMenuItemSelected: (Int) -> List<Shield>?
        ) {
            val drawerState = rememberDrawerState(DrawerValue.Open)
            val scope = rememberCoroutineScope()

            // icons to mimic drawer destinations
            val items = NavigationItems.getNavItems()
            val selectedItem = remember { mutableStateOf(items[0]) }

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        Spacer(Modifier.height(12.dp))
                        items.forEach { item ->
                            NavigationDrawerItem(
                                label = { Text(item.name) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                icon = { Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp),
                                    tint = MaterialTheme.colorScheme.onSurface
                                ) }
                            )
                        }
                    }
                },
                content = {
                    when (selectedItem.value.name) {
                        items[0].name -> WeaponList(
                            weapons = weapons,
                            openDrawer = { scope.launch { drawerState.open() } },
                            onQuery = { onQueryWeapon(it) },
                            onWeaponMenuItemSelected = onWeaponMenuItemSelected
                        )
                        items[1].name -> BowList(
                            bows = bows,
                            openDrawer = { scope.launch { drawerState.open() } },
                            onQuery = { onQueryBow(it) },
                            onMenuItemSelected = onBowMenuItemSelected
                        )
                        items[2].name -> ShieldList(
                            shields = shields,
                            openDrawer = { scope.launch { drawerState.open() } },
                            onQuery = { onQueryShield(it) },
                            onMenuItemSelected = onShieldMenuItemSelected
                        )
                        items[3].name -> MaterialList(
                            materials = materials,
                            openDrawer = { scope.launch { drawerState.open() } },
                            onQuery = { onQueryMaterial(it) },
                            onMenuItemSelected = { onMaterialMenuItemSelected(it) }
                        )
                    }
                }
            )
        }
    }

    @Preview
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewTopBar() {
        TearsTheme {
            TopBar(onQuerySearch = {}, onListEdit = {}, onOpenDrawer = {}, menuType = MENU_TYPE_WEAPONS)
        }
    }
}