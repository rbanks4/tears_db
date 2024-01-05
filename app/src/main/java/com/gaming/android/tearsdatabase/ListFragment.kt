package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gaming.android.tearsdatabase.api.Endpoints
import com.gaming.android.tearsdatabase.data.DataSource
import com.gaming.android.tearsdatabase.databinding.FragmentListBinding
import com.gaming.android.tearsdatabase.models.Item
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewBuilder
import com.gaming.android.tearsdatabase.viewmodels.ArmorViewModel
import com.gaming.android.tearsdatabase.viewmodels.BowsViewModel
import com.gaming.android.tearsdatabase.viewmodels.EffectViewModel
import com.gaming.android.tearsdatabase.viewmodels.MainViewModel
import com.gaming.android.tearsdatabase.viewmodels.MaterialsViewModel
import com.gaming.android.tearsdatabase.viewmodels.MealsViewModel
import com.gaming.android.tearsdatabase.viewmodels.RoastedFoodViewModel
import com.gaming.android.tearsdatabase.viewmodels.ShieldsViewModel
import com.gaming.android.tearsdatabase.viewmodels.WeaponsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = checkNotNull(_binding) {
        "Cannot access ListFragment binding. Is the view visible?"
    }

    private val viewModel: MainViewModel by viewModels()
    private val weaponsViewModel: WeaponsViewModel by viewModels()
    private val materialViewModel: MaterialsViewModel by viewModels()
    private val bowViewModel: BowsViewModel by viewModels()
    private val shieldViewModel: ShieldsViewModel by viewModels()
    private val roastedFoodViewModel: RoastedFoodViewModel by viewModels()
    private val mealsViewModel: MealsViewModel by viewModels()
    private val armorViewModel: ArmorViewModel by viewModels()
    private val effectViewModel: EffectViewModel by viewModels()

    private lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataSource = DataSource(requireContext())

        Endpoints.fetchMaterials(
            update = { materialViewModel.setup(it) { findDrawable(it)} },
            onFailure = { materialViewModel.setup(dataSource.materialsBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchBows(
            update = { bowViewModel.setup(it) { findDrawable(it)} },
            onFailure = { bowViewModel.setup(dataSource.bowsBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchShields(
            update = { shieldViewModel.setup(it) { findDrawable(it)} },
            onFailure = { shieldViewModel.setup(dataSource.shieldsBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchRoastedFood(
            update = { roastedFoodViewModel.setup(it) { findDrawable(it)} },
            onFailure = { roastedFoodViewModel.setup(dataSource.roastedBackup()) { findDrawable(it)} }
        )
        Endpoints.fetchMeals(
            update = { mealsViewModel.setup(it) { findDrawable(it)} },
            onFailure = { mealsViewModel.setup(dataSource.recipeBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchArmor(
            update = { armorViewModel.setup(it) { findDrawable(it)} },
            onFailure = { armorViewModel.setup(dataSource.armorBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchEffects(
            update = { effectViewModel.setup(it) { findDrawable(it)} },
            onFailure = { effectViewModel.setup(dataSource.effectsBackup()) { findDrawable(it)} }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                weaponsViewModel.weapons.collect { items ->
                    if(items.isEmpty()) {
                        Log.i("lifecycle", "models empty; size: ${items.size}")
                        weaponsViewModel.setup(dataSource.weaponBackup()) { findDrawable(it) }
                    } else {
                        Log.i("lifecycle", "models not empty; size: ${items.size}")
                        weaponsViewModel.setup(items) { findDrawable(it) }
                    }
                }
            }
        }

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            //kill this view when the Fragment is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TearsTheme {
                    ViewBuilder.CreateDrawer(
                        nav = viewModel,
                        weapons = weaponsViewModel,
                        materials = materialViewModel,
                        bows = bowViewModel,
                        shields = shieldViewModel,
                        roastedFoods = roastedFoodViewModel,
                        meals = mealsViewModel,
                        armor = armorViewModel,
                        effects = effectViewModel
                    )
                }
            }
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    fun <T> findDrawable(item: Item<T>): T {
        item.findDrawable(this.requireContext())
        return item.get()
    }

}