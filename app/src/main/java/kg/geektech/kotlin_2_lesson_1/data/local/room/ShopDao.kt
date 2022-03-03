package kg.geektech.kotlin_2_lesson_1.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItemDbModel

@Dao
interface ShopDao {

    @Query("SELECT * FROM shop_item")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("SELECT * FROM shop_item WHERE id = :shopItemId LIMIT 1")
    fun getShopItem(shopItemId: Int): ShopItemDbModel

    @Query("DELETE FROM shop_item WHERE id = :shopItemId")
    fun deleteShopItem(shopItemId: Int)
}