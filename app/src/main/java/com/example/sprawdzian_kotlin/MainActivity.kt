package com.example.sprawdzian_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Info
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

data class ActivityItem(val name: String, val count: Int, val icon: @Composable () -> Unit)

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
        ActivityItem("Committed changes", 22, { Icon(Icons.Default.CheckCircle, contentDescription = "Committed changes", modifier = Modifier.size(32.dp)) }),
        ActivityItem("Comment count", 15, { Icon(Icons.Default.AddCircle, contentDescription = "Comment count", modifier = Modifier.size(32.dp)) }),
        ActivityItem("Merged pull requests", 8, { Icon(Icons.Rounded.Info, contentDescription = "Merged pull requests", modifier = Modifier.size(32.dp)) }),
        ActivityItem("Closed pull requests", 3, { Icon(Icons.Default.Close, contentDescription = "Closed pull requests", modifier = Modifier.size(32.dp)) })
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
    ) {
        Image(
            painter = painterResource(id = R.drawable.man_person_icon),
            contentDescription = "User",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                activity.icon()

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = activity.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )


                Text(
                    text = activity.count.toString(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp)
                )
            }
        }
    }
}
