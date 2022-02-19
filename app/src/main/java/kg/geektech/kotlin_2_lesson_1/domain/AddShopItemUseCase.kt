package kg.geektech.kotlin_2_lesson_1.domain

import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class AddShopItemUseCase(private val repository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {
        repository.addShopItem(shopItem)
    }

}