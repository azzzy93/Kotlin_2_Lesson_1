package kg.geektech.kotlin_2_lesson_1

import android.app.Application
import androidx.room.Room
import kg.geektech.kotlin_2_lesson_1.data.local.room.AppDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(this, AppDataBase::class.java, "dataBase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        lateinit var dataBase: AppDataBase
    }
}