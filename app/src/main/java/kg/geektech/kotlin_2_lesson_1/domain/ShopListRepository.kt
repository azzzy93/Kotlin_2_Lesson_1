package kg.geektech.kotlin_2_lesson_1.domain

import androidx.lifecycle.LiveData
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)
    fun getShopList(): LiveData<List<ShopItem>>
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun getShopItem(id: Int): ShopItem

}