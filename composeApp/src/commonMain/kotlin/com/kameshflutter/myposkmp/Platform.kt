package com.kameshflutter.myposkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform