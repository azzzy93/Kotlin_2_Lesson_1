package kg.geektech.kotlin_2_lesson_1.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.kotlin_2_lesson_1.R
import kg.geektech.kotlin_2_lesson_1.databinding.ActivityDetailBinding
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import kg.geektech.kotlin_2_lesson_1.presentation.MainViewModel

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addShopItem(addItem())
            finish()
        }
    }

    private fun addItem(): ShopItem {
        val name = binding.etName.text.toString()
        val count = binding.etCount.text.toString()

        return if (name.isNotBlank() && count.isNotBlank()) {
            ShopItem(name, count.toInt(), false)
        } else if (name.isNotBlank() && count.isBlank()) {
            ShopItem(name, 7, false)
        } else if (name.isBlank() && count.isNotBlank()) {
            ShopItem("Name Default", count.toInt(), false)
        } else {
            ShopItem("Name Default", 7, false)
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DetailActivity::class.java))
        }
    }
}