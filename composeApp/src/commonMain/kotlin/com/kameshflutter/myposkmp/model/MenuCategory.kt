package com.kameshflutter.myposkmp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MenuCategory {
    @SerialName("pizza")
    PIZZA,
    @SerialName("burger")
    BURGER,
    @SerialName("sandwich")
    SANDWICH,
    @SerialName("kidsmenu")
    KIDSMENU,
    @SerialName("salad")
    SALAD,
    @SerialName("drinks")
    DRINKS,
}