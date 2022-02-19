package kg.geektech.kotlin_2_lesson_1.domain

import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class GetShopItemUseCase(private val repository: ShopListRepository) {

    fun getShopItem(id: Int): ShopItem {
        return repository.getShopItem(id)
    }
}