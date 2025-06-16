package com.kameshflutter.myposkmp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CartItemCard(cartMenuName: String, quantity: String, priceValue: String, onDeleteClick: ()-> Unit) {
    Row(modifier =  Modifier.padding(5.dp)){
        Text(text = cartMenuName, modifier = Modifier.weight(0.45f))
        Text(text = quantity, modifier = Modifier.weight(0.15f))
        Text(text = priceValue, modifier = Modifier.weight(0.25f))
        IconButton(onClick = {
            onDeleteClick()
        }, modifier = Modifier.weight(0.15f).align(Alignment.CenterVertically)) {
            androidx.compose.material.Icon(
                Icons.Outlined.Delete,
                tint = Color.Red,
                contentDescription = null
            )
        }
    }
}

@Composable
fun CartItemCardTitle(cartMenuName: String, quantity: String, priceValue: String) {
    Row(modifier = Modifier.padding(5.dp)) {
        Text(text = cartMenuName, modifier = Modifier.weight(0.45f))
        Text(text = quantity, modifier = Modifier.weight(0.15f))
        Text(text = priceValue, modifier = Modifier.weight(0.25f))
        Spacer(modifier = Modifier.weight(0.15f))
    }
}