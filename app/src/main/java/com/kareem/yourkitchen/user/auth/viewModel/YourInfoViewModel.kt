package com.kareem.yourkitchen.user.auth.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.kareem.yourkitchen.base.db.PaperDB
import com.kareem.yourkitchen.utils.AppData1
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

 class YourInfoViewModel : ViewModel() {
     var progressKey = mutableStateOf(false)

        private val db = FirebaseFirestore.getInstance()
     private val correctOTP = 1111

        private val _warningMessage = MutableLiveData<String>()
        val warningMessage: LiveData<String>
            get() = _warningMessage

        fun saveInfo(firstName: String, lastName: String, emailAddress: String, phoneNumber: String) {
            val info = hashMapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "emailAddress" to emailAddress,
                "phoneNumber" to phoneNumber,

            )

            val infoCollection = db.collection("userInfo")
            val docRef = infoCollection.document(phoneNumber)

            viewModelScope.launch {
                try {
                    docRef.set(info).await()


                    savePhoneNumberToAuth(phoneNumber)

//                    result(true)
                    _warningMessage.postValue("تم حفظ المعلومات بنجاح")
                    progressKey.value = false
                } catch (e: Exception) {
                    _warningMessage.postValue("حدثت مشكلة أثناء حفظ المعلومات")
                    progressKey.value = false
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

        suspend fun checkPhoneNumberExistsU(phoneNumberU: String): Boolean {
            val query = db.collection("userInfo").whereEqualTo("phoneNumber", phoneNumberU)
            val querySnapshot = query.get().await()

            return !querySnapshot.isEmpty
        }

     fun authUser(otp: Int, context: Context, function: (Boolean) -> Unit) {
         Toast.makeText(context, "Successful login", Toast.LENGTH_SHORT).show()
         if (otp == correctOTP) {

             PaperDB.SetData(context).apply {
                 setIsLoginUser("true")
                 setUserPhone(AppData1.phoneNumberUser)
             }

             function(true)
         } else {
             Toast.makeText(context, "Invalid OTP. Please enter the correct OTP to proceed.", Toast.LENGTH_SHORT).show()
             function(false)
         }

     }
 }

