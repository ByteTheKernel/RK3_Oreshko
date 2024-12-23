package com.example.rk_3.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rk_3.data.Food
import com.example.rk_3.data.FoodRepositoryImpl
import kotlinx.coroutines.launch

@Composable
fun FoodDetailScreen(foodId: String?) {
    val foodRepository = FoodRepositoryImpl()
    var food by remember { mutableStateOf<Food?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Загрузка данных
    LaunchedEffect(foodId) {
        if (foodId != null) {
            try {
                food = foodRepository.getFoodById(foodId)
                isLoading = false
            } catch (e: Exception) {
                errorMessage = "Error fetching food details"
                isLoading = false
            }
        }
    }

    // UI
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = errorMessage ?: "Unknown error", style = MaterialTheme.typography.h6)
        }
    } else if (food != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(food?.imageUrl),
                contentDescription = food?.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = food?.name ?: "", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = food?.description ?: "", style = MaterialTheme.typography.body1)
        }
    }
}
