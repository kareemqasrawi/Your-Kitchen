package com.kareem.yourkitchen.user.home.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kareem.yourkitchen.dto.ProductDtoGet
import com.kareem.yourkitchen.utils.AppData1

class InvoiceViewModel : ViewModel() {
    val db = Firebase.firestore

    fun saveProductsToFirestore(
        providerPhone: String,
        products: List<ProductDtoGet?>
    ) {

        val userOrder = hashMapOf(
            "products" to products
        )

        db.collection("userInfoProvider")
            .document(providerPhone)
            .collection("orders")
            .document(AppData1.phoneNumberUser)
            .set(userOrder)
            .addOnSuccessListener {
                println("Products successfully written!")
                deleteProductsFromBasket(AppData1.phoneNumberUser)

            }
            .addOnFailureListener { e ->
                println("Error writing products: $e")
            }
    }


    private fun deleteProductsFromBasket(userId: String) {

        db.collection("basket")
            .document(userId)
            .collection("products")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    db.collection("basket")
                        .document(userId)
                        .collection("products")
                        .document(document.id)
                        .delete()
                        .addOnSuccessListener {
                            println("Product successfully deleted!")
                        }
                        .addOnFailureListener { e ->
                            println("Error deleting product: $e")
                        }
                }
            }
            .addOnFailureListener { e ->
                println("Error getting products: $e")
            }
    }
    sealed class Result<out T> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Failure(val exception: Exception) : Result<Nothing>()
    }
}

