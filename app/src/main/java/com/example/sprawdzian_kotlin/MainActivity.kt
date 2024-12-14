package com.example.sprawdzian_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ActivityItem(val name: String, val count: Int, val img_name: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@Composable
fun AppScreen() {
    val activities = listOf(
        ActivityItem("Committed changes", 22, "commend_changes"),
        ActivityItem("Comment count", 15, "commend_count"),
        ActivityItem("Merged pull requests", 8, "merge_request"),
        ActivityItem("Closed pull requests", 3, "closed_comit")
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            HeaderSection(name = "Kamil Jakubisiak")

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 10.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            ActivitiesList(activities = activities)
            Spacer(modifier = Modifier.weight(1f))

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            DisplayMessageButton()
        }
    }
}
@Composable
fun DisplayMessageButton() {
    val context = LocalContext.current

    Button(
        onClick = {
            Toast.makeText(context, "Well done!", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = "Display message",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}


@Composable
fun HeaderSection(name: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.man_person_icon),
            contentDescription = "User Icon",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Git statistics",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun ActivitiesList(activities: List<ActivityItem>) {
    // Mapowanie nazw obrazów na zasoby
    val imageResources = mapOf(
        "commend_changes" to R.drawable.commited_changes,
        "commend_count" to R.drawable.commend_count,
        "merge_request" to R.drawable.merge_request,
        "closed_comit" to R.drawable.closed_comit
    )

    Text(
        text = "Recent Activities",
        fontSize = 24.sp,
        color = Color.Black,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(vertical = 16.dp)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        activities.forEach { activity ->
            val imgResId = imageResources[activity.img_name]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                imgResId?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = activity.name,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = activity.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = activity.count.toString(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}
