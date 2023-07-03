package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gaming.android.tearsdatabase.databinding.FragmentWeaponDetailsBinding

class WeaponDetailsFragment(var weapon: Weapon) : Fragment(R.layout.fragment_weapon_details) {
    private lateinit var binding: FragmentWeaponDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeaponDetailsBinding.inflate(inflater, container, false)
        binding.image.setImageResource(weapon.image)
        binding.name.text = weapon.name
        binding.damage.text = getString(R.string.weapon_damage, weapon.shown_attack)
        binding.durability.text = getString(R.string.weapon_durability, weapon.durability)
        binding.subtype.text = getString(R.string.weapon_subtype, weapon.sub_type)
        return binding.root
    }
}