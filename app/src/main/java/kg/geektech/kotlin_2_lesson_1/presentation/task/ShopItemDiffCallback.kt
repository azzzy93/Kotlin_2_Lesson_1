package kg.geektech.kotlin_2_lesson_1.presentation.task

import androidx.recyclerview.widget.DiffUtil
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem) = oldItem == newItem
}