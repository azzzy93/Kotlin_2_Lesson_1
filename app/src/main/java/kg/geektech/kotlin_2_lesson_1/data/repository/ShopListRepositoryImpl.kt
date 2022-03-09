package kg.geektech.kotlin_2_lesson_1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kg.geektech.kotlin_2_lesson_1.App
import kg.geektech.kotlin_2_lesson_1.data.local.room.ShopListMapper
import kg.geektech.kotlin_2_lesson_1.domain.ShopListRepository
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem

class ShopListRepositoryImpl : ShopListRepository {

    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return Transformations.map(App.dataBase.shopListDao().getShopList()) {
            mapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().updateShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(id: Int): ShopItem {
        return mapper.mapDbModelToEntity(App.dataBase.shopListDao().getShopItem(id))
    }
}