package kg.geektech.kotlin_2_lesson_1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kg.geektech.kotlin_2_lesson_1.data.local.room.AppDataBase
import kg.geektech.kotlin_2_lesson_1.data.local.room.ShopDao
import kg.geektech.kotlin_2_lesson_1.data.repository.ShopListRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "dataBase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideShopDao(appDataBase: AppDataBase): ShopDao {
        return appDataBase.shopListDao()
    }

    @Singleton
    @Provides
    fun provideRepository(shopDao: ShopDao): ShopListRepositoryImpl {
        return ShopListRepositoryImpl(shopDao)
    }
}