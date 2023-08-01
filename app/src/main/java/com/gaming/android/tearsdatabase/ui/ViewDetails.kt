package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import androidx.annotation.Dimension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
        fun MaterialDetails(material: Material) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(all = 8.dp)) {
                    Image(
                        painter = painterResource(id = material.image),
                        contentDescription = material.name,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    TitleRow(material.name)
                    if (material.effect_type.isNotEmpty() && !material.effect_type.equals("None"))
                        SubtitleRow(name = "Effect Type: ${material.effect_type}")
                    else if (material.hp_recover != 0 && material.hp_recover != null)
                        SubtitleRow(name = "Hp Recover: ${material.hp_recover}")
                    else if (material.additional_damage != -1)
                        SubtitleRow(name = "Additional Damage: ${material.additional_damage}")

                    Spacer(Modifier.padding(all = 8.dp))

                    if (material.additional_damage != -1) {
                        DetailRow(
                            name = "Additional Damage:",
                            value = material.additional_damage.toString()
                        )
                    }
                    if (material.selling_price != null) {
                        DetailRow(
                            name = "Selling Price:",
                            value = material.selling_price.toString()
                        )
                    }
                    if (material.buying_price != null) {
                        DetailRow(
                            name = "Buying Price:",
                            value = material.buying_price.toString()
                        )
                    }
                    if (material.dye_color.isNotEmpty()) {
                        DetailRow(
                            name = "Dye Color:",
                            value = material.dye_color.toString()
                        )
                    }
                    if (material.effect_type.isNotEmpty() && !material.effect_type.equals("None")) {
                        DetailRow(
                            name = "Effect type:",
                            value = material.effect_type.toString()
                        )
                    }
                    if (material.effect_level != null) {
                        DetailRow(
                            name = "Effect level:",
                            value = material.effect_level.toString()
                        )
                    }
                    if (material.effect_time != 0 && material.effect_time != null) {
                        DetailRow(
                            name = "Effect time:",
                            value = material.effect_time.toString()
                        )
                    }
                    if (material.hp_recover != 0 && material.hp_recover != null) {
                        DetailRow(
                            name = "HP Recover:",
                            value = material.hp_recover.toString()
                        )
                    }
                    if (material.additional_damage_rate_arrow != null) {
                        DetailRow(
                            name = "Additional Damage Rate Arrow:",
                            value = material.additional_damage_rate_arrow.toString()
                        )
                    }
                    if (material.sheild_bash_damage != null) {
                        DetailRow(
                            name = "Sheild Bash Damage:",
                            value = material.sheild_bash_damage.toString()
                        )
                    }
                    if (material.boost_effective_time != 0 && material.boost_effective_time != null) {
                        DetailRow(
                            name = "Boost effect time:",
                            value = material.boost_effective_time.toString()
                        )
                    }
                    if (material.boost_hp_recover != 0 && material.boost_hp_recover != null) {
                        DetailRow(
                            name = "Boost HP recover:",
                            value = material.boost_hp_recover.toString()
                        )
                    }
                    if (material.boost_max_heart != 0 && material.boost_max_heart != null) {
                        DetailRow(
                            name = "Boost max heart:",
                            value = material.boost_max_heart.toString()
                        )
                    }
                    if (material.boost_max_stamina != 0 && material.boost_max_stamina != null) {
                        DetailRow(
                            name = "Boost max stamina:",
                            value = material.boost_max_stamina.toString()
                        )
                    }
                    if (material.boost_critical_cook != 0 && material.boost_critical_cook != null) {
                        DetailRow(
                            name = "Boost critical cook:",
                            value = material.boost_critical_cook.toString() + "%"
                        )
                    }
                    if (material.sub_type.isNotEmpty()) {
                        DetailRow(name = "Sub Type:", value = material.sub_type)
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
        fun RoastedFoodDetails(item: RoastedFood) {
            Surface(
                Modifier
                    .requiredWidth(IntrinsicSize.Min)
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

                    DetailRow("Buying Price:", item.buying_price.toString())
                    DetailRow("Selling Price:", item.selling_price.toString())
                    DetailRow("Effect Type:", item.effect_type)
                    DetailRow("Effect Level:", item.effect_level.toString())
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
        fun ArmorDetails(item: Armor) {
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

                Column(modifier = Modifier.padding(all = 8.dp).verticalScroll(rememberScrollState())) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    TitleRow(item.name)
                    SubtitleRow(name = item.location)
                    if(item.coordinates.isNotEmpty()) {
                        Spacer(Modifier.padding(all = 4.dp))
                        SubtitleRow(name = "Coordinates: ${item.coordinates}")
                    }
                    Spacer(Modifier.padding(all = 8.dp))

                    DetailRow("Actor Name:", item.actor_name)

                    if(item.effect.isNotEmpty() && !item.effect.equals("none")) {
                        DetailRow("Effect:", item.effect)
                    }

                    if(item.set_name.isNotEmpty() && !item.set_name.equals("none")) {
                        DetailRow("Set:", item.set_name)
                    }

                    if(item.set_bonus.isNotEmpty() && !item.set_bonus.equals("none")) {
                        DetailRow("Set Bonus:", item.set_bonus)
                    }

                    DetailRow("Defense:", defense)
                    DetailRow("Buying Price:", item.buying_price.toString())
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
                    style = MaterialTheme.typography.titleMedium
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
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )

    @Composable
    fun PreviewDetailsView() {
        TearsTheme {
            WeaponDetails(weapon = SampleData.weapons[1])
        }
    }
    @Preview
    @Composable
    fun PreviewMaterialDetailsView() {
        TearsTheme {
            MaterialDetails(material = SampleData.materials[1])
        }
    }
    @Preview
    @Composable
    fun PreviewBowDetailsView() {
        TearsTheme {
            BowDetails(bow = SampleData.bows[1])
        }
    }

    @Preview
    @Composable
    fun PreviewShieldDetailsView() {
        TearsTheme {
            ShieldDetails(shield = SampleData.shields[1])
        }
    }

    @Preview
    @Composable
    fun PreviewRoastedFoodDetailsView() {
        TearsTheme {
            RoastedFoodDetails(item = SampleData.roastedFood[1])
        }
    }

    @Preview
    @Composable
    fun PreviewMealDetailsView() {
        TearsTheme {
            MealDetails(item = SampleData.meals[1])
        }
    }

    @Preview
    @Composable
    fun PreviewArmorDetailsView() {
        TearsTheme {
            ArmorDetails(item = SampleData.armor[0])
        }
    }
}