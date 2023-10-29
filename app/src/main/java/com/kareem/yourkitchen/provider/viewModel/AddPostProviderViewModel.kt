package com.kareem.yourkitchen.provider.viewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


class AddPostProviderViewModel(phoneNumberKey: String): ViewModel() {

    var progressKey = mutableStateOf(false)
   private val phoneNumber=phoneNumberKey

     val database = Firebase.database
     val myRef = database.getReference("AddFood")

     fun uploadPhoto(
         uri: Uri,
         context: Context,
         type: String,
         onImageUploaded: (String?) -> Unit
     ) {
         val storage = FirebaseStorage.getInstance()
         val storageRef = storage.reference
         val uniqueImageName = UUID.randomUUID()


         val imageRef = storageRef.child("imagess/${uniqueImageName}.jpg")

         val inputStream = context.contentResolver.openInputStream(uri)
         val byteArray = inputStream?.readBytes()

         if (byteArray != null) {
             val uploadTask = imageRef.putBytes(byteArray)

             uploadTask.addOnSuccessListener { taskSnapshot ->

                 imageRef.downloadUrl.addOnSuccessListener { uri ->
                     val imageUrl = uri.toString()


                     onImageUploaded(imageUrl)
                 }
             }.addOnFailureListener {

                 onImageUploaded(null)
             }
         } else {

             onImageUploaded(null)
         }
     }


     fun addDetailsFood(
         nameFood: String,
         description: String,
         Price: String,
         Rate: String,
         uri: String?,
         providerID :String?,
         result:(Boolean)-> Unit
     ) {

         val addFood = hashMapOf(
             "FoodName" to nameFood,
             "Description" to description,
             "Price" to Price,
             "Rate" to Rate,
             "PhotoFood" to uri.toString(),
             "ProviderId" to providerID.toString()
             //"refId" to myRef.child()
         )


         if (nameFood.isNotEmpty() && description.isNotEmpty() && Price.isNotEmpty() && Rate.isNotEmpty()) {
             myRef.child(phoneNumber).child(nameFood).setValue(addFood)
                 .addOnSuccessListener { // Code to handle success
                     Log.d("Firebase", "Data saved successfully.")
                     progressKey.value = false
                     result(true)
                 }
                 .addOnFailureListener { e -> // Code to handle failure
                     Log.e("Firebase", "Error saving data: ", e)
                     progressKey.value = false
                     result(false)
                 }
         }

     }



 }









//    fun add(value: String, nameFood: String, descrption: String, Price: String, Rate: String, uri: String?) {
//        // قم بإستخدام القيمة value كمفتاح لتخزين البيانات
//       // val keyRef = myRef.child(value).child()
//
//        val AddFood = hashMapOf(
//            "FoodName" to nameFood,
//            "Description" to descrption,
//            "Price" to Price,
//            "Rate" to Rate,
//            "PhotoFood" to uri.toString()
//        )
//
//        // تخزين البيانات داخل المفتاح الذي تم إنشاؤه باستخدام القيمة value كمفتاح
//        myRef.child(value).setValue(AddFood)
//    }





