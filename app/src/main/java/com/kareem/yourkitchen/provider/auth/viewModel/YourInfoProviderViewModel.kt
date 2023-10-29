package com.kareem.yourkitchen.provider.auth.viewModel




import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kareem.yourkitchen.base.db.PaperDB
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class YourInfoProviderViewModel() : ViewModel() {
    var progressKey = mutableStateOf(false)

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val _warningMessage = MutableLiveData<String>()
    private val correctOTP = 1111

    fun uploadToStorage(
        uri: Uri,
        context: Context,
        type: String,
        onImageUploaded: (String?) -> Unit
    ) {
        val storage = FirebaseStorage.getInstance()

        // Create a storage reference from our app
        val storageRef = storage.reference

        val unique_image_name = UUID.randomUUID()
        val spaceRef: StorageReference = if (type == "image") {
            storageRef.child("images/$unique_image_name.jpg")
        } else {
            storageRef.child("videos/$unique_image_name.mp4")
        }

        val byteArray: ByteArray? = context.contentResolver
            .openInputStream(uri)
            ?.use { it.readBytes() }

            val uploadTask = byteArray?.let { spaceRef.putBytes(it) }

        uploadTask?.addOnFailureListener {
            // Handle unsuccessful uploads
            onImageUploaded(null)
        }?.addOnSuccessListener { taskSnapshot ->
            spaceRef.downloadUrl.addOnSuccessListener { uri ->
                val downloadUrl = uri.toString()
                onImageUploaded(downloadUrl)
            }
        }

        }


    fun saveInfoProvider(
        firstNameP: String,
        lastNameP: String,
        emailAddressP: String,
        phoneNumberP: String,
        storeAddress: String,
        storeName: String,
        businessType: String,
        uri: String?

    ) {
        val userIdP = phoneNumberP

        val infoP = hashMapOf(
            "firstNameP" to firstNameP,
            "lastNameP" to lastNameP,
            "emailAddressP" to emailAddressP,
            "phoneNumberP" to phoneNumberP,
            "storeaddress" to storeAddress,
            "storeName" to storeName,
            "businesstype" to businessType,
            "imagePath" to uri.toString() // استخدم مسار الصورة هنا
       )

//        val myRef = database.getReference("AddFood")
//        val userReference = myRef.child("Users").child(phoneNumberP)
//
//        userReference.setValue(infoP).addOnCompleteListener {
//            function()
//        }

        val infoCollectionP = db.collection("userInfoProvider")
        val docRefP = infoCollectionP.document(phoneNumberP)

        viewModelScope.launch {
            try {
                docRefP.set(infoP).await()

                savePhoneNumberToAuth(userIdP, phoneNumberP)
                _warningMessage.postValue("تم حفظ المعلومات بنجاح")
                progressKey.value = false
            } catch (e: Exception) {
                _warningMessage.postValue("حدثت مشكلة أثناء حفظ المعلومات")
                progressKey.value = false
            }
        }
}


    private fun savePhoneNumberToAuth(userId: String, phoneNumberP: String) {
            val userP = auth.currentUser
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(phoneNumberP)
                .build()

            userP?.updateProfile(profileUpdates)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                    } else {

                    }
                }
        }

        suspend fun checkPhoneNumberExists(phoneNumberV: String): Boolean {
            val query: Query =
                db.collection("userInfoProvider").whereEqualTo("phoneNumberP", phoneNumberV)
            val querySnapshot = query.get().await()
            return !querySnapshot.isEmpty
        }

      val state = mutableStateOf<List<About>>(emptyList())



    init {
    getData()
}
    data class About(
        val storeName:String = "",
        val lastNameP: String="",
        val firstNameP: String="",
        val imagePath: String = "",
        val businesstype: String = "",
        val storeaddress: String = "",
        val phoneNumberP:String="",
        val emailAddressP: String="",

    )

    private fun getData() {
        viewModelScope.launch {
           // state.value= getDataFromFireStore()
            val allAbouts = getDataFromFireStore()
            state.value = allAbouts
        }
    }

    fun authProvider(otp:Int , context: Context, result:(Boolean)-> Unit) {
        Toast.makeText(context, "Successful login", Toast.LENGTH_SHORT).show()
        if (otp == correctOTP) {
            PaperDB.SetData(context).setIsLoginProvider("true")
            result(true)
        } else {
            Toast.makeText(context, "Invalid OTP. Please enter the correct OTP to proceed.", Toast.LENGTH_SHORT).show()
            result(false)
        }

    }

}

 suspend fun getDataFromFireStore(): MutableList<YourInfoProviderViewModel.About> {
    val db = FirebaseFirestore.getInstance()
    val aboutList = mutableListOf<YourInfoProviderViewModel.About>()

    try {
        val querySnapshot = db.collection("userInfoProvider").get().await()

        for (document in querySnapshot.documents) {
            val result = document.toObject(YourInfoProviderViewModel.About::class.java)
            result?.let { aboutList.add(it) }
        }
    } catch (_: FirebaseFirestoreException) {

    }

    return aboutList

}



