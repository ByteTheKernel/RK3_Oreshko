package com.example.rk_3.viewModel

import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rk_3.data.model.Recipe
import com.example.rk_3.data.network.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        loadRecipes()
    }

    fun loadRecipes() {

        _errorMessage.value = null // Сброс ошибки перед загрузкой

        viewModelScope.launch {
            try {
                _isLoading.value = true
                // Добавляем задержку перед началом загрузки
                delay(500) // 500 мс
                val response = RetrofitInstance.api.getRecipes()
                _recipes.value = response.recipes
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load recipes: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}