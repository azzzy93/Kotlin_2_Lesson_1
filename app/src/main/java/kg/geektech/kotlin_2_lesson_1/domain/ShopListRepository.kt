package kg.geektech.kotlin_2_lesson_1.domain

import androidx.lifecycle.LiveData
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)
    fun getShopList(): LiveData<List<ShopItem>>
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(id: Int)
    fun getShopItem(id: Int): ShopItem

}