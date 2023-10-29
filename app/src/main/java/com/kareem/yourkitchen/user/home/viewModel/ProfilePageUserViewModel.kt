package com.kareem.yourkitchen.user.home.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.kareem.yourkitchen.utils.AppData1
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfilePageUserViewModel : ViewModel() {

    val state1 = mutableStateOf<List<About2>>(emptyList())
    init {

        getData2(
            AppData1.phoneNumberUser

        )
        Log.d("fdfdfd777", "about: ${AppData1.phoneNumberUser}")
    }

    data class About2(
        val lastName: String = "",
        val firstName: String = "",
        val phoneNumber: String = "",
        val emailAddress: String = ""
    )

    fun getData2(ss: String) {
        viewModelScope.launch {
            val about = getDataFromFireStore2(AppData1.phoneNumberUser)
            state1.value = about
            Log.d("fdfdfd8", "about: ${state1.value}")
        }
    }

    private suspend fun getDataFromFireStore2(ss: String): MutableList<About2> {
        val db = FirebaseFirestore.getInstance()
        try {
            val document = db.collection("userInfo").document(ss).get().await()

            if (document.exists()) {
                val result = document.toObject(About2::class.java)
                if (result != null) {
                    return mutableListOf(result)
                }
            }
        } catch (e: FirebaseFirestoreException) {
            // Handle or log the exception as needed
            Log.e("FirestoreException", "Error fetching data: ${e.message}")
        }

        return mutableListOf()
    }

}

