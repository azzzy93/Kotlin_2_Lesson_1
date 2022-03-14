package kg.geektech.kotlin_2_lesson_1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kg.geektech.kotlin_2_lesson_1.data.local.room.ShopDao
import kg.geektech.kotlin_2_lesson_1.data.local.room.ShopListMapper
import kg.geektech.kotlin_2_lesson_1.domain.ShopListRepository
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(private val shopDao: ShopDao) :
    ShopListRepository {

    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return Transformations.map(shopDao.getShopList()) {
            mapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopDao.updateShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(id: Int): ShopItem {
        return mapper.mapDbModelToEntity(shopDao.getShopItem(id))
    }
}