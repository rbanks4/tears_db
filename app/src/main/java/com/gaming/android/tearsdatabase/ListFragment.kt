package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                /**
                 * ~~~~~ PERSONAL NOTES ~~~~~
                 * might be best to separate each list view into their own fragment but this will require:
                 * 1. todo a deconstruction of compose elements like "CreateDrawer"
                 * 2. todo a DetailViewFragment along with a fragment for each Item {weapons, materials, bows, ...}
                 * 3. todo navi logic to navigate to each view
                 * 4. optional graphical improvements to the drawer and themes that are more appealing
                 * 5. todo proper persistent storage -- let's use Room since I plan to work with LiveData and View Models
                 *    Realm:
                 *      Pros
                 *        a) serverless architecture
                 *        b) flexible data modeling
                 *      Cons
                 *        a) learning curve - difficult to setup
                 *        b) expensive - cost money if you want to get serious
                 *
                 *    Room:
                 *      Pros
                 *        a) LiveData + ViewModel integration
                 *        b) conventional structure -- simple readable code
                 *      Cons
                 *        a) no encryption built in for user data (we aren't using user data tho -- don't plan to)
                 *        b) not cross platform friendly with MongoDB and other services
                 *
                 *    Conclusion: Using ROOM. This is a small project that won't be using user data and is for learning purposes only.
                 *    The main goal of this project is to follow conventional standards and Room has that down.
                 *    Next app I make will use Realm just to see how it works with MongoDB tools and analytics.
                 *
                 */

                //weapons
                launch {
                    weaponsViewModel.weapons.collect { items ->
                        if (items.isEmpty()) {
                            weaponsViewModel.setup(dataSource.weaponBackup()) { findDrawable(it) }
                        } else {
                            weaponsViewModel.setup(items) { findDrawable(it) }
                        }
                    }
                }

                //materials
                launch {
                    materialViewModel.materials.collect { items ->
                        if(items.isEmpty()) {
                            materialViewModel.setup(dataSource.materialsBackup()) { findDrawable(it) }
                        } else {
                            materialViewModel.setup(items) { findDrawable(it) }
                        }
                    }
                }

                //bows
                launch {
                    bowViewModel.bows.collect { items ->
                        if(items.isEmpty()) {
                            bowViewModel.setup(dataSource.bowsBackup()) { findDrawable(it) }
                        } else {
                            bowViewModel.setup(items) { findDrawable(it) }
                        }
                    }
                }

                //roasted food
                launch {
                    roastedFoodViewModel.roastedFood.collect { items ->
                        if(items.isEmpty()) {
                            roastedFoodViewModel.setup(dataSource.roastedBackup()) { findDrawable(it) }
                        } else {
                            roastedFoodViewModel.setup(items) { findDrawable(it) }
                        }
                    }
                }

                //meals
                launch {
                    mealsViewModel.meals.collect { items ->
                        if(items.isEmpty()) {
                            mealsViewModel.setup(dataSource.recipeBackup()) { findDrawable(it) }
                        } else {
                            mealsViewModel.setup(items) { findDrawable(it) }
                        }
                    }
                }

                //shields
                launch {
                    shieldViewModel.shields.collect { items ->
                        if(items.isEmpty()) {
                            shieldViewModel.setup(dataSource.shieldsBackup()) { findDrawable(it) }
                        } else {
                            shieldViewModel.setup(items) { findDrawable(it) }
                        }
                    }
                }

                //effects
                launch {
                    effectViewModel.effects.collect { items ->
                        if(items.isEmpty()) {
                            effectViewModel.setup(dataSource.effectsBackup()) { findDrawable(it) }
                        } else {
                            effectViewModel.setup(items) { findDrawable(it) }
                        }
                    }
                }

                //armor
                launch {
                    armorViewModel.armor.collect { items ->
                        if(items.isEmpty()) {
                            armorViewModel.setup(dataSource.armorBackup()) { findDrawable(it) }
                        } else {
                            armorViewModel.setup(items) { findDrawable(it) }
                        }
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