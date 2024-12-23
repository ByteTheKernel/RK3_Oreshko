package com.example.rk_3.data

interface FoodRepository {
    suspend fun getFoodList(): List<Food>
    suspend fun getFoodById(id: String): Food?
}