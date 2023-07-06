package com.gaming.android.tearsdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gaming.android.tearsdatabase.databinding.ItemViewBinding
class ItemAdapter (private var itemList: List<Weapon>, private val controller: FragmentController?) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(), ListUpdater {
    private lateinit var binding: ItemViewBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, controller)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
    class ItemViewHolder(private val itemBinding: ItemViewBinding, private val controller: FragmentController?): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Weapon) {
            val context = itemBinding.root.context
            itemBinding.itemImage.setImageResource(item.getDrawable(context))
            itemBinding.itemTitle.text = item.name

            itemBinding.root.setOnClickListener {
                showDetailsFragment(item)
            }
        }

        private fun showDetailsFragment(weapon: Weapon){
            val f = WeaponDetailsFragment()
            f.init(weapon)
            controller?.transition(f)
        }
    }

    override fun update(weapons: List<Weapon>) {
        println("update called")
        itemList = weapons
        notifyDataSetChanged()
    }

    override fun getList(): List<Weapon> {
        return itemList
    }


}