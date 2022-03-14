package kg.geektech.kotlin_2_lesson_1.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.kotlin_2_lesson_1.R
import kg.geektech.kotlin_2_lesson_1.databinding.ActivityMainBinding
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import kg.geektech.kotlin_2_lesson_1.presentation.MainViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.getShopList().observe(this) {
            Log.d("Aziz", "GetShopList: $it")
        }
    }

    private fun initListeners() {
        binding.btnGetItem.setOnClickListener {
            val text = binding.etForId.text.toString()
            if (text.isNotBlank()) {
                try {
                    lifecycleScope.launch {
                        val shopItem = viewModel.getShopItem(text.toInt())
                        Log.d("Aziz", "GetShopItem: $shopItem")
                    }
                } catch (e: RuntimeException) {
                    Log.d("Aziz", "Exception: $e")
                }
            }
        }

        binding.btnEditItem.setOnClickListener {
            val text = binding.etForId.text.toString()
            if (text.isNotBlank()) {
                try {
                    viewModel.getShopItem(binding.etForId.text.toString().toInt()).observe(this) {
                        viewModel.editShopItem(it)
                        Log.d("Aziz", "EditShopItem")
                    }
                } catch (e: Exception) {
                    Log.d("Aziz", "Exception: $e")
                }
            }
        }

        binding.btnCreateItem.setOnClickListener {
            viewModel.addShopItem(addItem())
            Log.d("Aziz", "CreateItem: ${addItem()}")
        }

        binding.btnDeleteItem.setOnClickListener {
            try {
                viewModel.getShopItem(binding.etForId.text.toString().toInt()).observe(this) {
                    viewModel.deleteShopItem(it)
                }
            } catch (e: Exception) {
                Log.d("Aziz", "Exception: $e")
            }
        }
    }

    private fun addItem(): ShopItem {
        val text = binding.etForId.text.toString()
        return if (text.isBlank()) {
            ShopItem("Aziz", 2, false)
        } else {
            try {
                ShopItem("Aziz", 2, false, text.toInt())
            } catch (e: Exception) {
                Log.d("Aziz", "Exception: $e")
                ShopItem("Aziz", 2, false)
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}