package kg.geektech.kotlin_2_lesson_1.presentation.task

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.kotlin_2_lesson_1.R
import kg.geektech.kotlin_2_lesson_1.databinding.ActivityTaskBinding
import kg.geektech.kotlin_2_lesson_1.presentation.main.MainViewModel

class TaskActivity : AppCompatActivity(R.layout.activity_task) {

    private val binding: ActivityTaskBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    //    private lateinit var adapter: TaskAdapter
    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRv()
        initObserve()
    }

    private fun initObserve() {
        viewModel.getShopList().observe(this) {
//            adapter.list = it
            adapter.submitList(it)
        }
    }

    private fun setupRv() {
//        adapter = TaskAdapter()
        adapter = TaskListAdapter() {
            viewModel.editShopItem(it.id)
            Toast.makeText(this, "Shop item with id ${it.id} isEnabled: ${it.enabled}", Toast.LENGTH_SHORT).show()
        }
        binding.rvTask.layoutManager = LinearLayoutManager(this)
        binding.rvTask.apply {
            adapter = this@TaskActivity.adapter
            setUpSwipeListener(this)
        }
    }

    private fun setUpSwipeListener(rvTask: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = adapter.list[viewHolder.absoluteAdapterPosition]
                val item = adapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvTask)
    }

}