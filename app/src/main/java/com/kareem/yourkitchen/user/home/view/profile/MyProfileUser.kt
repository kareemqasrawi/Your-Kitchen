package com.kareem.yourkitchen.user.home.view.profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kareem.yourkitchen.provider.auth.viewModel.YourInfoProviderViewModel
import com.kareem.yourkitchen.provider.auth.viewModel.getDataFromFireStore
import com.kareem.yourkitchen.user.home.view.BottomNavItem1
import com.kareem.yourkitchen.user.home.viewModel.ProfilePageUserViewModel
import com.kareem.yourkitchen.utils.AppData1.phoneNumberUser
import com.kareem.yourkitchen.utils.ArrowBack
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

@Composable
fun MyProfileUser(navController: NavController,profilePageUserViewModel: ProfilePageUserViewModel = viewModel(),viewModel:SivePhotoUser) {
    val getProfileUserData = profilePageUserViewModel.state1.value


    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { result ->
        result?.let { uri = it }
    }

    ArrowBack {
        navController.navigate(BottomNavItem1.JobsUser.screen_route) {
            popUpTo(BottomNavItem1.JobsUser.screen_route)
        }
    }
    Column(
        modifier = Modifier
            .padding(start = 10.dp, top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .height(680.dp)
                .width(400.dp)
        ) {
            items(getProfileUserData) { name ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(RoundedCornerShape(75.dp))
                            .background(
                                color = Color(red = 220, green = 220, blue = 220),
                                shape = RoundedCornerShape(25.dp),
                            )
                            .clickable {
                                singlePhotoPicker.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                    ) {
                        uri?.let { imageUri ->
                            Image(
                                painter = rememberImagePainter(data = imageUri),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .graphicsLayer(
                                        clip = true,
                                        shape = RoundedCornerShape(30.dp)
                                    ),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = name.firstName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "Change Photo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp)
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(160.dp))
        LazyColumn {
            items(getProfileUserData) { name ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "My Name")
                    Row(
                        modifier = Modifier.padding(end = 25.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = name.firstName,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                        Text(
                            text = name.lastName,
                            color = Color.Black,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Phone")
                    Text(
                        text = "+962 ${name.phoneNumber}",
                        modifier = Modifier.padding(end = 25.dp),
                        color = Color.Black

                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Email")
                    Text(
                        text = name.emailAddress,
                        modifier = Modifier.padding(end = 25.dp),
                        color = Color.Black
                    )
                }
                val context = LocalContext.current
                Spacer(modifier = Modifier.height(200.dp))
                Button(
                    onClick = {
                        viewModel.progressKey.value = true
                        uri?.let {
                            viewModel.imageUseruploadToStorage(
                                uri = it,
                                context = context,
                                type = "image"
                            ) {imageUrl ->
                                if (imageUrl != null) {
                                    viewModel.addPhotoForUser(imageUrl)
                                }

                            }

                        }

                    },
                    modifier = Modifier
                        .padding(end = 30.dp)
                        .height(50.dp)
                        .width(320.dp)
                        .border(
                            width = 1.dp,
                            color = Color(red = 230, green = 20, blue = 0),
                            shape = MaterialTheme.shapes.small
                        ),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 240, green = 20, blue = 0)
                    ),
                ) {
                    if (viewModel.progressKey.value) {
                        androidx.compose.material.CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 4.dp
                        )
                    } else {
                        Text(text = "Save")
                    }
                }

            }

        }

    }
}


class SivePhotoUser() : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _warningMessage = MutableLiveData<String>()
    var progressKey = mutableStateOf(false)
    fun imageUseruploadToStorage(
        uri: Uri,
        context: Context,
        type: String,
        onImageUploaded: (String?) -> Unit

    ) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val uniqueImageName = UUID.randomUUID()


        val imageRef = storageRef.child("imagesUSER/${phoneNumberUser}.jpg")

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
    fun addPhotoForUser( uri: String?){
        val AddPhoto = hashMapOf("AddPhoto" to uri.toString() )

        val infoCollectionP = db.collection("ProfilePhotoUser")
        val docRefP = infoCollectionP.document(phoneNumberUser)

        viewModelScope.launch {
            try {
                docRefP.set(AddPhoto).await()

                _warningMessage.postValue("تم حفظ المعلومات بنجاح")
                progressKey.value = false
            } catch (e: Exception) {
                _warningMessage.postValue("حدثت مشكلة أثناء حفظ المعلومات")
                progressKey.value = false
            }
        }

    }
    data class About3(
        val imagePath: String = "",
    )

    val state = mutableStateOf<String?>(null)

    init {
        getPhoto()
    }

    private fun getPhoto() {
        viewModelScope.launch {
            val imageUrl = getPhotoFromFireStore()
            state.value = imageUrl
        }
    }
}

suspend fun getPhotoFromFireStore(): String? {
    val db = FirebaseFirestore.getInstance()

    try {
        val documentSnapshot = db.collection("ProfilePhotoUser")
            .document(phoneNumberUser)
            .get()
            .await()

        if (documentSnapshot.exists()) {
            val imageUrl = documentSnapshot.getString("AddPhoto")
            Log.d("wuqhiof","$imageUrl")
            return imageUrl
        }
    } catch (e: FirebaseFirestoreException) {

    }

    return null
}