package kg.geektech.kotlin_2_lesson_1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import kg.geektech.kotlin_2_lesson_1.domain.ShopListRepository

class ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private val shopListLd = MutableLiveData<List<ShopItem>>()
    private var autoIncrement = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrement++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLd
    }

    private fun updateList() {
        shopListLd.value = shopList.toList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(id: Int) {
        val shopItem = shopList[id]
        shopItem.enabled = true
        shopList[id] = shopItem
        updateList()
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList[id]
    }
}