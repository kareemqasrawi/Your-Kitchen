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

class BasketViewModel : ViewModel() {
    val db = Firebase.firestore

    fun saveProduct(userID: String, product: ProductDto, context: Context) {

        // Reference to the user's basket
        val userBasketRef = db.collection("basket").document(userID)

        // Adding a new product to the user's list of products
        userBasketRef.collection("products")
            .document(product.FoodName.toString())
            .set(product)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(
                    context,
                    "Product ${product.FoodName} added successfully.",
                    Toast.LENGTH_LONG
                ).show()
                println("Product ${product.FoodName} added successfully.")
            }
            .addOnFailureListener { e ->
                println("Error adding product: $e")
                Toast.makeText(context, "Error adding product: $e", Toast.LENGTH_LONG).show()
            }


    }




    val productList = mutableStateOf(listOf<ProductDtoGet?>())
    fun getProductList(userID: String): MutableState<List<ProductDtoGet?>> {
        fetchDataFromFirestore(userID) { result ->
            when (result) {
                is Result.Success -> {
                    productList.value = result.data
                    println("Firestore: List retrieved: $productList")
                }

                is Result.Failure -> {
                    println("Firestore: Error reading data: ${result.exception}")
                }
            }
        }
        return productList
    }

    private fun fetchDataFromFirestore(
        phoneNumber: String,
        callback: (Result<List<ProductDtoGet?>>) -> Unit
    ) {
        val tempProductList: MutableList<ProductDtoGet?> = ArrayList()

        val userBasketRef = db.collection("basket").document(phoneNumber).collection("products")

        userBasketRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val product = document.toObject(ProductDtoGet::class.java)
                    tempProductList.add(product)
                }
                callback(Result.Success(tempProductList))
            }
            .addOnFailureListener { exception ->
                callback(Result.Failure(exception))
            }
    }

    fun calculateTotalPrice(productList: List<ProductDtoGet?>): Double {
        return productList.sumByDouble { product ->
            product?.price?.toDouble() ?: 0.0
        }
    }





    fun deleteProduct(productId: String, onDeleteSuccess: () -> Unit, onDeleteFailure: (Exception) -> Unit) {
        val userBasketRef = db.collection("basket")
            .document(AppData1.phoneNumberUser)
            .collection("products")
            .document(productId)

        userBasketRef.delete()
            .addOnSuccessListener {
                onDeleteSuccess() // Callback on successful deletion
            }
            .addOnFailureListener { e ->
                onDeleteFailure(e) // Callback on failure

            }
    }









    sealed class Result<out T> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Failure(val exception: Exception) : Result<Nothing>()
    }
}

