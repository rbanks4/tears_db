package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gaming.android.tearsdatabase.databinding.FragmentWeaponDetailsBinding
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.models.WeaponParcel

const val VIEW_MODEL_DETAILS = "weapon_details"
class WeaponDetailsFragment : Fragment(R.layout.fragment_weapon_details) {
    private lateinit var binding: FragmentWeaponDetailsBinding
    private var weapon: Weapon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null)
            weapon = savedInstanceState.getParcelable<WeaponParcel>(VIEW_MODEL_DETAILS)?.toWeapon()
    }

    fun init(newWeapon: Weapon) {
        weapon = newWeapon
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(weapon != null)
            outState.putParcelable(VIEW_MODEL_DETAILS, weapon!!.toParcelable())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeaponDetailsBinding.inflate(inflater, container, false)
        if(weapon != null) {
            binding.image.setImageResource(weapon!!.image?:R.drawable.wooden_stick)
            binding.name.text = weapon!!.name
            binding.damage.text = getString(R.string.weapon_damage, weapon!!.shown_attack)
            binding.durability.text = getString(R.string.weapon_durability, weapon!!.durability)
            binding.subtype.text = getString(R.string.weapon_subtype, weapon!!.sub_type)
        }
        return binding.root
    }
}