package com.kareem.yourkitchen.user.home.view.basket

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.user.home.view.BottomNavItem1
import com.kareem.yourkitchen.user.home.view.map.getAddressFromLocation
import com.kareem.yourkitchen.user.home.view.invoice.InvoiceDialog
import com.kareem.yourkitchen.user.home.viewModel.BasketViewModel
import com.kareem.yourkitchen.user.home.viewModel.InvoiceViewModel
import com.kareem.yourkitchen.utils.AppData1
import com.kareem.yourkitchen.utils.TextBottom


@Composable
fun Basket(navController: NavController, basketViewModel: BasketViewModel = viewModel(),invoiceViewModel: InvoiceViewModel = viewModel()) {
    val context = LocalContext.current
    val productList = basketViewModel.getProductList(AppData1.phoneNumberUser).value
    val totalPrice = basketViewModel.calculateTotalPrice(productList)
    val scrollState = rememberScrollState()
    var isDialogOpen = remember { mutableStateOf(false) }

    var addressText = ""
    val address = getAddressFromLocation(context = context)
    address?.let {
        addressText = with(it) {
            "Current Location: ${getAddressLine(0)}\n${locality ?: ""}, ${postalCode ?: ""}, ${countryName ?: ""}"
        }

    } ?: run {
        // Handle the case where the address is null
        addressText = ""
    }
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {

        Column() {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable {
                        navController.navigate("Map")
                    }

            ) {
                Text(
                    text = "Delivering to ",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = null,
                    tint = Color(red = 230, green = 20, blue = 0),
                    modifier = Modifier
                        .size(70.dp)
                        .padding(16.dp)
                )


            }


            Text(
                text = addressText,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp),
                textAlign = TextAlign.Start,
                fontSize = 12.sp
            )
        }


        LazyColumn(
            modifier = Modifier
                .height(444.dp)
                .width(400.dp)
                .background(color = Color.White)
        ) {
            item {
                Spacer(modifier = Modifier.width(7.dp))
            }

            items(productList) { data ->
                Spacer(modifier = Modifier.width(7.dp))
                ProductBasket(
                    data?.photoFood.toString(),
                    data?.foodName.toString(),
                    data?.description.toString(),
                    data?.price.toString()
                )
                Spacer(modifier = Modifier.width(7.dp))
            }
        }




    }
Column(modifier = Modifier.padding(top = 530.dp)) {


    Divider(
        color = Color.Gray,
        thickness = 3.dp,
        modifier = Modifier
            .width(390.dp)
            .height(1.dp)

    )


    Text(
        text = "Payment summary",
        modifier = Modifier
            .background(Color.Transparent)
            .padding(start = 10.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
    )
    Spacer(modifier = Modifier.height(10.dp))

    Column(
        Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Subtotal",
                modifier = Modifier
                    .background(Color.Transparent),
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
            Text(
                text = "JOD $totalPrice",
                modifier = Modifier
                    .background(Color.Transparent),
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }


        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Delivery fee",
                modifier = Modifier
                    .background(Color.Transparent),
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
            Text(
                text = "JOD 1.00",
                modifier = Modifier
                    .background(Color.Transparent),
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Total amount",
                modifier = Modifier
                    .background(Color.Transparent),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
            Text(
                text = "JOD ${totalPrice + 1.0}",
                modifier = Modifier
                    .background(Color.Transparent),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .height(50.dp)
                .width(150.dp)
                .border(
                    width = 2.dp,
                    color = Color(red = 230, green = 20, blue = 0),
                    shape = MaterialTheme.shapes.small
                ),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 255, green = 255, blue = 255)
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Text(
                    text = "cash only",
                    color = Color(red = 0, green = 0, blue = 0)
                )
            }
        }
        Spacer(modifier = Modifier.width(22.dp))

        Button(
            onClick = {
                isDialogOpen.value = true

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 230, green = 20, blue = 0)
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .height(50.dp)
                .width(210.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                TextBottom("Checkout")

            }
        }
    }
}
    InvoiceDialog(
        totalPrice,
        isDialogOpen = isDialogOpen.value,
        onCloseDialog = { isDialogOpen.value = false },
        onSubmit = {
            isDialogOpen.value = false
            invoiceViewModel.saveProductsToFirestore("0${productList[0]?.providerId}", products = productList)
            Toast.makeText(context,"The order has been created successfully!!",Toast.LENGTH_LONG).show()
            navController.navigate(BottomNavItem1.HomeUser.screen_route)
        }
    )
}

@Composable
fun ProductBasket(
    image: String,
    productName: String,
    desc: String,
    price: String,
    basketViewModel: BasketViewModel = viewModel()
) {
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .height(120.dp)
            .width(360.dp)
            .padding(start = 20.dp)
            .background(
                color = Color(red = 230, green = 230, blue = 230),
                shape = RoundedCornerShape(400.dp),
            )
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(25.dp),
            )
    ) {


        Row(
            Modifier
                .size(width = 360.dp, height = 120.dp)
                .background(Color.White)
                .padding(top = 2.dp)
                .clip(RoundedCornerShape(30.dp)),
            Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Spacer(modifier = Modifier.width(10.dp))

            val painterImage = rememberImagePainter(data = image, builder = {
                crossfade(true)
                placeholder(R.drawable.yourkitchen)
            })// optional placeholder

            Image(
                // optional error image
                painter = painterImage,
                contentDescription = "Description for accessibility",  // Describe the image for accessibility purposes
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))



            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = productName,
                    modifier = Modifier
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                )
                Text(
                    text = desc,
                    modifier = Modifier
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.sp,
                )
            }
        }



        Icon(
            painter = painterResource(id = R.drawable.baseline_delete_24),
            contentDescription = "Delete Icon",
            modifier = Modifier.padding(start = 15.dp)
                .clickable {
                    basketViewModel.deleteProduct(productName,
                        onDeleteSuccess = {
                            // Handle success, e.g., refresh product list
                            basketViewModel.productList.value =
                                basketViewModel.productList.value.filter { it?.foodName != productName }
                            Toast
                                .makeText(context, "Item deleted successfully", Toast.LENGTH_LONG)
                                .show()

                        },
                        onDeleteFailure = { exception ->
                            // Handle failure, e.g., show an error message
                            println("Failed to delete product: $exception")
                            Toast
                                .makeText(context, "Item deleted fail", Toast.LENGTH_LONG)
                                .show()
                        }
                    )
                }
                .padding(top = 10.dp, start = 280.dp),

            tint = Color.Red
        )
        Text(
            text = "JOD $price",
            modifier = Modifier
                .background(Color.Transparent)
                .padding(top = 90.dp, start = 270.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        )


    }
}