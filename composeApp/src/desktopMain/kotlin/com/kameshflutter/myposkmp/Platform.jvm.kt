package com.kameshflutter.myposkmp

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

