package com.gaming.android.tearsdatabase.models.submodels

import androidx.annotation.StringRes
import com.gaming.android.tearsdatabase.R

enum class EffectId(
    val id: Int,
    @StringRes val effectName: Int
) {
    None(0, R.string.e_none),
    Enduring(1, R.string.e_enduring),
    Spicy(2, R.string.e_spicy),
    Electro(3, R.string.e_electro),
    Hasty(4, R.string.e_hasty),
    Sneaky(5, R.string.e_sneaky),
    Fireproof(6, R.string.e_fireproof),
    Tough(7, R.string.e_tough),
    Bright(8, R.string.e_bright),
    Energizing(9, R.string.e_energizing),
    Chilly(10, R.string.e_chilly),
    Sticky(11, R.string.e_sticky),
    Mighty(12, R.string.e_mighty),
    Hearty(13, R.string.e_hearty),
    AttackUpHot(14, R.string.e_atk_up_hot),
    AttackUpCold(15, R.string.e_atk_up_cold),
    AttackUpThunderstorm(16, R.string.e_atk_up_thunder),
    MiasmaGuard(17, R.string.e_miasma_guard),
    SwimSpeedUp(18, R.string.e_swim_speed_up),
    Honey(408, R.string.e_honey),
    Fairy(206, R.string.e_fairy),
    Unique(999, R.string.unique);



    companion object {
        private val map = EffectId.entries.associateBy(EffectId::id)
        var m_id: Int = 0
        fun fromInt(id: Int): EffectId {
            if(id > SwimSpeedUp.id) this.m_id = id
            return map[id]?:Unique
        }
    }
}