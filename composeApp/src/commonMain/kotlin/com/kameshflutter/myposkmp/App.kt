package com.kameshflutter.myposkmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.kameshflutter.myposkmp.model.MenuList
import com.kameshflutter.myposkmp.model.startReadMenuListAsync
import com.kameshflutter.myposkmp.ui.MenuCategoryNavigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val menuList = startReadMenuListAsync()
        var menuListSync: MenuList
        runBlocking {
            menuListSync = menuList.await()
            delay(1000)
        }
        MenuCategoryNavigation(menuListSync)
    }
}