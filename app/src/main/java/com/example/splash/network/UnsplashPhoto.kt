package com.example.splash.network

/**
 * The data class that defines the structure of the JSON objects received
 */
data class UnsplashPhoto(
    val id: String,
    val width : Int,
    val height : Int,
    val description : String? = null,
    val alt_description : String? = null,
    val urls: Url
)

