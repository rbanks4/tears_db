package com.gaming.android.tearsdatabase.models

import android.os.Parcel
import android.os.Parcelable

class WeaponParcel(val name: String?,
                   val compendium_no: Int,
                   val base_attack: Int,
                   val shown_attack: Int,
                   val durability: Int,
                   val guard_break_power: Int,
                   val sub_type: String?,
                   val fuse_durability: Int,
                   val fuse_damage: Int,
                   val sub_type2: String?,
                   val attach_zoani_attk: Int,
                   val shield_bash_damage: Int
                   ) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(compendium_no)
        parcel.writeInt(base_attack)
        parcel.writeInt(shown_attack)
        parcel.writeInt(durability)
        parcel.writeInt(guard_break_power)
        parcel.writeString(sub_type)
        parcel.writeInt(fuse_durability)
        parcel.writeInt(fuse_damage)
        parcel.writeString(sub_type2)
        parcel.writeInt(attach_zoani_attk)
        parcel.writeInt(shield_bash_damage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeaponParcel> {
        override fun createFromParcel(parcel: Parcel): WeaponParcel {
            return WeaponParcel(parcel)
        }

        override fun newArray(size: Int): Array<WeaponParcel?> {
            return arrayOfNulls(size)
        }
    }
    fun toWeapon(): Weapon {
        return Weapon(name?:"", compendium_no, base_attack, shown_attack, durability, guard_break_power, sub_type?: "", fuse_durability, fuse_damage, sub_type2?: "", attach_zoani_attk, shield_bash_damage)
    }

}