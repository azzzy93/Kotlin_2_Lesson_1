package kg.geektech.kotlin_2_lesson_1.domain

import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class DeleteShopItemUseCase(private val repository: ShopListRepository) {

    suspend fun deleteShopItem(shopItem: ShopItem) {
        repository.deleteShopItem(shopItem)
    }
}