package com.example.rk_3.data.model

data class Recipe (
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val cookTimeMinutes: Int,
    val tags: List<String>,
    val image: String,
    val rating: Double,
    val caloriesPerServing: Int,
    val cuisine: String
)

data class RecipeResponse(
    val recipes: List<Recipe>,
    val total: Int,
    val skip: Int,
    val limit: Int
)