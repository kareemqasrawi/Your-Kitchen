package com.kareem.yourkitchen.user.home.view.invoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InvoiceDialog(
    totalPrice: Double,
    isDialogOpen: Boolean,
    onCloseDialog: () -> Unit,
    onSubmit: () -> Unit
) {
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = onCloseDialog,
            title = {
                Text(text = "Invoice", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            },
            text = {
                Column(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Payment Details")
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        Modifier
                            .height(100.dp)
                            .fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            androidx.compose.material3.Text(
                                text = "Subtotal",
                                modifier = Modifier
                                    .background(Color.Transparent),
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                            androidx.compose.material3.Text(
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

                            androidx.compose.material3.Text(
                                text = "Delivery fee",
                                modifier = Modifier
                                    .background(Color.Transparent),
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                            androidx.compose.material3.Text(
                                text = "JOD 1.00",
                                modifier = Modifier
                                    .background(Color.Transparent),
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(25.dp))
                        Row(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            androidx.compose.material3.Text(
                                text = "Total amount",
                                modifier = Modifier
                                    .background(Color.Transparent),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                            androidx.compose.material3.Text(
                                text = "JOD ${totalPrice + 1.0}",
                                modifier = Modifier
                                    .background(Color.Transparent),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }

                    }
                }
            },
            buttons = {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(
                        onClick = onSubmit,
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error) // Using predefined error color (red) from the MaterialTheme
                    ) {
                        Text("Submit")
                    }
                }
            }
        )
    }
}