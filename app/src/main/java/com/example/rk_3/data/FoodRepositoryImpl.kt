package com.example.rk_3.data

import kotlinx.coroutines.delay

class FoodRepositoryImpl : FoodRepository {
    private val foodList = listOf(
        Food("1", "Pizza", "Delicious cheese pizza", "https://picsum.photos/200/200?random=1"),
        Food("2", "Burger", "Juicy beef burger", "https://picsum.photos/200/200?random=2"),
        Food("3", "Pasta", "Creamy Alfredo pasta", "https://picsum.photos/200/200?random=3")
    )

    override suspend fun getFoodList(): List<Food> {
        delay(2000) // Simulate network delay
        return foodList
    }

    override suspend fun getFoodById(id: String): Food? {
        delay(2000) // Simulate network delay
        return foodList.find { it.id == id }
    }
}