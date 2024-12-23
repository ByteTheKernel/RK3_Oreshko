package com.example.rk_3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.rk_3.R
import com.example.rk_3.viewModel.FoodViewModel
import com.example.rk_3.data.model.Recipe
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.rk_3.utils.getCuisineFlag


@Composable
fun FoodListScreen(viewModel: FoodViewModel, onRecipeClick: (String) -> Unit) {
    val recipes by viewModel.recipes.collectAsState() // Получаем список рецептов
    val isLoading by viewModel.isLoading.collectAsState() // Получаем статус загрузки
    val errorMessage by viewModel.errorMessage.collectAsState() // Получаем ошибку, если есть


    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = errorMessage ?: "Unknown error",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(onClick = { viewModel.loadRecipes() }) { // Повторная попытка загрузки
                    Text(text = "Попробовать ещё раз")
                }
            }
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(recipes) { recipe ->
                FoodCard(recipe, onClick = { onRecipeClick(recipe.id.toString()) })
            }
        }
    }
}

@Composable
fun FoodCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(recipe.image),
                contentDescription = recipe.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    if(getCuisineFlag(recipe.cuisine).startsWith("hawaiian_flag")){
                        Image(
                            painter = painterResource(id = R.drawable.hawaiian_flag),
                            contentDescription = "Hawaiian Flag",
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = getCuisineFlag(recipe.cuisine),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${recipe.cookTimeMinutes} min",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = recipe.tags.joinToString(", "),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
