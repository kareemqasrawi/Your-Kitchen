package com.kareem.yourkitchen.user.home.view.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Popup
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.user.home.view.BottomNavItem1
import com.kareem.yourkitchen.user.home.viewModel.MapViewModel
import com.kareem.yourkitchen.utils.AppData1
import com.kareem.yourkitchen.utils.TextBottom
import java.util.Locale

@Composable
fun Map(navController: NavController, mapViewModel: MapViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val buildingName = remember { mutableStateOf("") }
    val aptNumber = remember { mutableStateOf("") }
    val floor = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(color = Color.White)
                    .width(400.dp)
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = null,
                            tint = Color(red = 230, green = 20, blue = 0),
                            modifier = Modifier
                                .size(30.dp)
                                .padding(5.dp)
                                .clickable {

                                    navController.navigate(BottomNavItem1.Basket.screen_route) {
                                        popUpTo(BottomNavItem1.Basket.screen_route)
                                    }

                                })
                    }

                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Delivery location",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = null,
                        tint = Color(red = 230, green = 20, blue = 0),
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .size(25.dp)
                    )

                }
            }

            GoogleMapUI()
        }

        AddressDetails() { buildingNameValue, aptNo, floorValue, streetValue ->
            buildingName.value = buildingNameValue
            aptNumber.value = aptNo
            floor.value = floorValue
            street.value = streetValue
        }


        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                mapViewModel.saveAddressToFirestore(
                    AppData1.phoneNumberUser.toString(),
                    aptNumber.value,
                    buildingName.value,
                    floor.value,
                    street.value
                ) {
                    if (it)
                        navController.navigate(BottomNavItem1.Basket.screen_route)
                    Toast.makeText(context, "Address successfully Added!", Toast.LENGTH_LONG).show()
                }


            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 230, green = 20, blue = 0)

            ),

            modifier = Modifier
                .padding(start = 20.dp, bottom = 40.dp)
                .height(50.dp)
                .width(350.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                TextBottom("Confirm")

            }
        }


    }
}


@Composable
fun GoogleMapUI() {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth(),
        factory = { ctx ->
            MapView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                onCreate(null)
                onResume()
                getMapAsync { googleMap ->
                    MapsInitializer.initialize(context)
                    setupGoogleMap(googleMap, context)
                }
            }
        })
}


var lat = 0.0
var logt = 0.0
fun setupGoogleMap(googleMap: GoogleMap, context: Context) {
    // Check if location permission is granted
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        googleMap.isMyLocationEnabled = true

        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationProvider = LocationManager.GPS_PROVIDER
        val lastKnownLocation = locationManager.getLastKnownLocation(locationProvider)

        if (lastKnownLocation != null) {
            val latLng = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
            lat = lastKnownLocation.latitude
            logt = lastKnownLocation.longitude
            googleMap.addMarker(MarkerOptions().position(latLng).title("Current Location"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
        }
    } else {
        // Handle the case where location permission is not granted
        // You can request the necessary permissions here or handle the absence gracefully
    }
}


fun getAddressFromLocation(
    context: Context,
    latitude: Double = lat,
    longitude: Double = logt
): Address? {
    val geocoder = Geocoder(context, Locale.getDefault())
    return try {

        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            addresses[0]
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}