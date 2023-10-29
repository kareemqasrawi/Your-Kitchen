package com.kareem.yourkitchen.user.auth.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.kareem.yourkitchen.utils.AppData1.phoneNumberUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MyAddressViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    fun saveMyAddress(
        buildingName: String,
        aptNumber: String,
        street: String,
        floor: String
    ) {
        val address = hashMapOf(
            "buildingName" to buildingName,
            "aptNumber" to aptNumber,
            "street" to street,
            "floor" to floor
        )

        val infoCollection = db.collection("collectionName")
        val docRef = infoCollection.document(phoneNumberUser)

        viewModelScope.launch {
            try {
                docRef.set(address).await()


                savePhoneNumberToAuth(phoneNumberUser)

                Log.d("MyAddressViewModel", "Address saved successfully")
            } catch (e: Exception) {
                Log.w("MyAddressViewModel", "Error adding document", e)
            }
        }




        }
    private fun savePhoneNumberToAuth(phoneNumber: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(phoneNumber)
            .build()

        user?.updateProfile(profileUpdates)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {

                }
            }
    }
}
