package kg.geektech.kotlin_2_lesson_1.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.kotlin_2_lesson_1.data.repository.ShopListRepositoryImpl
import kg.geektech.kotlin_2_lesson_1.domain.*
import kg.geektech.kotlin_2_lesson_1.domain.model.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ShopListRepositoryImpl): ViewModel() {

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun addShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun getShopList(): LiveData<List<ShopItem>> {
        return getShopListUseCase.getShopList()
    }

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun getShopItem(id: Int): LiveData<ShopItem> {
        val shopItem: MutableLiveData<ShopItem> = MutableLiveData()
        viewModelScope.launch {
            shopItem.postValue(getShopItemUseCase.getShopItem(id))
        }
        return shopItem
    }

    fun editShopItem(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }
}