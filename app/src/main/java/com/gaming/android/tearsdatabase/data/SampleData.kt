package com.gaming.android.tearsdatabase.data

import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.models.submodels.CookId.*
import com.gaming.android.tearsdatabase.models.submodels.EffectId.*

object SampleData {
    val weapons = listOf(
        Weapon(1,"Boat Oar", 3, 3, 3, 3, 3, listOf("Blunt"), 3, 3, listOf("Blunt"), 3, 3).setDrawable(
            R.drawable.boat_oar
        ),
        Weapon(2,
            "Bokoblin Arm",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.bokoblin_arm),
        Weapon(3,
            "Boomerang",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.boomerang),
        Weapon(4,
            "Cobble Crusher (Intact)",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.cobble_crusher_intact),
        Weapon(5,
            "Feathered Edge",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.feathered_edge),
        Weapon(6,
            "Forest Dweller's Spear",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.forest_dwellers_spear),
        Weapon(7,
            "Gloom Sword",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.gloom_sword),
        Weapon(8,
            "Knight's Claymore",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.knights_claymore),
        Weapon(9,
            "Lizal Boomerang",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.lizal_boomerang),
        Weapon(10,
            "Gnarled Wooden Stick",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.gnarled_wooden_stick),
        Weapon(11,
            "Gerudo Spear (Intact)",
            3,
            3,
            3,
            3,
            3,
            listOf("Blunt"),
            3,
            3,
            listOf("Blunt"),
            3,
            3
        ).setDrawable(R.drawable.gerudo_spear_intact)
    )

    val materials = listOf(
        Material(
            1,
            "Hot Footed Frog",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Hasty.id
        )
            .setDrawable(R.drawable.hot_footed_frog),
        Material(
            2,
            "Energetic Rino Beetle",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Hasty.id
        )
            .setDrawable(R.drawable.energetic_rhino_beetle),
        Material(
            3,
            "Smotherwing Butterfly",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Fireproof.id
        )
            .setDrawable(R.drawable.smotherwing_butterfly),
        Material(
            4,
            "Sticky Frog",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Sticky.id
        )
            .setDrawable(R.drawable.sticky_frog),
        Material(
            5,
            "Sticky Lizard",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Sticky.id
        )
            .setDrawable(R.drawable.sticky_lizard),
        Material(
            6,
            "Deep Firefly",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Bright.id
        )
            .setDrawable(R.drawable.deep_firefly),
        Material(
            7,
            "Tireless Frog",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Enduring.id
        )
            .setDrawable(R.drawable.tireless_frog), Material(
            8,
            "Cold Darner",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Chilly.id
        )
            .setDrawable(R.drawable.cold_darner),
        Material(
            9,
            "Sunset Firefly",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Spicy.id
        )
            .setDrawable(R.drawable.sunset_firefly),
        Material(
            9,
            "Sunset Firefly",
            3,
            3,
            "3",
            "blue",
            "fire",
            7,
            3,
            3,
            "",
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            Insect.id,
            Spicy.id
        )
            .setDrawable(R.drawable.sunset_firefly),

    )

    val bows = listOf(
        Bow(1,"Traveler's Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.travelers_bow),
        Bow(2,"Soldier's Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.soldiers_bow),
        Bow(3,"Spiked Boko Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.spiked_boko_bow),
        Bow(4,"Boko Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.boko_bow),
        Bow(5,"Lizal Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.lizal_bow),
        Bow(6,"Lynel Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.lynel_bow),
        Bow(7,"Strengtheneed Lizal Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.strengthened_lizal_bow),
        Bow(8,"Forest Dweller's Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.forest_dwellers_bow),
        Bow(9,"Zora Bow", 3, 3, 3, 3, 3.3f, 3.3f, listOf("Bone"), 3, listOf("Blunt"), "IsShotBow")
            .setDrawable(R.drawable.zora_bow)
    )

    val shields = listOf(
        Shield(1,"Traveler's Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.travelers_shield),
        Shield(2, "Soldier's Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.soldiers_shield),
        Shield(3, "Spiked Boko Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.spiked_boko_shield),
        Shield(4, "Boko Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.boko_shield),
        Shield(5, "Lizal Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.lizal_shield),
        Shield(6, "Lynel Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.lynel_shield),
        Shield(7, "Savage Lynel Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.savage_lynel_shield),
        Shield(8, "Zonaite Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.zonaite_shield),
        Shield(9, "Zora Shield", 3, 3, 3, 3.3f, 3.3f, listOf("Blunt"), 3, listOf("GuardEnhance"))
            .setDrawable(R.drawable.zora_shield)
    )

    val roastedFood = listOf(
        RoastedFood(1,
            "Item_Boiled_A_01",
            "Hard-Boiled Egg",
            1,
            3,
            3,
            3,
            "None",
            3,
            3,
            "Blunt",
            3,
            "blue"
        )
            .setDrawable(R.drawable.hard_boiled_egg),
        RoastedFood(2,
            "Item_Boiled_A_01",
            "Hard-Boiled Egg",
            1,
            3,
            3,
            3,
            "None",
            3,
            3,
            "Blunt",
            3,
            "blue"
        )
            .setDrawable(R.drawable.hard_boiled_egg),
        RoastedFood(3,
            "Item_Boiled_A_01",
            "Hard-Boiled Egg",
            1,
            3,
            3,
            3,
            "None",
            3,
            3,
            "Blunt",
            3,
            "blue"
        )
            .setDrawable(R.drawable.hard_boiled_egg),
        RoastedFood(4,
            "Item_Boiled_A_01",
            "Hard-Boiled Egg",
            1,
            3,
            3,
            3,
            "None",
            3,
            3,
            "Blunt",
            3,
            "blue"
        )
            .setDrawable(R.drawable.hard_boiled_egg)
    )

    val meals = listOf(
        Meal(
            12,
            "Item_Cooked_A_01",
            "Mushroom Skewer",
            1,
            listOf(
                listOf(12, 2),
                listOf(3, 0),
                listOf(2, 0, 4, 0, 12, 425),
                listOf(12, 3),
                listOf(12, 7)
            ),
            3,
            3,
            3
        )
            .setDrawable(R.drawable.mushroom_skewer),
        Meal(
            13,
            "Item_Cooked_A_01",
            "Mushroom Skewer",
            1,
            listOf(listOf(2, 0), listOf(2, 0)),
            3,
            3,
            3
        )
            .setDrawable(R.drawable.carrot_cake),
        Meal(
            14,
            "Item_Cooked_A_01",
            "Mushroom Skewer",
            1,
            listOf(listOf(2, 0), listOf(2, 0)),
            3,
            3,
            3
        )
            .setDrawable(R.drawable.omelet),
        Meal(
            15,
            "Item_Cooked_A_01",
            "Mushroom Skewer",
            1,
            listOf(listOf(2, 0), listOf(2, 0)),
            3,
            3,
            3
        )
            .setDrawable(R.drawable.creamy_heart_soup)
    )

    val armor = listOf(
        Armor(1,
            "Armor_001_Head",
            "Barbarian Helm",
            "Barbarian",
            listOf("AttackUp"),
            emptyList(),
            3,
            0,
            3,
            6,
            9,
            12,
            15,
            15,
            65,
            165,
            365,
            865,
            listOf("5 Bokoblin Horns", "3 Bokoblin Fangs"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 50 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 200 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 500 rupees"),
            listOf("20 Bokoblin Horns, 30 Bokoblin Fangs, 1500 rupees"),
            "Royal Hidden Passage (entrance location -0253,0764,0087), break rocks near electric Like-Like. Enter prison cell and move boulder to climb to tunnel to cell on the other side.",
            "-0267, 0638, -0035"
        ).setDrawable(R.drawable.barbarian_helm),
        Armor(2,
            "Armor_001_Head",
            "Hylian Hood",
            "Hylia",
            emptyList(),
            emptyList(),
            3,
            0,
            3,
            6,
            9,
            12,
            15,
            15,
            65,
            165,
            365,
            865,
            listOf("5 Bokoblin Horns", "3 Bokoblin Fangs"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 50 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 200 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 500 rupees"),
            listOf("20 Bokoblin Horns, 30 Bokoblin Fangs, 1500 rupees"),
            "Lookout Landing (Shop)",
            ""
        ).setDrawable(R.drawable.hylian_hood),
        Armor(3,
            "Armor_001_Head",
            "Hylian Hood",
            "Hylia",
            emptyList(),
            emptyList(),
            3,
            0,
            3,
            6,
            9,
            12,
            15,
            15,
            65,
            165,
            365,
            865,
            listOf("5 Bokoblin Horns", "3 Bokoblin Fangs"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 50 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 200 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 500 rupees"),
            listOf("20 Bokoblin Horns, 30 Bokoblin Fangs, 1500 rupees"),
            "Lookout Landing (Shop)",
            ""
        ).setDrawable(R.drawable.hylian_hood),
        Armor(4,
            "Armor_001_Head",
            "Hylian Hood",
            "Hylia",
            emptyList(),
            emptyList(),
            3,
            0,
            3,
            6,
            9,
            12,
            15,
            15,
            65,
            165,
            365,
            865,
            listOf("5 Bokoblin Horns", "3 Bokoblin Fangs"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 50 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 200 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 500 rupees"),
            listOf("20 Bokoblin Horns, 30 Bokoblin Fangs, 1500 rupees"),
            "Lookout Landing (Shop)",
            ""
        ).setDrawable(R.drawable.hylian_hood),
        Armor(5,
            "Armor_001_Head",
            "Hylian Hood",
            "Hylia",
            emptyList(),
            emptyList(),
            3,
            0,
            3,
            6,
            9,
            12,
            15,
            15,
            65,
            165,
            365,
            865,
            listOf("5 Bokoblin Horns", "3 Bokoblin Fangs"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 50 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 200 rupees"),
            listOf("5 Bokoblin Horns, 3 Bokoblin Fangs, 500 rupees"),
            listOf("20 Bokoblin Horns, 30 Bokoblin Fangs, 1500 rupees"),
            "Lookout Landing (Shop)",
            ""
        ).setDrawable(R.drawable.hylian_hood),
    )

    val effects = listOf(
        Effect("AttackUp", "Attack Up", 1, null, "1-27 yellow hearts", 1, 108, 0, true).setDrawable(
            R.drawable.attackup
        ),
        Effect(
            "LifeMaxUp",
            "Extra Hearts",
            4,
            108,
            "1-27 yellow hearts",
            1,
            108,
            0,
            false
        ).setDrawable(R.drawable.lifemaxup)
    )

    val effectMap = mapOf(
        Pair(
            "AttackUp",
            Effect(
                "AttackUp",
                "Attack Up",
                1,
                null,
                "1-27 yellow hearts",
                1,
                108,
                0,
                true
            ).setDrawable(R.drawable.attackup)
        ),
        Pair(
            "LifeMaxUp",
            Effect(
                "LifeMaxUp",
                "Extra Hearts",
                4,
                108,
                "1-27 yellow hearts",
                1,
                108,
                0,
                false
            ).setDrawable(R.drawable.lifemaxup)
        )
    )
}