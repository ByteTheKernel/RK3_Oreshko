package com.example.rk_3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.rk_3.data.Food
import com.example.rk_3.data.FoodRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FoodListScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val foodRepository = FoodRepositoryImpl()
    var foodList by remember { mutableStateOf<List<Food>?>(null) }

    // Load data from repository
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            foodList = foodRepository.getFoodList()
            println("Food list loaded: $foodList")
        }
    }

    if (foodList == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
            println("Loading food list...")
        }
    } else {
        LazyColumn {
            items(foodList!!) { food ->
                println("Displaying food item: ${food.name}")
                FoodItem(food = food, onClick = {
                    println("Navigating to details of: ${food.id}")
                    navController.navigate("food_detail/${food.id}")
                })
            }
        }
    }
}

@Composable
fun FoodItem(food: Food, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = food.imageUrl),
            contentDescription = food.name,
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = food.name, style = MaterialTheme.typography.h6)
            Text(
                text = food.description,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}