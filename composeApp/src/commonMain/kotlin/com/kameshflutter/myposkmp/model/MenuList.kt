package com.kameshflutter.myposkmp.model

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import myposkmp.composeapp.generated.resources.Res
import kotlinx.coroutines.*

@Serializable
class MenuList(val menuList: List<Menu>)

@OptIn(DelicateCoroutinesApi::class)
fun startReadMenuListAsync() = GlobalScope.async {
    readMenuList()
}
suspend fun readMenuList(): MenuList {
    val readBytes = Res.readBytes("files/ExampleData.json")
    val jsonString = String(readBytes)
    return Json.decodeFromString(jsonString)
}