package kg.geektech.kotlin_2_lesson_1.domain

import androidx.lifecycle.LiveData
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)
    fun getShopList(): LiveData<List<ShopItem>>
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun getShopItem(id: Int): ShopItem

}