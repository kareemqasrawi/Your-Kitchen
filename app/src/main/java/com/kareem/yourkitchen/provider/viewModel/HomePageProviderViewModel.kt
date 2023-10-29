package com.kareem.yourkitchen.provider.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kareem.yourkitchen.dto.ProductDto


class HomePageProviderViewModel: ViewModel() {
    val database = Firebase.database
    val myRef = database.getReference("AddFood")

    val foodList = mutableStateOf(listOf<ProductDto?>())

    fun getProductList(phoneNumber: String): MutableState<List<ProductDto?>> {
        fetchDataFromFirebase(phoneNumber) { result ->
            when (result) {
                is Result.Success -> {
                    foodList.value = result.data
                    Log.d("Firebase", "List retrieved: $foodList")
                }
                is Result.Failure -> {
                    Log.e("Firebase", "Error reading data: ", result.exception)
                }
            }
        }

        return foodList
    }

    private fun fetchDataFromFirebase(phoneNumber: String, callback: (Result<List<ProductDto?>>) -> Unit) {
        val tempFoodList: MutableList<ProductDto?> = ArrayList()

        myRef.child(phoneNumber).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val food: ProductDto? = childSnapshot.getValue(ProductDto::class.java)
                    tempFoodList.add(food)
                }
                callback(Result.Success(tempFoodList))
            }

            override fun onCancelled(error: DatabaseError) {
                callback(Result.Failure(error.toException()))
            }
        })
    }


    fun deleteProduct(phoneNumber: String, nameFood: String, result: (Boolean) -> Unit) {
        val myRef = FirebaseDatabase.getInstance().getReference("AddFood")

        myRef.child(phoneNumber).child(nameFood)
            .removeValue()
            .addOnSuccessListener { // Code to handle success
                Log.d("Firebase", "Data deleted successfully.")
                result(true)
            }
            .addOnFailureListener { e -> // Code to handle failure
                Log.e("Firebase", "Error deleting data: ", e)
                result(false)
            }
    }


    sealed class Result<out T> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Failure(val exception: Exception) : Result<Nothing>()
    }
}