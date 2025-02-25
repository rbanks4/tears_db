package com.gaming.android.tearsdatabase

import com.gaming.android.tearsdatabase.api.response.ArmorResponse
import com.gaming.android.tearsdatabase.api.response.SubtypeResponse
import com.gaming.android.tearsdatabase.api.response.WeaponsResponse
import com.gaming.android.tearsdatabase.data.ARMOR_JSON
import com.gaming.android.tearsdatabase.data.DataSource
import com.gaming.android.tearsdatabase.data.DataSource.Companion.readJsonBackup
import com.gaming.android.tearsdatabase.models.Subtype
import com.gaming.android.tearsdatabase.models.Weapon
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.io.IOException


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
const val WEAPONS_JSON = "weapons.json"
const val SUBTYPE_JSON = "subtype.json"
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun weaponSubtypeJson() {
        var weapons = createWeapons()
        var subtypeSet = mutableSetOf<String>()
        var subtypes = weapons?.map {
            it.sub_type.forEach { type ->
                type
            }
        }?.toSet()
        weapons?.forEach { weapon ->
            weapon.sub_type.forEach {
                subtypeSet.add(it)
            }
            weapon.sub_type2.forEach {
                subtypeSet.add(it)
            }
        }

        println(subtypeSet)
        //assertTrue(file.exists())
    }

    @Test
    fun subtypeJson() {
        val subtypes = createSubtypes()

        println(subtypes.size)
        subtypes.forEach { subtype ->
            var title = if(subtype.alternate.isNotBlank())
                subtype.alternate else subtype.name
            println("$title")
            if(subtype.description.isNotBlank())
                println("    "+ subtype.description)
        }

        assertTrue(subtypes.size == 32)
    }

    @Test
    fun mergeSubtypesWithWeapons() {
        // we want to do this by id later
        var subtypes = createSubtypes().associateBy { it.name }
        //convert a list into a map if the items have unique ids
        var weapons = createWeapons().map { weapon ->
            weapon.sub_type = weapon.sub_type.map { name ->
                /*
                * if we were to merge these we would need to first go through
                * each one and grab their ids and pair them with a map of subtypes
                * that can be used later*/
                var subtype = subtypes.get(name)
                subtype?.let{ sub ->
                    if(sub.alternate.isNotBlank())
                        sub.alternate else sub.name
                }

            }
            weapon
        }
        println("merge of subtypes complete!!!")
        println(weapons)
    }

    fun createWeapons(): List<Weapon> {
        var json = DataSource.testReadJson(WEAPONS_JSON)
        var weapons = Gson().fromJson(json, WeaponsResponse::class.java).weapons

        return weapons
    }

    fun createSubtypes(): List<Subtype> {
        var json = DataSource.testReadJson(SUBTYPE_JSON)
        var subtypes = Gson().fromJson(json, SubtypeResponse::class.java).subtypes

        return subtypes
    }
}