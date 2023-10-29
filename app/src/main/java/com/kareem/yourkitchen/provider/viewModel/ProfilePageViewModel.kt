package com.kareem.yourkitchen.provider.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.kareem.yourkitchen.base.db.PaperDB
import com.kareem.yourkitchen.utils.AppData
import io.paperdb.Paper
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfilePageViewModel(application: Application) : AndroidViewModel(application) {


    val state = mutableStateOf<List<About1>>(emptyList())

    init {

        getPhoneNumber(application)
    }

    data class About1(
        val storeName: String = "",
        val lastNameP: String = "",
        val firstNameP: String = "",
        val imagePath: String = "",
        val businesstype: String = "",
        val storeaddress: String = "",
        val phoneNumberP: String = "",
        val emailAddressP: String = ""
    )

    private fun getPhoneNumber(context: Context) {
        viewModelScope.launch {
            val about = getDataFromFireStore1(
                PaperDB.GetData(context).getProviderPhone<String>().toString()
            )
            state.value = about
        }
    }

    private suspend fun getDataFromFireStore1(ss: String): MutableList<About1> {
        val db = FirebaseFirestore.getInstance()
        try {
            val document = db.collection("userInfoProvider").document(ss).get().await()

            if (document.exists()) {
                val result = document.toObject(About1::class.java)
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





