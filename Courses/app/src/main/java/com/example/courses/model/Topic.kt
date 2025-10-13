package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val titleId: Int,
    val availableCourses: Int,
    @DrawableRes val imageId: Int,
)