package kg.geektech.kotlin_2_lesson_1.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItemDbModel

@Dao
interface ShopDao {

    @Query("SELECT * FROM shop_item")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("SELECT * FROM shop_item WHERE id = :shopItemId LIMIT 1")
    suspend fun getShopItem(shopItemId: Int): ShopItemDbModel

    @Query("DELETE FROM shop_item WHERE id = :shopItemId")
    suspend fun deleteShopItem(shopItemId: Int)

    @Update
    suspend fun updateShopItem(shopItemDbModel: ShopItemDbModel)
}