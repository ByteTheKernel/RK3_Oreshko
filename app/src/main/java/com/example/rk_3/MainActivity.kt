package com.example.rk_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.rk_3.ui.navigation.FoodAppNavigation
import com.example.rk_3.ui.screens.FoodDetailScreen
import com.example.rk_3.ui.screens.FoodListScreen
import com.example.rk_3.ui.theme.MyAppTheme
import com.example.rk_3.viewModel.FoodViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = FoodViewModel()

        setContent {
            MyAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    FoodAppNavigation(viewModel)
                }
            }
        }
    }
}