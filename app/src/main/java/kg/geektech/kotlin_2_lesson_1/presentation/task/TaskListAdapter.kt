package kg.geektech.kotlin_2_lesson_1.presentation.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlin_2_lesson_1.databinding.ShopListDisabledBinding
import kg.geektech.kotlin_2_lesson_1.databinding.ShopListEnabledBinding
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import kg.geektech.kotlin_2_lesson_1.utils.Constants

class TaskListAdapter(private val longClickListener: (ShopItem) -> Unit) :
    ListAdapter<ShopItem, RecyclerView.ViewHolder>(ShopItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.FIRST_LAYOUT) {
            val binding =
                ShopListDisabledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderFirst(binding) {
                longClickListener(currentList[it])
            }
        } else {
            val binding =
                ShopListEnabledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderSecond(binding) {
                longClickListener(currentList[it])
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constants.FIRST_LAYOUT) {
            val viewHolderFirst = holder as ViewHolderFirst
            viewHolderFirst.onBind(getItem(position))
        } else {
            val viewHolderSecond = holder as ViewHolderSecond
            viewHolderSecond.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!getItem(position).enabled) {
            Constants.FIRST_LAYOUT
        } else {
            Constants.SECOND_LAYOUT
        }
    }

    class ViewHolderFirst(
        private val binding: ShopListDisabledBinding,
        clickAtPosition: (Int) -> Unit
    ) :
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

    class ViewHolderSecond(
        private val binding: ShopListEnabledBinding,
        clickAtPosition: (Int) -> Unit
    ) :
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