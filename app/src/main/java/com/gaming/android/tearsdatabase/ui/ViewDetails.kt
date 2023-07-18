package com.gaming.android.tearsdatabase.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon
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
            Row {
                Text(text = name, style = MaterialTheme.typography.titleSmall)
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
}