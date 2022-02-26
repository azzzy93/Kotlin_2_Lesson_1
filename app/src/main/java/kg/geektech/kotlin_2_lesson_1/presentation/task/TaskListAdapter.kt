package kg.geektech.kotlin_2_lesson_1.presentation.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlin_2_lesson_1.databinding.ShopListDisabledBinding
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class TaskListAdapter(private val longClickListener: (ShopItem) -> Unit) :
    ListAdapter<ShopItem, TaskListAdapter.ViewHolder>(ShopItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ShopListDisabledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding) {
            longClickListener(currentList[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(private val binding: ShopListDisabledBinding, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                clickAtPosition(absoluteAdapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun onBind(shopItem: ShopItem) {
            binding.tvName.text = shopItem.name
            binding.tvCount.text = shopItem.count.toString()
        }

    }
}