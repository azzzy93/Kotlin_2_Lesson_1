package kg.geektech.kotlin_2_lesson_1.domain

import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class EditShopItemUseCase(private val repository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem) {
        repository.editShopItem(shopItem)
    }
}