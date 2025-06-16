package com.kameshflutter.myposkmp.viewmodel

import androidx.lifecycle.ViewModel
import com.kameshflutter.myposkmp.model.CartDetails
import com.kameshflutter.myposkmp.model.CartMenuDetails
import com.kameshflutter.myposkmp.model.Menu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class CartViewModel : ViewModel() {
    private var _totalPrice = MutableStateFlow(0.0)

    //Expose immutable flow using asStateFlow()
    val totalCartPrice = _totalPrice.asStateFlow()

    fun updateTotalCartPriceAndList(menuItem: Menu) {
        _totalPrice.value += menuItem.price
        if(cartDetails.cartMenuSet.isEmpty()) {
            cartDetails.billNumber = getBillNumber()
        }
        val cartMenuDetails = cartDetails.cartMenuSet.firstOrNull{cartMenuDetails ->  cartMenuDetails.id == menuItem.id}
        if(cartMenuDetails == null) {
            cartDetails.cartMenuSet.add(
                CartMenuDetails(
                    id = menuItem.id,
                    menuItemName = menuItem.title,
                    quantity = 1,
                    price = menuItem.price
            )
            )
        } else {
            cartMenuDetails.quantity += 1
        }
        cartDetails.totalPrice = totalCartPrice.value
    }


    fun updateClearTotal() {
        _totalPrice.value = 0.0
    }

    var cartDetails = CartDetails(billNumber = getBillNumber(), totalPrice = 0.0, mutableSetOf<CartMenuDetails>())

    private fun getBillNumber() = Random.nextInt(from = 0, until = 1000000)
}