package com.kameshflutter.myposkmp.model

data class CartDetails(var billNumber: Int, var totalPrice: Double, var cartMenuSet: MutableSet<CartMenuDetails>)

data class CartMenuDetails(val id: Int, val menuItemName: String, var quantity: Int, val price: Double)