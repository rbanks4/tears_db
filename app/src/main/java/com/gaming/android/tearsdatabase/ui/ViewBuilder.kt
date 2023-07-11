package com.gaming.android.tearsdatabase.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.navigation.NavigationItem
import kotlinx.coroutines.launch

class ViewBuilder {
    @Composable
    fun TopBar(
        onQuerySearch: (String) -> Unit,
        onListEdit: (Int) -> Unit,
        onOpenDrawer: () -> Unit) {
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
                        onListEdit = { onListEdit(it)
                        }
                    )
                },
                supportingText = { Text(ctx.getString(R.string.search_details)) }
            )
        }

    }

    @Composable
    fun MenuBox(onListEdit: (Int) -> Unit) {
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
                    DropdownMenuItem(
                        text = { Text(ctx.getString(R.string.sort_dmg_high_low)) },
                        onClick = { onListEdit(SORT_DAMAGE_DEC) }
                    )
                    DropdownMenuItem(
                        text = { Text(ctx.getString(R.string.sort_dmg_low_high)) },
                        onClick = {
                            onListEdit(SORT_DAMAGE_INC)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(ctx.getString(R.string.sort_dur_high_low)) },
                        onClick = {
                            onListEdit(SORT_DURABILITY_DEC)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(ctx.getString(R.string.sort_dur_low_high)) },
                        onClick = {
                            onListEdit(SORT_DURABILITY_INC)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun WeaponCard(wpn: Weapon, onClick: (Weapon) -> Unit) {
        Column(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = wpn.image),
                contentDescription = wpn.name,
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        onClick(wpn)
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if(isExpanded) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface
            )

            Column (modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .align(Alignment.CenterHorizontally)){
                Text(
                    text = wpn.name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = "Damage: ${wpn.shown_attack} \nDurability: ${wpn.durability} \nSub Type: ${wpn.sub_type}",
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    @Composable
    fun WeaponList(
        weapons: List<Weapon>?,
        openDrawer: () -> Unit,
        onWeaponClick: (Weapon) -> Unit,
        onQuery: (String) -> List<Weapon>?,
        onMenuItemSelected: (Int) -> List<Weapon>?
    ){
        val displayedWeapons = remember { mutableStateListOf<Weapon>() }

        val selectedWeapon = remember { mutableStateOf(SampleData.weapons[1]) }
        val open = remember { mutableStateOf(false) }

        if(open.value) {
            Dialog(
                onDismissRequest = { open.value = false },
                content = { WeaponDetails(selectedWeapon.value) }
            )
        }

        if(weapons != null) {
            if(displayedWeapons.isNullOrEmpty()) {
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
                                displayedWeapons.clear()
                                onQuery(it)?.map {weapon ->
                                    displayedWeapons.add(weapon)
                                }
                            },
                            onListEdit = {
                                displayedWeapons.clear()
                                onMenuItemSelected(it)?.map{ weapon ->
                                    displayedWeapons.add(weapon)
                                }
                            },
                            onOpenDrawer = { openDrawer() }
                        )
                    }
                ) { contentPadding ->
                    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                        modifier = Modifier.padding(contentPadding),
                        content = {
                            items(displayedWeapons.size) { index ->
                                WeaponCard(wpn = displayedWeapons[index], onClick = { selectedWeapon.value = it
                                open.value = true})
                            }
                        })

                }
            }
        }
    }

    @Composable
    fun CreateDrawer(
        weapons: List<Weapon>?,
        onQuery: (String) -> List<Weapon>?,
        onMenuItemSelected: (Int) -> List<Weapon>?
    ) {
        val drawerState = rememberDrawerState(DrawerValue.Open)
        val scope = rememberCoroutineScope()

        // icons to mimic drawer destinations
        val items = listOf(
            NavigationItem(
                Icon(
                    painter = painterResource(R.drawable.wooden_stick),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colorScheme.onSurface
                ), "Weapons"
            ),
            NavigationItem(
                Icon(
                    painter = painterResource(R.drawable.wooden_stick),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colorScheme.onSurface
                ), "Bows"
            ),
            NavigationItem(
                Icon(
                    painter = painterResource(R.drawable.wooden_stick),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colorScheme.onSurface
                ), "Shields"
            ),
            NavigationItem(
                Icon(
                    painter = painterResource(R.drawable.wooden_stick),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colorScheme.onSurface
                ), "Materials"
            )
        )
        val selectedItem = remember { mutableStateOf(items[0]) }
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(12.dp))
                    items.forEach { item ->
                        NavigationDrawerItem(
                            icon = { item.icon },
                            label = { Text(item.name) },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            },
            content = {
                WeaponList(
                    weapons = weapons,
                    openDrawer = { scope.launch { drawerState.open() }},
                    onWeaponClick = {  },
                    onQuery = { onQuery(it) },
                    onMenuItemSelected = { onMenuItemSelected(it) }
                )
            }
        )
    }

    @Composable
    fun WeaponDetails(weapon: Weapon) {
        Surface(
            Modifier
                .requiredWidth(IntrinsicSize.Min)
                .requiredHeight(IntrinsicSize.Min)
                .clip(RoundedCornerShape(20.dp))) {
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = weapon.image),
                    contentDescription = weapon.name,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                TitleRow(weapon.name)
                SubtitleRow(name = "Compendium no. ${weapon.compendium_no}")
                Spacer(Modifier.padding(all = 8.dp))
                DetailRow(name = "Damage:", value = weapon.shown_attack.toString())
                DetailRow(name = "Durability:", value = weapon.durability.toString())
                DetailRow(
                    name = "Guard Break Power:",
                    value = weapon.guard_break_power.toString()
                )
                DetailRow(
                    name = "Fuse Damage:",
                    value = weapon.fuse_damage.toString())

                if(weapon.fuse_durability != null) {
                    DetailRow(
                        name = "Fuse Durability:",
                        value = weapon.fuse_durability.toString()
                    )
                }
                if(weapon.attach_zoani_attk != null) {
                    DetailRow(
                        name = "Zonai Attack:",
                        value = weapon.attach_zoani_attk.toString()
                    )
                }
                DetailRow(name = "Shield Bash Damage:", value = weapon.shield_bash_damage.toString())
                DetailRow(name = "Sub Type:", value = weapon.sub_type)
                DetailRow(name= "Subtype2:", value = weapon.sub_type2)

            }
        }
    }

    @Composable
    fun TitleRow(name: String) {
        Row {
            Text(text = name, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleMedium)
        }
    }

    @Composable
    fun SubtitleRow(name: String) {
        Row {
            Text(text = name, style = MaterialTheme.typography.titleSmall)
        }
    }

    @Composable
    fun DetailRow(name: String, value: String) {
        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(text = name, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.padding(horizontal = 4.dp))
            Text(text = value, style = MaterialTheme.typography.bodySmall)
        }
    }
}