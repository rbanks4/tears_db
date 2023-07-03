package com.gaming.android.tearsdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gaming.android.tearsdatabase.databinding.ItemViewBinding
class ItemAdapter (private val itemList: List<Weapon>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private lateinit var binding: ItemViewBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
    class ItemViewHolder(private val itemBinding: ItemViewBinding): RecyclerView.ViewHolder(itemBinding.root) {
//        fun bind(item: Item){
//            itemBinding.itemImage.setImageResource(item.imageResId)
//            itemBinding.itemTitle.setText(item.stringResId)
//        }
        fun bind(item: Weapon){
//            itemBinding.itemImage.setImageResource(item.name)
            itemBinding.itemTitle.setText(item.name)
        }
    }

}