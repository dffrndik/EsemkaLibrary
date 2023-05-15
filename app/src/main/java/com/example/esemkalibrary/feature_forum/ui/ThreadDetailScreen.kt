package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryTextField

@Composable
fun ThreadDetailScreen(modifier: Modifier = Modifier) {
    Column {
        LazyColumn(modifier
            .weight(weight = 1f, fill = false)
            .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            item {
                PostCard()
            }
            items(7) {
                ReplyCard()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = White)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            LibraryTextField(value = "", onValueChange = {}, hint = { Text("Reply....") }, modifier = Modifier.fillMaxWidth(), showLabel = false)
            LibraryButton(onClick = { /*TODO*/ }, text = "Add Reply", modifier = Modifier.fillMaxWidth())
        }
    }
}