package kg.geektech.kotlin_2_lesson_1.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.kotlin_2_lesson_1.R
import kg.geektech.kotlin_2_lesson_1.databinding.ActivityMainBinding
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    val shopItem = viewModel.getShopItem(text.toInt())
                    Log.d("Aziz", "GetShopItem: $shopItem")
                } catch (e: Exception) {
                    Log.d("Aziz", "Exception: $e")
                }
            }
        }

        binding.btnEditItem.setOnClickListener {
            val text = binding.etForId.text.toString()
            if (text.isNotBlank()) {
                try {
                    viewModel.editShopItem(text.toInt())
                    Log.d("Aziz", "EditShopItem")
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
            viewModel.deleteShopItem(
                try {
                    ShopItem(
                        "Aziz",
                        2,
                        false,
                        binding.etForId.text.toString().toInt()
                    )
                } catch (e:Exception) {
                    Log.d("Aziz", "Exception: $e")
                    ShopItem(
                        "Aziz",
                        2,
                        false
                    )
                }
            )
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
}