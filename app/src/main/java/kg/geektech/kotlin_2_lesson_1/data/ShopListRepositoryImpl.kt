package kg.geektech.kotlin_2_lesson_1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.kotlin_2_lesson_1.domain.ShopListRepository
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import kotlin.random.Random

class ShopListRepositoryImpl : ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private val shopListLd = MutableLiveData<List<ShopItem>>()
    private var autoIncrement = 0

    init {
        for (i in 0..20) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

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
        val oldShopItem = getShopItem(id)
        val newShopItem = oldShopItem.copy(enabled = !oldShopItem.enabled)
        shopList.remove(oldShopItem)
        shopList.add(newShopItem)
        updateList()
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }
}