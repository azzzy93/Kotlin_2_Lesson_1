package kg.geektech.kotlin_2_lesson_1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_item")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)