package kg.geektech.kotlin_2_lesson_1.presentation.task

import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.kotlin_2_lesson_1.R
import kg.geektech.kotlin_2_lesson_1.databinding.ActivityTaskBinding
import kg.geektech.kotlin_2_lesson_1.presentation.MainViewModel
import kg.geektech.kotlin_2_lesson_1.presentation.detail.DetailActivity

@AndroidEntryPoint
class TaskActivity : AppCompatActivity(R.layout.activity_task) {

    private val binding: ActivityTaskBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRv()
        initObserve()
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.search_tool_bar)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Enter ID to search"
        searchView.inputType = InputType.TYPE_CLASS_NUMBER
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.toInt()?.let { id ->
                    try {
                        viewModel.getShopItem(id).observe(this@TaskActivity) { shopItem ->
                            Toast.makeText(
                                this@TaskActivity,
                                shopItem.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@TaskActivity,
                            e.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun initListeners() {
        adapter.onShopItemClick = {
            Toast.makeText(
                applicationContext,
                "Shop item with id ${it.id} isEnabled: ${it.enabled}",
                Toast.LENGTH_SHORT
            ).show()
        }
        adapter.onShopItemLongClick = {
            viewModel.editShopItem(it)
        }
        binding.fab.setOnClickListener {
            DetailActivity.start(this)
        }
    }

    private fun initObserve() {
        viewModel.getShopList().observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setupRv() {
        adapter = TaskListAdapter()
        binding.rvTask.apply {
            layoutManager = LinearLayoutManager(this@TaskActivity)
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
                val item = adapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvTask)
    }

}