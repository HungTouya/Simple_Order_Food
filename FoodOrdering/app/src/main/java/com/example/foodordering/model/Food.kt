package com.example.foodordering.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Food(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)
