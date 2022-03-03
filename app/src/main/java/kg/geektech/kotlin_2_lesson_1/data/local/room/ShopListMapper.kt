package kg.geektech.kotlin_2_lesson_1.data.local.room

import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItemDbModel

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel {
        return ShopItemDbModel(
            shopItem.id,
            shopItem.name,
            shopItem.count,
            shopItem.enabled
        )
    }

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem {
        return ShopItem(
            shopItemDbModel.name,
            shopItemDbModel.count,
            shopItemDbModel.enabled,
            shopItemDbModel.id
        )
    }

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>): List<ShopItem> {
        return list.map { mapDbModelToEntity(it) }
    }
}