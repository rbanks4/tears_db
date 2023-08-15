package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gaming.android.tearsdatabase.data.DataSource
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.theme.TearsTheme

class ViewDetails {
    companion object {
        @Composable
        fun WeaponDetails(weapon: Weapon) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(20.dp))
            ) {
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
                        value = weapon.fuse_damage.toString()
                    )

                    if (weapon.fuse_durability != null) {
                        DetailRow(
                            name = "Fuse Durability:",
                            value = weapon.fuse_durability.toString()
                        )
                    }
                    if (weapon.attach_zoani_attk != null) {
                        DetailRow(
                            name = "Zonai Attack:",
                            value = weapon.attach_zoani_attk.toString()
                        )
                    }
                    DetailRow(
                        name = "Shield Bash Damage:",
                        value = weapon.shield_bash_damage.toString()
                    )
                    DetailRow(name = "Sub Type:", value = weapon.sub_type)
                    DetailRow(name = "Subtype2:", value = weapon.sub_type2)

                }
            }
        }

        @Composable
        fun MaterialDetails(item: Material, effects: List<Effect>) {
            Surface(
                Modifier
                    .requiredWidth(300.dp)
                    .fillMaxHeight(0.7f)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier
                    .padding(all = 8.dp)
                    .verticalScroll(rememberScrollState())) {
                    Box (modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(100.dp)
                        )
                        if (effects.isNotEmpty()) {
                            Log.d("ViewCards.ArmorCard", "Showing: ${effects[0].name}")
                            Icon(
                                painter = painterResource(effects[0].image),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.TopEnd),
                                tint = if (effects[0].monochrome) MaterialTheme.colorScheme.onSurface else Color.Unspecified
                            )
                        }
                    }

                    TitleRow(item.name)
                    if (item.effect_type.isNotEmpty() && !item.effect_type.equals("None"))
                        SubtitleRow(name = "Effect Type: ${item.effect_type}")
                    else if (item.hp_recover != 0 && item.hp_recover != null)
                        SubtitleRow(name = "Hp Recover: ${item.hp_recover}")
                    else if (item.additional_damage != -1)
                        SubtitleRow(name = "Additional Damage: ${item.additional_damage}")

                    Spacer(Modifier.padding(all = 8.dp))

                    if (item.additional_damage != -1) {
                        DetailRow(
                            name = "Additional Damage:",
                            value = item.additional_damage.toString()
                        )
                    }
                    if (item.selling_price != null) {
                        DetailRow(
                            name = "Selling Price:",
                            value = "${item.selling_price} rupees"
                        )
                    }
                    if (item.buying_price != null) {
                        DetailRow(
                            name = "Buying Price:",
                            value = "${item.buying_price} rupees"
                        )
                    }
                    if (item.dye_color.isNotEmpty()) {
                        DetailRow(
                            name = "Dye Color:",
                            value = item.dye_color
                        )
                    }
                    if (item.effect_type.isNotEmpty() && !item.effect_type.equals("None")) {
                        if(effects.isNotEmpty()) {
                            DetailRowExpandable("Effect:", item.effect_type, effects[0], true)
                        } else {
                            DetailRow("Effect:", item.effect_type)
                        }
                    }
                    if (item.effect_level != null) {
                        DetailRow(
                            name = "Effect Potency:",
                            value = item.effect_level.toString()
                        )
                    }
                    if (item.effect_time != 0 && item.effect_time != null) {
                        DetailRow(
                            name = "Effect Time:",
                            value = item.effect_time.toString()
                        )
                    }
                    if (item.hp_recover != 0 && item.hp_recover != null) {
                        DetailRow(
                            name = "HP Recover:",
                            value = item.hp_recover.toString()
                        )
                    }
                    if (item.additional_damage_rate_arrow != null) {
                        DetailRow(
                            name = "Additional Damage Rate Arrow:",
                            value = item.additional_damage_rate_arrow.toString()
                        )
                    }
                    if (item.sheild_bash_damage != null) {
                        DetailRow(
                            name = "Sheild Bash Damage:",
                            value = item.sheild_bash_damage.toString()
                        )
                    }
                    if (item.boost_effective_time != 0 && item.boost_effective_time != null) {
                        DetailRow(
                            name = "Boost Effect Time:",
                            value = item.boost_effective_time.toString()
                        )
                    }
                    if (item.boost_hp_recover != 0 && item.boost_hp_recover != null) {
                        DetailRow(
                            name = "Boost HP Recover:",
                            value = item.boost_hp_recover.toString()
                        )
                    }
                    if (item.boost_max_heart != 0 && item.boost_max_heart != null) {
                        DetailRow(
                            name = "Boost Max Heart:",
                            value = item.boost_max_heart.toString()
                        )
                    }
                    if (item.boost_max_stamina != 0 && item.boost_max_stamina != null) {
                        DetailRow(
                            name = "Boost Max Stamina:",
                            value = item.boost_max_stamina.toString()
                        )
                    }
                    if (item.boost_critical_cook != 0 && item.boost_critical_cook != null) {
                        DetailRow(
                            name = "Boost Critical Cook:",
                            value = "+${item.boost_critical_cook} %"
                        )
                    }
                    if (item.sub_type.isNotEmpty()) {
                        DetailRow(name = "Sub Type:", value = item.sub_type)
                    }

                }
            }
        }

        @Composable
        fun BowDetails(bow: Bow) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(all = 8.dp)) {
                    Image(
                        painter = painterResource(id = bow.image),
                        contentDescription = bow.name,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    TitleRow(bow.name)
                    SubtitleRow(name = "Compendium no. ${bow.compendium_no}")

                    Spacer(Modifier.padding(all = 8.dp))

                    DetailRow(
                        name = "Damage: ",
                        value = bow.base_attack.toString()
                    )

                    DetailRow(
                        name = "Durability: ",
                        value = bow.durability.toString()
                    )

                    DetailRow(
                        name = "Range: ",
                        value = bow.range.toString()
                    )

                    DetailRow(
                        name = "Drawing Time: ",
                        value = bow.drawing_time.toString()
                    )

                    DetailRow(
                        name = "Reload Time: ",
                        value = bow.reload_time.toString()
                    )

                    if (bow.additional_damage != null && bow.additional_damage != 0) {
                        DetailRow(
                            name = "Additional Damage: ",
                            value = bow.additional_damage.toString()
                        )
                    }

                    if (bow.sub_type.isNotEmpty()) {
                        DetailRow(
                            name = "Sub Type: ",
                            value = bow.sub_type
                        )
                    }

                    if (bow.sub_type2.isNotEmpty()) {
                        DetailRow(
                            name = "Sub Type 2: ",
                            value = bow.sub_type2
                        )
                    }

                    if (bow.other.isNotEmpty()) {
                        DetailRow(
                            name = "Other Type: ",
                            value = bow.other
                        )
                    }

                }
            }
        }

        @Composable
        fun ShieldDetails(shield: Shield) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(all = 8.dp)) {
                    Image(
                        painter = painterResource(id = shield.image),
                        contentDescription = shield.name,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    TitleRow(shield.name)
                    SubtitleRow(name = "Compendium no. ${shield.compendium_no}")
                    Spacer(Modifier.padding(all = 8.dp))
                    DetailRow(name = "Guard Power:", value = shield.guard_power.toString())
                    DetailRow(name = "Durability:", value = shield.durability.toString())
                    DetailRow(
                        name = "Shield Surfing Damage Ratio:",
                        value = shield.shield_surfing_damage_ratio.toString()
                    )
                    DetailRow(
                        name = "Shield Surfing Friction:",
                        value = shield.shield_surfing_friction.toString()
                    )

                    if (shield.additional_damage != null) {
                        DetailRow(
                            name = "Additional Damage:",
                            value = shield.additional_damage.toString()
                        )
                    }

                    if(shield.sub_type.isNotEmpty()) {
                        DetailRow(name = "Sub Type:", value = shield.sub_type)
                    }

                    if(shield.sub_type2.isNotEmpty()) {
                        DetailRow(name = "Subtype2:", value = shield.sub_type2)
                    }

                }
            }
        }

        @Composable
        fun RoastedFoodDetails(item: RoastedFood, effects: List<Effect>) {
            Surface(
                Modifier
                    .requiredWidth(300.dp)
                    .fillMaxHeight(0.7f)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier
                    .padding(all = 8.dp)
                    .verticalScroll(rememberScrollState())) {
                    Box (modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(100.dp)
                        )
                        if (effects.isNotEmpty()) {
                            Log.d("ViewCards.ArmorCard", "Showing: ${effects[0].name}")
                            Icon(
                                painter = painterResource(effects[0].image),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.TopEnd),
                                tint = if (effects[0].monochrome) MaterialTheme.colorScheme.onSurface else Color.Unspecified
                            )
                        }
                    }

                    TitleRow(item.name)
                    SubtitleRow(name = "Recipe no. ${item.recipe_no}")
                    Spacer(Modifier.padding(all = 8.dp))

                    DetailRow("Buying Price:", "${item.buying_price} rupees")
                    DetailRow("Selling Price:", "${item.selling_price} rupees")
                    if(effects.isNotEmpty()) {
                        DetailRowExpandable("Effect:", item.effect_type, effects[0], false)
                    } else {
                        DetailRow("Effect:", item.effect_type)
                    }
                    DetailRow("Effect Potency:", item.effect_level.toString())
                    DetailRow("Effect Time:", item.effect_time.toString())
                    DetailRow("HP:", item.hit_point_counter.toString())
                    DetailRow("Color:", item.color)

                    if (item.shield_bash_damage != null) {
                        DetailRow("Shield Bash Damage:", item.shield_bash_damage.toString())
                    }

                    if(item.sub_type.isNotEmpty()) {
                        DetailRow("Sub Type:", item.sub_type)
                    }

                }
            }
        }

        @Composable
        fun MealDetails(item: Meal) {
            Surface(
                Modifier
                    .requiredWidth(300.dp)
                    .requiredHeight(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(all = 8.dp)) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    TitleRow(item.name)
                    SubtitleRow(name = "Recipe no. ${item.recipe_no}")
                    Spacer(Modifier.padding(all = 8.dp))

                    item.bonus_heart?.let { DetailRow("Bonus Heart:", it.toString()) }
                    item.bonus_level?.let { DetailRow("Bonus Level:", it.toString()) }
                    item.bonus_time?.let {DetailRow("Bonus Time:", it.toString()) }
                    DetailRow("Recipe:", DataSource.recipeFormat(item.recipe))

                }
            }
        }

        @Composable
        fun ArmorDetails(item: Armor, effects: List<Effect>) {
            Surface(
                Modifier
                    .requiredWidth(300.dp)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                var defense = item.base_defense.toString()
                item.star_one?.let { defense += " | $it" }
                item.star_two?.let { defense += " | $it" }
                item.star_three?.let { defense += " | $it" }
                item.star_four?.let { defense += " | $it" }

                var sellPrice = item.selling_price.toString()
                item.selling_price_s1?.let { sellPrice += " | $it" }
                item.selling_price_s2?.let { sellPrice += " | $it" }
                item.selling_price_s3?.let { sellPrice += " | $it" }
                item.selling_price_s4?.let { sellPrice += " | $it" }

                Column(modifier = Modifier
                    .padding(all = 8.dp)
                    .verticalScroll(rememberScrollState())) {
                    Box (modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(100.dp)
                        )
                        if (effects.isNotEmpty()) {
                            Log.d("ViewCards.ArmorCard", "Showing: ${effects[0].name}")
                            Icon(
                                painter = painterResource(effects[0].image),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.TopEnd),
                                tint = if (effects[0].monochrome) MaterialTheme.colorScheme.onSurface else Color.Unspecified
                            )
                        }
                    }

                    TitleRow(item.name)
                    SubtitleRow(name = item.location)
                    if(item.coordinates.isNotEmpty()) {
                        Spacer(Modifier.padding(all = 4.dp))
                        SubtitleRow(name = "Coordinates: ${item.coordinates}")
                    }
                    Spacer(Modifier.padding(all = 8.dp))

                    DetailRow("Actor Name:", item.actor_name)

                    if(item.effect.isNotEmpty() && !item.effect.equals("none")) {
                        if(effects.isNotEmpty()) {
                            DetailRowExpandable("Effect:", item.effect, effects[0], false)
                        } else {
                            DetailRow("Effect:", item.effect)
                        }
                    }

                    if(item.set_name.isNotEmpty() && !item.set_name.equals("none")) {
                        DetailRow("Set:", item.set_name)
                    }

                    if(item.set_bonus.isNotEmpty() && !item.set_bonus.equals("none")) {
                        DetailRow("Set Bonus:", item.set_bonus)
                    }

                    DetailRow("Defense:", defense)
                    DetailRow("Buying Price:", "${item.buying_price} rupees")
                    DetailRow("Selling Price:",sellPrice)
                    if(item.upgrade_s1.isNotEmpty()) {
                        DetailRow("Upgrade 1:", item.upgrade_s1)
                    }

                    if(item.upgrade_s2.isNotEmpty()) {
                        DetailRow("Upgrade 2:", item.upgrade_s2)
                    }

                    if(item.upgrade_s3.isNotEmpty()) {
                        DetailRow("Upgrade 3:", item.upgrade_s3)
                    }

                    if(item.upgrade_s4.isNotEmpty()) {
                        DetailRow("Upgrade 4:", item.upgrade_s4)
                    }

                    if(item.total_upgrades.isNotEmpty()) {
                        DetailRow("Total Upgrades:", item.total_upgrades)
                    }
                }
            }
        }

        @Composable
        fun TitleRow(name: String) {
            Row {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        @Composable
        fun SubtitleRow(name: String) {
            Row (Modifier.width(280.dp)){
                Text(text = name, style = MaterialTheme.typography.titleSmall, softWrap = true)
            }
        }

        @Composable
        fun DetailRow(name: String, value: String) {
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.padding(horizontal = 4.dp))
                Text(text = value, style = MaterialTheme.typography.bodySmall)
            }
        }

        @Composable
        fun DetailRowExpandable(name: String, value: String, effect: Effect, showPotency: Boolean) {
            var isExpanded by remember { mutableStateOf(false) }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.padding(horizontal = 4.dp))
                Text(
                    text = value,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = TextDecoration.Underline,
                    //do we pass in an effect to make this take action based on that?
                    modifier = Modifier.clickable { isExpanded = !isExpanded }
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    ShowEffectRow(effect = effect, showPotency)
                    if (effect.effect_level2 != null) {
                        ShowEffectRow(effect = effect.effect_level2!!, showPotency)
                    }
                    if (effect.effect_level3 != null) {
                        ShowEffectRow(effect = effect.effect_level3!!, showPotency)
                    }
                }
            }
        }

        @Composable
        fun ShowEffectRow(effect: Effect, showPotency: Boolean) {
            val level = if(effect.level != null) "Level ${effect.level}" else ""
            val title = "${effect.name} $level"
            Row(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            Row(
                Modifier
                    .width(280.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = effect.value,
                    style = MaterialTheme.typography.titleSmall,
                    softWrap = true
                )
            }
            //potency
            if(showPotency && effect.required_potency != null){
                EffectRow("Potency: ", effect.required_potency.toString())
                if(effect.highest_potency != null) {
                    EffectRow("Highest Potency:", effect.highest_potency.toString())
                }
            }
        }

        @Composable
        fun EffectRow(title: String, detail: String){
            Row(modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.padding(horizontal = 4.dp))
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }

    @Preview
    @Composable
    fun PreviewMealDetailsView() {
        TearsTheme {
            MealDetails(item = SampleData.meals[1])
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewArmorDetailsView() {
        TearsTheme {
            ArmorDetails(item = SampleData.armor[0], effects = SampleData.effects)
        }
    }
}