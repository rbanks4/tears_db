package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    itemTitle(title = wpn.name)

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
        fun MaterialCard(mat: Material, onClick: (Material) -> Unit) {

            var text = if (mat.effect_type.isNotEmpty() && !mat.effect_type.equals("None"))
                "Effect: ${mat.effect_type}"
            else if (mat.hp_recover != 0 && mat.hp_recover != null)
                "Hp Recover: ${mat.hp_recover}"
            else if (mat.additional_damage != -1)
                "Additional Damage: ${mat.additional_damage}"
            else ""

            if (mat.sub_type.isNotEmpty())
                text += "\nSubtype: ${mat.sub_type}"

            Column(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = mat.image),
                    contentDescription = mat.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            onClick(mat)
                        }
                        .align(Alignment.CenterHorizontally)
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
                    Text(
                        text = mat.name,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
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
        fun BowCard(bow: Bow, onClick: (Bow) -> Unit) {
            var text = "Damage: ${bow.base_attack} \nDurability: ${bow.durability}"

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
                    itemTitle(bow.name)

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
            var text = "Durability: ${shield.durability}"

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
                    itemTitle(shield.name)

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
        fun RoastedFoodCard(item: RoastedFood, onClick: (RoastedFood) -> Unit) {
            var text = "Recipe No: ${item.recipe_no}"

            Column(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = item.image),
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
                    itemTitle(item.name)

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
            var text = "Recipe No: ${item.recipe_no}"

            Column(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = item.image),
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
                    itemTitle(item.name)

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
        fun itemTitle(title: String){
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
                    onClick = {}
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
                    onClick = {}
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
}