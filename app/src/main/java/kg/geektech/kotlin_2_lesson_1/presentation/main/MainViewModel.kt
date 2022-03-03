package kg.geektech.kotlin_2_lesson_1.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kg.geektech.kotlin_2_lesson_1.data.repository.ShopListRepositoryImpl
import kg.geektech.kotlin_2_lesson_1.domain.*
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl()

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val shopList = getShopListUseCase.getShopList()

    fun addShopItem(shopItem: ShopItem) {
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopList
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun getShopItem(id: Int): ShopItem {
        return getShopItemUseCase.getShopItem(id)
    }

    fun editShopItem(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }
}