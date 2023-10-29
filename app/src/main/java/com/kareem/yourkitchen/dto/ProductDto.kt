package com.kareem.yourkitchen.dto

data class ProductDto(
    val FoodName  :String? = null ,
    val Description :String? = null,
    val PhotoFood :String? = null,
    val Price:String?=null,
    val Rate:String?=null,
    val ProviderId:String? =null

)

data class ProductDtoGet(
    val foodName  :String? = null ,
    val description :String? = null,
    val photoFood :String? = null,
    val price:String?=null,
    val rate:String?=null,
    val providerId:String? =null

)
