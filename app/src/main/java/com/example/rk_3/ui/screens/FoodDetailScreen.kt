package com.example.rk_3.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rk_3.viewModel.FoodViewModel
import com.example.rk_3.data.model.Recipe
import kotlinx.coroutines.launch

@Composable
fun FoodDetailScreen(recipeId: String?, viewModel: FoodViewModel) {
    // Получаем список рецептов из viewModel с использованием collectAsState()
    val recipes by viewModel.recipes.collectAsState()

    // Ищем рецепт по id
    val recipe = recipes.find { it.id.toString() == recipeId }

    if (recipe == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Recipe not found")
        }
        return
    }

    LazyColumn(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        item {
            Image(
                painter = rememberAsyncImagePainter(recipe.image),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = recipe.name, style = MaterialTheme.typography.headlineMedium)
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ingredients:", style = MaterialTheme.typography.titleSmall)
            recipe.ingredients.forEach { ingredient ->
                Text(text = "- $ingredient", style = MaterialTheme.typography.bodyLarge)
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Instructions:", style = MaterialTheme.typography.titleSmall)
            recipe.instructions.forEachIndexed { index, step ->
                Text(text = "${index + 1}. $step", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
