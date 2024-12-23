package com.example.rk_3.data.api

import com.example.rk_3.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {
    @GET("recipes")
    suspend fun getRecipes(
        @Query("limit") limit: Int = 50,
        @Query("skip") skip: Int = 0,
    ): RecipeResponse
}