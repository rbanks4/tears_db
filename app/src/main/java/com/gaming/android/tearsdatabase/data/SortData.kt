package com.gaming.android.tearsdatabase.data

import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.models.*

class SortData {
    companion object {
        fun onWeaponMenuItemSelected(choice: Int, weaponsViewModel: WeaponsViewModel, updateList: (List<Weapon>) -> Unit): List<Weapon>? {
            var listUpdate: List<Weapon>? = null
            val list =
                if(!weaponsViewModel.searchList.isNullOrEmpty())
                    weaponsViewModel.searchList
                else weaponsViewModel.weapons

            when (choice) {
                SORT_DAMAGE_DEC ->
                    listUpdate = list?.sortedByDescending { it.shown_attack }
                SORT_DAMAGE_INC ->
                    listUpdate = list?.sortedBy { it.shown_attack }
                SORT_DURABILITY_DEC ->
                    listUpdate = list?.sortedByDescending { it.durability }
                SORT_DURABILITY_INC ->
                    listUpdate = list?.sortedBy { it.durability }
            }
            return if (!listUpdate.isNullOrEmpty()) {
                updateList(listUpdate)
                listUpdate
            } else {
                weaponsViewModel.weapons
            }
        }

        fun onMaterialMenuItemSelected(choice: Int, materialViewModel: MaterialsViewModel, updateList: (List<Material>) -> Unit): List<Material>? {
            var listUpdate: List<Material>? = null
            val list =
                if(!materialViewModel.searchList.isNullOrEmpty())
                    materialViewModel.searchList
                else materialViewModel.materials

            when (choice) {
                SORT_HP_DEC ->
                    listUpdate = list?.sortedByDescending { it.hp_recover }
                        ?.filter{ it.hp_recover != 0 && it.hp_recover != null }
                SORT_DAMAGE_INC ->
                    listUpdate = list?.sortedBy { it.additional_damage }
                        ?.filter{ it.additional_damage != -1 }
                SORT_DAMAGE_DEC ->
                    listUpdate = list?.sortedByDescending { it.additional_damage }
                        ?.filter{ it.additional_damage != -1 }
                SORT_SELLING_DEC ->
                    listUpdate = list?.sortedByDescending { it.selling_price }
                        ?.filter{ it.selling_price != null }
                SORT_SELLING_INC ->
                    listUpdate = list?.sortedBy { it.selling_price }
                        ?.filter{ it.selling_price != null }
                SORT_BUYING_DEC ->
                    listUpdate = list?.sortedByDescending { it.buying_price?.toInt() }
                        ?.filter{ it.buying_price != null }
                SORT_BUYING_INC ->
                    listUpdate = list?.sortedBy { it.buying_price }
                        ?.filter{ it.buying_price != null }

            }
            return if (!listUpdate.isNullOrEmpty()) {
                updateList(listUpdate)
                listUpdate
            } else {
                materialViewModel.materials
            }
        }

        fun onBowMenuItemSelected(choice: Int, bowViewModel: BowsViewModel, updateList: (List<Bow>) -> Unit): List<Bow>? {
            var listUpdate: List<Bow>? = null
            val list =
                if(!bowViewModel.searchList.isNullOrEmpty())
                    bowViewModel.searchList
                else bowViewModel.bows

            when (choice) {
                SORT_DAMAGE_DEC ->
                    listUpdate = list?.sortedByDescending { it.base_attack }
                SORT_DAMAGE_INC ->
                    listUpdate = list?.sortedBy { it.base_attack }
                SORT_DURABILITY_DEC ->
                    listUpdate = list?.sortedByDescending { it.durability }
                SORT_DURABILITY_INC ->
                    listUpdate = list?.sortedBy { it.durability }
                SORT_DRAWING_TIME_DEC ->
                    listUpdate = list?.sortedByDescending { it.drawing_time }
                SORT_DRAWING_TIME_INC ->
                    listUpdate = list?.sortedBy { it.drawing_time }
                SORT_RELOAD_TIME_DEC ->
                    listUpdate = list?.sortedByDescending { it.reload_time }
                SORT_RELOAD_TIME_INC ->
                    listUpdate = list?.sortedBy { it.reload_time }
                SORT_RANGE_DEC ->
                    listUpdate = list?.sortedByDescending { it.range }
                SORT_RANGE_INC ->
                    listUpdate = list?.sortedBy { it.range }
            }
            return if (!listUpdate.isNullOrEmpty()) {
                updateList(listUpdate)
                listUpdate
            } else {
                bowViewModel.bows
            }
        }

        fun onShieldMenuItemSelected(choice: Int, shieldViewModel: ShieldsViewModel, updateList: (List<Shield>) -> Unit): List<Shield>? {
            var listUpdate: List<Shield>? = null
            val list =
                if(!shieldViewModel.searchList.isNullOrEmpty())
                    shieldViewModel.searchList
                else shieldViewModel.shields

            when (choice) {
                SORT_DURABILITY_DEC ->
                    listUpdate = list?.sortedByDescending { it.durability }
                SORT_DURABILITY_INC ->
                    listUpdate = list?.sortedBy { it.durability }
                SORT_SLIPPERINESS_DEC ->
                    listUpdate = list?.sortedBy { it.shield_surfing_friction }
                SORT_SLIPPERINESS_INC ->
                    listUpdate = list?.sortedByDescending { it.shield_surfing_friction }
            }
            return if (!listUpdate.isNullOrEmpty()) {
                updateList(listUpdate)
                listUpdate
            } else {
                shieldViewModel.shields
            }
        }

        fun onRoastedFoodItemSelected(choice: Int, viewModel: RoastedFoodViewModel, updateList: (List<RoastedFood>) -> Unit): List<RoastedFood>? {
            var listUpdate: List<RoastedFood>? = null
            val list =
                if(!viewModel.searchList.isNullOrEmpty())
                    viewModel.searchList
                else viewModel.roastedFood

            when (choice) {
                SORT_BUYING_INC ->
                    listUpdate = list?.sortedByDescending { it.buying_price }
                SORT_BUYING_DEC ->
                    listUpdate = list?.sortedBy { it.buying_price }
                SORT_SELLING_INC ->
                    listUpdate = list?.sortedBy { it.selling_price }
                SORT_SELLING_DEC ->
                    listUpdate = list?.sortedByDescending { it.selling_price }
                SORT_ID_INC ->
                    listUpdate = list?.sortedByDescending { it.recipe_no }
            }
            return if (!listUpdate.isNullOrEmpty()) {
                updateList(listUpdate)
                listUpdate
            } else {
                viewModel.roastedFood
            }
        }

        fun onMealItemSelected(choice: Int, viewModel: MealsViewModel, updateList: (List<Meal>) -> Unit): List<Meal>? {
            var listUpdate: List<Meal>? = null
            val list =
                if(!viewModel.searchList.isNullOrEmpty())
                    viewModel.searchList
                else viewModel.meals

            when (choice) {
                SORT_ID_INC ->
                    listUpdate = list?.sortedByDescending { it.recipe_no }
            }
            return if (!listUpdate.isNullOrEmpty()) {
                updateList(listUpdate)
                listUpdate
            } else {
                viewModel.meals
            }
        }
    }
}