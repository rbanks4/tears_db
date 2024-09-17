package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.models.submodels.EffectId
import com.gaming.android.tearsdatabase.theme.TearsTheme

class ViewDetails {
    companion object {
        @Composable
        fun WeaponDetails(weapon: Weapon) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Min)
                    .sizeIn(maxHeight = LocalConfiguration.current.screenHeightDp.times(0.7f).dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    DetailHeader(
                        weapon.image,
                        weapon.name,
                        "Compendium no.",
                        weapon.compendium_no.toString()
                    )

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
                    ShowSubRow(title = "Modifiers:", list = weapon.sub_type + weapon.sub_type2)

                }
            }
        }

        @Composable
        fun MaterialDetails(item: Material, effects: List<Effect>) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Min)
                    .sizeIn(maxHeight = LocalConfiguration.current.screenHeightDp.times(0.7f).dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    val subTitlePair =
                        if (item.effect_type.isNotEmpty() && !item.effect_type.equals("None"))
                            Pair("Effect Type:", item.effect_type)
                        else if (item.hp_recover != 0 && item.hp_recover != null)
                            Pair("Hp Recover:", item.hp_recover.toString())
                        else if (item.additional_damage != -1)
                            Pair("Additional Damage:", item.additional_damage.toString())
                        else Pair(null, null)

                    DetailHeaderWithEffects(
                        image = item.image,
                        title = item.name,
                        effects = effects,
                        subLabel = subTitlePair.first,
                        subtitle = subTitlePair.second
                    )

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
                        if (effects.isNotEmpty()) {
                            DetailRowExpandable(
                                "Effect:",
                                listOf(item.effect_type),
                                effects[0],
                                true
                            )
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
                    .sizeIn(maxHeight = LocalConfiguration.current.screenHeightDp.times(0.7f).dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    DetailHeader(
                        image = bow.image,
                        title = bow.name,
                        subLabel = "Compendium no.",
                        subtitle = bow.compendium_no.toString()
                    )

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

                    if (bow.other.isNotEmpty()) {
                        DetailRow(
                            name = "Specialty: ",
                            value = bow.other
                        )
                    }

                    if (bow.sub_type.isNotEmpty()||bow.sub_type2.isNotEmpty()) {
                        val combinedList = bow.sub_type + bow.sub_type2
                        ShowSubRow(title = "Modifiers", list = combinedList.toSet().toList())
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
                    .sizeIn(maxHeight = LocalConfiguration.current.screenHeightDp.times(0.7f).dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    DetailHeader(
                        image = shield.image,
                        title = shield.name,
                        subLabel = "Compendium no.",
                        subtitle = shield.compendium.toString()
                    )
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

                    if (shield.sub_type.isNotEmpty() || shield.sub_type2.isNotEmpty()) {
                        ShowSubRow(title = "Modifier", list = shield.sub_type + shield.sub_type2)
                    }

                }
            }
        }

        @Composable
        fun RoastedFoodDetails(item: RoastedFood, effects: List<Effect>) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Max)
                    .sizeIn(maxHeight = LocalConfiguration.current.screenHeightDp.times(0.7f).dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {

                Column(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    DetailHeaderWithEffects(
                        image = item.image,
                        title = item.name,
                        effects = effects,
                        subLabel = "Recipe no.",
                        subtitle = item.recipe_no.toString()
                    )
                    Spacer(Modifier.padding(all = 8.dp))

                    DetailRow("Buying Price:", "${item.buying_price} rupees")
                    DetailRow("Selling Price:", "${item.selling_price} rupees")
                    if (effects.isNotEmpty()) {
                        DetailRowExpandable("Effect:", listOf(item.effect_type), effects[0], false)
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

                    if (item.sub_type.isNotEmpty()) {
                        DetailRow("Sub Type:", item.sub_type)
                    }

                }
            }
        }

        @Composable
        fun MealDetails(meal: Meal, findList: (Pair<Int, Int>) -> RecipePair) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Max)
                    .sizeIn(maxHeight = LocalConfiguration.current.screenHeightDp.times(0.7f).dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    DetailHeader(
                        image = meal.image,
                        title = meal.name,
                        "Recipe no.",
                        meal.recipe_no.toString()
                    )
                    //SubtitleRow(name = "Recipe no. ${meal.recipe_no}")
                    Spacer(Modifier.padding(all = 8.dp))

                    meal.bonus_heart?.let { DetailRow("Bonus Heart:", it.toString()) }
                    meal.bonus_level?.let { DetailRow("Bonus Level:", it.toString()) }
                    meal.bonus_time?.let { DetailRow("Bonus Time:", it.toString()) }
                    //todo show the materials instead
                    //ShowSubRow("Recipe:", DataSource.recipeFormat(meal.recipe))

                    var recipeList = mutableListOf<List<Material>>()
                    var labelList = mutableListOf<String>()

                    for (ingredients in meal.recipe.indices) {

                        recipeList.add(emptyList())
                        labelList.add("")

                        var count = 0
                        val chunkedGroup = meal.recipe[ingredients].chunked(2)

                        for (pair in chunkedGroup) {

                            val newIngredients = findList(Pair(pair[0], pair[1]))

                            if (newIngredients.list.isNotEmpty()) {
                                if(labelList[ingredients].isEmpty()) {
                                    labelList[ingredients] += newIngredients.name
                                } else if (chunkedGroup.size - 1 == count) {
                                    labelList[ingredients] += ", or ${newIngredients.name}"
                                } else {
                                    labelList[ingredients] += ", ${newIngredients.name}"
                                }
                                recipeList[ingredients] += newIngredients.list
                                count++
                            }
                        }
                    }

                    IngredientRow(materials = recipeList, labelList = labelList)

                }
            }
        }

        @Composable
        fun ArmorDetails(armor: Armor, effects: List<Effect>) {
            /**
             * What if I want to do a percent of the parent size?
             * Modifier.onSizeChanged { parentWidth = with(density) { it.width.toDp() } }
             * at your container and then you can use it like this
             * Modifier.widthIn(max = parentWidth  * 0.75f) for elements inside container
             * Tried it, can't get it to work
             */

            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Max)
                    .sizeIn(maxHeight = LocalConfiguration.current.screenHeightDp.times(0.7f).dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                var defense = armor.base_defense.toString()
                armor.star_one?.let { defense += " | $it" }
                armor.star_two?.let { defense += " | $it" }
                armor.star_three?.let { defense += " | $it" }
                armor.star_four?.let { defense += " | $it" }

                var sellPrice = armor.selling_price.toString()
                armor.selling_price_s1?.let { sellPrice += " | $it" }
                armor.selling_price_s2?.let { sellPrice += " | $it" }
                armor.selling_price_s3?.let { sellPrice += " | $it" }
                armor.selling_price_s4?.let { sellPrice += " | $it" }

                Column(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    DetailHeaderWithEffects(
                        armor.image,
                        armor.name,
                        effects
                    )

                    SubtitleRow(name = armor.location)
                    if (armor.coordinates.isNotEmpty()) {
                        Spacer(Modifier.padding(all = 4.dp))
                        SubtitleRow(name = "Coordinates: ${armor.coordinates}")
                    }
                    Spacer(Modifier.padding(all = 8.dp))

                    DetailRow("Actor Name:", armor.actor_name)

                    if (armor.effect.isNotEmpty() && !armor.effect.equals("none")) {
                        if (effects.isNotEmpty()) {
                            DetailRowExpandable("Effect:", armor.effect, effects[0], false)
                        } else {
                            ShowSubRow("Effect:", armor.effect)
                        }
                    }

                    if (armor.set_name.isNotEmpty() && !armor.set_name.equals("none")) {
                        DetailRow("Set:", armor.set_name)
                    }

                    if (armor.set_bonus.isNotEmpty() && !armor.set_bonus.equals("none")) {
                        ShowSubRow("Set Bonus:", armor.set_bonus)
                    }

                    DetailRow("Defense:", defense)
                    DetailRow("Buying Price:", "${armor.buying_price} rupees")
                    DetailRow("Selling Price:", sellPrice)
                    if (armor.upgrade_s1.isNotEmpty()) {
                        ShowSubRow("Upgrade 1:", armor.upgrade_s1)
                    }

                    if (armor.upgrade_s2.isNotEmpty()) {
                        ShowSubRow("Upgrade 2:", armor.upgrade_s2)
                    }

                    if (armor.upgrade_s3.isNotEmpty()) {
                        ShowSubRow("Upgrade 3:", armor.upgrade_s3)
                    }

                    if (armor.upgrade_s4.isNotEmpty()) {
                        ShowSubRow("Upgrade 4:", armor.upgrade_s4)
                    }

                    if (armor.total_upgrades.isNotEmpty()) {
                        ShowSubRow("Total Upgrades:", armor.total_upgrades)
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
            Row(Modifier.width(280.dp)) {
                Text(text = name, style = MaterialTheme.typography.titleSmall, softWrap = true)
            }
        }

        @Composable
        fun DetailRow(name: String, value: String) {
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.padding(horizontal = 4.dp))
                Text(text = value, style = MaterialTheme.typography.bodySmall)
            }
        }

        @Composable
        fun DetailRowExpandable(
            name: String,
            value: List<String>,
            effect: Effect,
            showPotency: Boolean
        ) {
            var isExpanded by remember { mutableStateOf(false) }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.padding(horizontal = 4.dp))
                value.forEach {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,
                        //do we pass in an effect to make this take action based on that?
                        modifier = Modifier.clickable { isExpanded = !isExpanded }
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded
            ) {
                Column {
                    if(effect.effect_level1 != null) {
                        ShowEffectRow(effect = effect.effect_level1!!, showPotency)
                    } else {
                        ShowEffectRow(effect = effect, showPotency)
                    }
                    effect.effect_level2?.let {
                        ShowEffectRow(effect = it, showPotency)
                    }
                    effect.effect_level3?.let {
                        ShowEffectRow(effect = it, showPotency)
                    }
                }
            }
        }

        private @Composable
        fun DetailHeader(
            image: Int, title: String, subLabel: String? = null, subtitle: String? = null
        ) {
            Card(
                Modifier
                    .fillMaxWidth(0.9f)
                    .requiredHeight(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(5.dp))
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = title,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Column(
                    Modifier
                        .padding(8.dp)
                        .width(280.dp)
                ) {
                    TitleRow(title)

                    if (subLabel != null) {
                        SubtitleRow(name = "$subLabel $subtitle")
                    }
                }

            }
        }

        private @Composable
        fun DetailHeaderWithEffects(
            image: Int,
            title: String,
            effects: List<Effect>,
            subLabel: String? = null,
            subtitle: String? = null
        ) {
            Card(
                Modifier
                    .fillMaxWidth(0.9f)
                    .requiredHeight(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(5.dp))
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = title,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.Center)
                    )
                    if (effects.isNotEmpty()) {
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

                Column(
                    Modifier
                        .padding(8.dp)
                        .width(280.dp)
                ) {
                    TitleRow(title)

                    if (subLabel != null) {
                        SubtitleRow(name = "$subLabel $subtitle")
                    }
                }

            }
        }

        @Composable
        fun ShowSubRow(title: String, list: List<String>) {

            Row(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            list.toSet().forEach {
                if (it.isNotEmpty()) {
                    Row(
                        Modifier
                            .width(280.dp)
                            .height(IntrinsicSize.Max)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            softWrap = true
                        )
                    }
                }
            }
        }

        @Composable
        fun ShowEffectRow(effect: Effect, showPotency: Boolean) {
            val level = if (effect.level != null) "Level ${effect.level}" else ""
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
            if (showPotency && effect.required_potency != null) {
                EffectRow("Potency: ", effect.required_potency.toString())
                if (effect.highest_potency != null) {
                    EffectRow("Highest Potency:", effect.highest_potency.toString())
                }
            }
        }

        @Composable
        fun EffectRow(title: String, detail: String) {
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

        @Composable
        fun IngredientRow(materials: List<List<Material>>, labelList: List<String>) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .width(320.dp)
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                        shape = MaterialTheme.shapes.medium,
                        shadowElevation = 1.dp,
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        modifier = Modifier
                            .padding(1.dp)
                            .fillMaxWidth(1f)
                    ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.Start
                    ) {
                        for (index in materials.indices) {
                            val mats = materials[index]
                            if(labelList.size > 0) Text(
                                text = labelList[index],
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .height(30.dp)
                                    .wrapContentWidth(unbounded = true)
                            ) {
                                mats.forEach {
                                    Image(
                                        painter = painterResource(id = it.image),
                                        contentDescription = it.name,
                                        modifier = Modifier
                                            .size(30.dp)
                                            .scale(1f)
                                            .padding(1.dp)
                                    )
                                }
                            }
                        }
                    }

                }

            }
        }


    }

    @Preview
    @Composable
    fun PreviewWeaponDetailsView() {
        TearsTheme {
            WeaponDetails(SampleData.weapons[1])
        }
    }

    @Preview
    @Composable
    fun PreviewMealDetailsView() {
        TearsTheme {
            MealDetails(
                meal = SampleData.meals[0],
                findList = { RecipePair("Insect", SampleData.materials.subList(0, 4)) })
        }
    }

    @Preview
    @Composable
    fun PreviewMaterialsDetailsView() {
        TearsTheme {
            MaterialDetails(item = SampleData.materials[1], SampleData.effects)
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
            ArmorDetails(armor = SampleData.armor[0], effects = SampleData.effects)
        }
    }
}