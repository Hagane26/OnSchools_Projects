package com.example.onschools

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform