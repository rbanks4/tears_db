package com.gaming.android.tearsdatabase

import android.os.Parcel
import android.os.Parcelable

class WeaponParcel(val name: String?,
                   val shown_attack: Int,
                   val durability: Int,
                   val sub_type: List<String>?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(shown_attack)
        parcel.writeInt(durability)
        parcel.writeStringList(sub_type)
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

    fun toWeapon(): Weapon  {
        return Weapon(name?:"", shown_attack, durability, sub_type?: listOf())
    }

}