package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gaming.android.tearsdatabase.databinding.FragmentWeaponListBinding

class WeaponListFragment(var weapons: List<Weapon>, var controller: FragmentController): Fragment(R.layout.fragment_weapon_list) {
    private lateinit var bind: FragmentWeaponListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentWeaponListBinding.inflate(inflater, container,false)
        bind.mainList.adapter = ItemAdapter(weapons, controller)
        bind.mainList.layoutManager = GridLayoutManager(bind.root.context, 3)
        return bind.root
    }
}