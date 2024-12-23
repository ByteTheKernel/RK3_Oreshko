package com.example.rk_3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rk_3.ui.screens.FoodListScreen
import com.example.rk_3.ui.screens.FoodDetailScreen
import com.example.rk_3.viewModel.FoodViewModel

@Composable
fun FoodAppNavigation(viewModel: FoodViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "food_list") {
        composable("food_list") {
            FoodListScreen(
                viewModel = viewModel,
                onRecipeClick = { recipeId ->
                    navController.navigate("food_detail/$recipeId")
                }
            )
        }
        composable(
            route = "food_detail/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            FoodDetailScreen(recipeId = recipeId, viewModel = viewModel)
        }
    }
}
