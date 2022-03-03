package kg.geektech.kotlin_2_lesson_1.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItemDbModel

@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun shopListDao(): ShopDao

}