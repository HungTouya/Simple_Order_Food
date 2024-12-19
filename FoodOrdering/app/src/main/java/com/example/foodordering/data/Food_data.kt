package com.example.foodordering.data

import com.example.foodordering.R
import com.example.foodordering.model.Food

object Food_data {
    val defaultFood = getFoodData()[0]

    fun getFoodData(): List<Food> {
        return listOf(
            Food(
                id =1,
                titleResourceId = R.string.bread,
                imageResourceId = R.drawable.bread,
            ),

            Food(
                id =2,
                titleResourceId = R.string.burger,
                imageResourceId = R.drawable.burger,
            ),

            Food(
                id =3,
                titleResourceId = R.string.cake,
                imageResourceId = R.drawable.cake,
            ),

            Food(
                id =4,
                titleResourceId = R.string.candy,
                imageResourceId = R.drawable.candy,
            ),

            Food(
                id =5,
                titleResourceId = R.string.cookie,
                imageResourceId = R.drawable.cookie,
            ),

            Food(
                id = 6,
                titleResourceId = R.string.pizza,
                imageResourceId = R.drawable.pizza,
            )
        )
    }
}