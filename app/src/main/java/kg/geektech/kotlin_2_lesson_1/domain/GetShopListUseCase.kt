package kg.geektech.kotlin_2_lesson_1.domain

import androidx.lifecycle.LiveData
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class GetShopListUseCase(private val repository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return repository.getShopList()
    }
}