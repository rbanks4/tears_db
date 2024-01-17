package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.theme.TearsTheme

class ViewCards {
    companion object {
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
                    if (isExpanded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface
                )

                Column(modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .align(Alignment.CenterHorizontally)) {
                    ItemTitle(title = wpn.name)

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
                            text = "Damage: ${wpn.shown_attack} \nDurability: ${wpn.durability}",
                            modifier = Modifier.padding(all = 4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        @Composable
        fun MaterialCard(mat: Material, onClick: (Material) -> Unit, effect: List<Effect>) {

            val text = if (mat.hp_recover != 0 && mat.hp_recover != null)
                "Hp Recover: ${mat.hp_recover}"
            else if (mat.additional_damage != -1)
                "Damage: ${mat.additional_damage}"
            else ""


            Column(modifier = Modifier.padding(all = 8.dp)) {
                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Image(
                        painter = painterResource(id = mat.image),
                        contentDescription = mat.name,
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                                onClick(mat)
                            }
                    )
                    if(effect.isNotEmpty()) {
                        Icon(
                            painter = painterResource(effect[0].image),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp).align(Alignment.TopEnd),
                            tint = if(effect[0].monochrome) MaterialTheme.colorScheme.onSurface else Color.Unspecified
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface, label = "surfaceColor"
                )

                Column(modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .align(Alignment.CenterHorizontally)) {
                    Text(
                        text = mat.name,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    if(text.isNotEmpty()) {
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
                                text = text,
                                modifier = Modifier.padding(all = 4.dp),
                                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }

        @Composable
        fun BowCard(bow: Bow, onClick: (Bow) -> Unit) {
            val text = "Damage: ${bow.base_attack} \nDurability: ${bow.durability}"

            Column(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = bow.image),
                    contentDescription = bow.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            onClick(bow)
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))

                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface
                )

                Column(modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .align(Alignment.CenterHorizontally)) {
                    ItemTitle(bow.name)

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
                            text = text,
                            modifier = Modifier.padding(all = 4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        @Composable
        fun ShieldCard(shield: Shield, onClick: (Shield) -> Unit) {
            val text = "Durability: ${shield.durability}"

            Column(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = shield.image),
                    contentDescription = shield.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            onClick(shield)
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))

                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface
                )

                Column(modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .align(Alignment.CenterHorizontally)) {
                    ItemTitle(shield.name)

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
                            text = text,
                            modifier = Modifier.padding(all = 4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        @Composable
        fun RoastedFoodCard(item: RoastedFood, onClick: (RoastedFood) -> Unit, effect: List<Effect>) {
            val text = "Recipe No: ${item.recipe_no}"


            Column(modifier = Modifier.padding(all = 8.dp)) {
                Box {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                                onClick(item)
                            }
                    )
                    if(effect.isNotEmpty()) {
                        Icon(
                            painter = painterResource(effect[0].image),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp).align(Alignment.TopEnd),
                            tint = if(effect[0].monochrome) MaterialTheme.colorScheme.onSurface else Color.Unspecified
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface
                )

                Column(modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .align(Alignment.CenterHorizontally)) {
                    ItemTitle(item.name)

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
                            text = text,
                            modifier = Modifier.padding(all = 4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        @Composable
        fun MealCard(item: Meal, onClick: (Meal) -> Unit) {
            val text = "Recipe No: ${item.recipe_no}"
            var imageId = if(item.image == 0) R.drawable.mushroom_skewer else item.image

            Column(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            onClick(item)
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))

                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface
                )

                Column(modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .align(Alignment.CenterHorizontally)) {
                    ItemTitle(item.name)

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
                            text = text,
                            modifier = Modifier.padding(all = 4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        @Composable
        fun ArmorCard(item: Armor, onClick: (Armor) -> Unit, effect: List<Effect>) {
            val text = if(item.set_name.isNotEmpty()) {
                item.set_name
            } else if(item.effect.isNotEmpty()) {
                 item.effect.toString()
            } else "none"



            Column(modifier = Modifier.padding(all = 8.dp)) {
                Box {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                                onClick(item)
                            }
                    )
                    if(effect.isNotEmpty()) {
                        Log.d("ViewCards.ArmorCard", "Showing: ${effect[0].name}")
                        Icon(
                            painter = painterResource(effect[0].image),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp).align(Alignment.TopEnd),
                            tint = if(effect[0].monochrome) MaterialTheme.colorScheme.onSurface else Color.Unspecified
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor by animateColorAsState(
                    if (isExpanded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface
                )

                Column(modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .align(Alignment.CenterHorizontally)) {
                    ItemTitle(item.name)

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
                            text = text,
                            modifier = Modifier.padding(all = 4.dp),
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            style = MaterialTheme.typography.bodyMedium,
                            softWrap = true
                        )
                    }
                }
            }
        }

        @Composable
        fun ItemTitle(title: String){
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewWeaponCard() {
        TearsTheme {
            Surface {
                WeaponCard(
                    wpn= SampleData.weapons[1],
                    onClick = {}
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMaterialCard() {
        TearsTheme {
            Surface {
                MaterialCard(
                    mat= SampleData.materials[1],
                    onClick = {},
                    effect = SampleData.effects
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewBowCard() {
        TearsTheme {
            Surface {
                BowCard(
                    bow= SampleData.bows[1],
                    onClick = {}
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewShieldCard() {
        TearsTheme {
            Surface {
                ShieldCard(
                    shield= SampleData.shields[1],
                    onClick = {}
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewRoastedFoodCard() {
        TearsTheme {
            Surface {
                RoastedFoodCard(
                    item = SampleData.roastedFood[1],
                    onClick = {},
                    effect = SampleData.effects
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMealCard() {
        TearsTheme {
            Surface {
                MealCard(
                    item = SampleData.meals[1],
                    onClick = {}
                )
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
    fun PreviewArmorCard() {
        TearsTheme {
            Surface {
                ArmorCard(
                    item = SampleData.armor[0],
                    onClick = {},
                    effect = SampleData.effects
                )
            }
        }
    }
}