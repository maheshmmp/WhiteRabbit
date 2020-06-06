package com.example.whiterabbitct.data.models

data class Rabbit (
    val id: Int,
    val name: String,
    val image: String,
    val profile_image: String,
    val company: Company,
    val email: String,
    val phone: String,
    val website: String,
    val address: Address
)