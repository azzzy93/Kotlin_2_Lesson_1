package kg.geektech.kotlin_2_lesson_1.domain

class EditShopItemUseCase(private val repository: ShopListRepository) {

    fun editShopItem(id: Int) {
        repository.editShopItem(id)
    }
}