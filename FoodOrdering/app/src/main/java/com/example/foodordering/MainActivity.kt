package com.example.foodordering

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.foodordering.data.Food_data
import com.example.foodordering.model.Food

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                FoodOrderingApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodOrderingApp() {
    val foodList = Food_data.getFoodData()
    val orderedItems = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Food Ordering App",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {

                    IconButton(
                        onClick = {
                            val intent = Intent(context, CartActivity::class.java)
                            intent.putStringArrayListExtra("orderedItems", ArrayList(orderedItems))
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Shopping Cart",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }

                    // Search Button
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Search clicked", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB71C1C),
                    actionIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Categories Section
            Text(
                text = stringResource(id = R.string.categories),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFB71C1C),
                modifier = Modifier.padding(10.dp)
            )

            // Food Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(foodList.size) { index ->
                    val food = foodList[index]
                    FoodItem(
                        food = food,
                        onClick = {
                            val foodName = context.getString(food.titleResourceId)
                            orderedItems.add(foodName)
                            Toast.makeText(
                                context,
                                "You ordered $foodName",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FoodItem(food: Food, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = food.imageResourceId),
            contentDescription = stringResource(id = food.titleResourceId),
            modifier = Modifier
                .size(240.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = stringResource(id = food.titleResourceId),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


