package com.example.rk_3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rk_3.ui.screens.FoodListScreen
import com.example.rk_3.ui.screens.FoodDetailScreen

@Composable
fun FoodAppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "food_list") {
        composable("food_list") {
            FoodListScreen(navController)
        }
       composable("food_detail/{foodId}") { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId")
            FoodDetailScreen(foodId)
        }
    }
}