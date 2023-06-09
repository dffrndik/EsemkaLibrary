package com.example.esemkalibrary.feature_mycart.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.theme.MudBrown
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.core.utils.viewModelFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MyCartScreen(modifier: Modifier = Modifier) {

    val viewModel: MyCartViewModel = viewModel(factory = viewModelFactory {
        MyCartViewModel(LocalContext.current)
    })

    var showRemoveDialog by remember {
        mutableStateOf(false)
    }

    var currentBookId by remember {
        mutableStateOf("")
    }

    if (showRemoveDialog) {
        RemoveDialog(
            onNoClicked = { showRemoveDialog = false },
            onYesClicked = {
                showRemoveDialog = false
                viewModel.removeItemFromCart(currentBookId)
            },
            id = currentBookId
        )
    }

    val bookIds by viewModel.bookIdsInCart.collectAsState("")
    val token by viewModel.token.collectAsState(initial = "")
    val uiState by viewModel.uiState.collectAsState()
    val books by viewModel.getBooksFromIds(bookIds.split(";"), token).collectAsState(initial = emptyList())
    viewModel.updateCartItems(books)
    val showStartDateDialog = uiState.showStartDateDialog

    if (showStartDateDialog) {
        DatePickerDialog(
            onDateSelected = {
                viewModel.updateStartDate(it)
                Log.e("TAG", "MyCartScreen: ", )
            },
            onDismissRequest = {
                viewModel.makeStartDateDialogInvisible()
            })
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyColumn(modifier = modifier.weight(1f, fill = false)) {
            items(
                uiState.cartItems
            ) { book ->
                CartCard(book = book, onRemoveClick = {
                    currentBookId = book.id
                    showRemoveDialog = true
                })
            }
        }
        Column(
            modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
            ,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text("Date Borrow:",modifier = Modifier.align(Alignment.Start))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    ,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically) {
                    DateLabel(modifier = Modifier.width(165.dp), onClick = {
                        viewModel.makeStartDateDialogVisible()
                    }, date = uiState.startDate)
                    Text("-")
                    DateLabel(modifier = Modifier.width(165.dp), onClick = {

                    }, date = uiState.endDate)
                }
                LibraryButton(onClick = { viewModel.uploadCart(
                    bookIds = uiState.cartItems.map{it.id},
                    token = token,
                    start = uiState.startDate,
                    end = uiState.endDate,
                ) }, modifier = Modifier.fillMaxWidth(), text = "Booking Borrow")
            }
        }
    }

}
@Preview
@Composable
fun CartScreenPrev() {
    MyCartScreen(modifier = Modifier.fillMaxSize())
}