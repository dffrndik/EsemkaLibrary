package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.theme.Grey
import com.example.esemkalibrary.feature_forum.data.MainPost
import java.time.format.DateTimeFormatter

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: MainPost
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(1))
            .border(BorderStroke(width = 1.dp, color = Grey.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(1))
            .background(color = Color.White)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.photoprofiledefault),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .clip(CircleShape)
                .border(BorderStroke(width = 1.dp, color = Grey.copy(0.5f)), shape = CircleShape)
                .size(64.dp)
        )
        Column(modifier = Modifier, Arrangement.SpaceAround){
            Text(
                text  = post.subject,
                fontSize = 18.sp
            )
            Text(
                text  = "${post.createdBy.name} - ${post.createdAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}",
                fontSize = 14.sp,
            )
            Text(
                text = post.body,
                fontSize = 12.sp,
            )
        }
    }
}