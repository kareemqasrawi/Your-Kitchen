package com.kareem.yourkitchen.user.home.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kareem.yourkitchen.dto.ProductDto
import com.kareem.yourkitchen.dto.ProductDtoGet
import com.kareem.yourkitchen.utils.AppData1

class MapViewModel : ViewModel() {
    val db = Firebase.firestore

    fun saveAddressToFirestore(
        userPhone: String,
        apt: String,
        buildingName: String,
        floor: String,
        street: String
        ,statusCode:(Boolean)-> Unit
    ) {
        val deliveryAddress = hashMapOf(
            "apt" to apt,
            "buildingName" to buildingName,
            "floor" to floor,
            "street" to street
        )

        db.collection("deliveryAddress")
            .document(userPhone)
            .set(deliveryAddress)
            .addOnSuccessListener {
                statusCode(true)
                println("Address successfully written!")
            }
            .addOnFailureListener { e ->
                statusCode(false)
                println("Error writing address: $e")
            }
    }

    sealed class Result<out T> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Failure(val exception: Exception) : Result<Nothing>()
    }
}

