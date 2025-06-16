package com.kameshflutter.myposkmp.model

import kotlinx.serialization.*

@Serializable
data class Menu(val id: Int,
                val title: String,
                val price: Double,
                val currencyString: String,
                val menuCategory: MenuCategory,
                val description: String,
                )