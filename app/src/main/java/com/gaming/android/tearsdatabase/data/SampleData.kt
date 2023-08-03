package com.gaming.android.tearsdatabase.data

import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.models.*

object SampleData {
    val weapons = listOf(
        Weapon("Boat Oar", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.boat_oar),
        Weapon("Bokoblin Arm", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.bokoblin_arm),
        Weapon("Boomerang", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.boomerang),
        Weapon("Cobble Crusher (Intact)", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.cobble_crusher_intact),
        Weapon("Feathered Edge", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.feathered_edge),
        Weapon("Forest Dweller's Spear", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.forest_dwellers_spear),
        Weapon("Gloom Sword", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.gloom_sword),
        Weapon("Knight's Claymore", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.knights_claymore),
        Weapon("Lizal Boomerang", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.lizal_boomerang),
        Weapon("Gnarled Wooden Stick", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.gnarled_wooden_stick),
        Weapon("Gerudo Spear (Intact)", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3).setDrawable(R.drawable.gerudo_spear_intact)
    )

    val materials = listOf(
        Material("Hot Footed Frog", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.hot_footed_frog),
        Material("Energetic Rino Beetle", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.energetic_rhino_beetle),
        Material("Smotherwing Butterfly", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.smotherwing_butterfly),
        Material("Sticky Frog", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.sticky_frog),
        Material("Sticky Lizard", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.sticky_lizard),
        Material("Deep Firefly", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.deep_firefly),
        Material("Tireless Frog", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.tireless_frog),
        Material("Cold Darner", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.cold_darner),
        Material("Sunset Firefly", 3, 3, "3", "blue", "fire",7, 3, 3, "", 3, 3, 3,3,3,3,3)
            .setDrawable(R.drawable.sunset_firefly)

    )

    val bows = listOf(
        Bow("Traveler's Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.travelers_bow),
        Bow("Soldier's Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.soldiers_bow),
        Bow("Spiked Boko Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.spiked_boko_bow),
        Bow("Boko Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.boko_bow),
        Bow("Lizal Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.lizal_bow),
        Bow("Lynel Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.lynel_bow),
        Bow("Strengtheneed Lizal Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.strengthened_lizal_bow),
        Bow("Forest Dweller's Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.forest_dwellers_bow),
        Bow("Zora Bow", 3, 3, 3, 3, 3.3f, 3.3f, "Bone", 3, "Blunt", "IsShotBow")
            .setDrawable(R.drawable.zora_bow)
    )

    val shields = listOf(
        Shield("Traveler's Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.travelers_shield),
        Shield("Soldier's Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.soldiers_shield),
        Shield("Spiked Boko Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.spiked_boko_shield),
        Shield("Boko Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.boko_shield),
        Shield("Lizal Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.lizal_shield),
        Shield("Lynel Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.lynel_shield),
        Shield("Savage Lynel Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.savage_lynel_shield),
        Shield("Zonaite Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.zonaite_shield),
        Shield("Zora Shield", 3, 3, 3, 3.3f, 3.3f, "Blunt", 3, "3.3f")
            .setDrawable(R.drawable.zora_shield)
    )

    val roastedFood = listOf(
        RoastedFood("Item_Boiled_A_01", "Hard-Boiled Egg", 1, 3, 3, 3, "None", 3, 3, "Blunt", 3, "blue")
            .setDrawable(R.drawable.hard_boiled_egg),
        RoastedFood("Item_Boiled_A_01", "Hard-Boiled Egg", 1, 3, 3, 3, "None", 3, 3, "Blunt", 3, "blue")
            .setDrawable(R.drawable.hard_boiled_egg),
        RoastedFood("Item_Boiled_A_01", "Hard-Boiled Egg", 1, 3, 3, 3, "None", 3, 3, "Blunt", 3, "blue")
            .setDrawable(R.drawable.hard_boiled_egg),
        RoastedFood("Item_Boiled_A_01", "Hard-Boiled Egg", 1, 3, 3, 3, "None", 3, 3, "Blunt", 3, "blue")
            .setDrawable(R.drawable.hard_boiled_egg)
    )

    val meals = listOf(
        Meal("Item_Cooked_A_01", "Mushroom Skewer", 1, "CookMushroom and CookMushroom", 3, 3, 3)
            .setDrawable(R.drawable.mushroom_skewer),
        Meal("Item_Cooked_A_01", "Mushroom Skewer", 1, "CookMushroom and CookMushroom", 3, 3, 3)
            .setDrawable(R.drawable.mushroom_skewer),
        Meal("Item_Cooked_A_01", "Mushroom Skewer", 1, "CookMushroom and CookMushroom", 3, 3, 3)
            .setDrawable(R.drawable.mushroom_skewer),
        Meal("Item_Cooked_A_01", "Mushroom Skewer", 1, "CookMushroom and CookMushroom", 3, 3, 3)
            .setDrawable(R.drawable.mushroom_skewer)
    )

    val armor = listOf(
        Armor("Armor_001_Head", "Hylian Hood", "Hylia", "none", "none",
            3, 0, 3, 6, 9, 12, 15, 15, 65, 165, 365, 865,
            "5 Bokoblin Horns\n 3 Bokoblin Fangs", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 50 rupees", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 200 rupees",
            "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 500 rupees", "20 Bokoblin Horns\n 30 Bokoblin Fangs\n 1500 rupees",
            "Royal Hidden Passage (entrance location -0253,0764,0087), break rocks near electric Like-Like. Enter prison cell and move boulder to climb to tunnel to cell on the other side.", "-0267, 0638, -0035").setDrawable(R.drawable.hylian_hood),
        Armor("Armor_001_Head", "Hylian Hood", "Hylia", "none", "none",
            3, 0, 3, 6, 9, 12, 15, 15, 65, 165, 365, 865,
            "5 Bokoblin Horns\n 3 Bokoblin Fangs", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 50 rupees", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 200 rupees",
            "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 500 rupees", "20 Bokoblin Horns\n 30 Bokoblin Fangs\n 1500 rupees", "Lookout Landing (Shop)", "").setDrawable(R.drawable.hylian_hood),
        Armor("Armor_001_Head", "Hylian Hood", "Hylia", "none", "none",
            3, 0, 3, 6, 9, 12, 15, 15, 65, 165, 365, 865,
            "5 Bokoblin Horns\n 3 Bokoblin Fangs", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 50 rupees", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 200 rupees",
            "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 500 rupees", "20 Bokoblin Horns\n 30 Bokoblin Fangs\n 1500 rupees", "Lookout Landing (Shop)", "").setDrawable(R.drawable.hylian_hood),
        Armor("Armor_001_Head", "Hylian Hood", "Hylia", "none", "none",
            3, 0, 3, 6, 9, 12, 15, 15, 65, 165, 365, 865,
            "5 Bokoblin Horns\n 3 Bokoblin Fangs", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 50 rupees", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 200 rupees",
            "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 500 rupees", "20 Bokoblin Horns\n 30 Bokoblin Fangs\n 1500 rupees", "Lookout Landing (Shop)", "").setDrawable(R.drawable.hylian_hood),
        Armor("Armor_001_Head", "Hylian Hood", "Hylia", "none", "none",
            3, 0, 3, 6, 9, 12, 15, 15, 65, 165, 365, 865,
            "5 Bokoblin Horns\n 3 Bokoblin Fangs", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 50 rupees", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 200 rupees",
            "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 500 rupees", "20 Bokoblin Horns\n 30 Bokoblin Fangs\n 1500 rupees", "Lookout Landing (Shop)", "").setDrawable(R.drawable.hylian_hood),
        Armor("Armor_001_Head", "Hylian Hood", "Hylia", "none", "none",
            3, 0, 3, 6, 9, 12, 15, 15, 65, 165, 365, 865,
            "5 Bokoblin Horns\n 3 Bokoblin Fangs", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 50 rupees", "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 200 rupees",
            "5 Bokoblin Horns\n 3 Bokoblin Fangs\n 500 rupees", "20 Bokoblin Horns\n 30 Bokoblin Fangs\n 1500 rupees", "Lookout Landing (Shop)", "").setDrawable(R.drawable.hylian_hood),
    )
}