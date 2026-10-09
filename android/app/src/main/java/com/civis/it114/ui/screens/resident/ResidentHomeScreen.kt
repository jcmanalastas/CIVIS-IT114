package com.civis.it114.ui.screens.resident

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val CivisBlue = Color(0xFF1769C2)
private val CivisGreen = Color(0xFF16A34A)
private val CivisLightBlue = Color(0xFFE8F1FC)

@Composable
fun ResidentHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FC))
            .padding(horizontal = 10.dp, vertical = 16.dp)
    ) {
        CivisLogo()

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Good day,",
            fontSize = 12.sp
        )

        Text(
            text = "Resident!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "How can we help your community?",
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
        ) {
            Text(
                text = "＋   Report an Issue",
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .background(CivisLightBlue)
        ) {
            Text(
                text = "Track My Reports",
                fontSize = 12.sp,
                color = CivisBlue
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Report",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All",
                fontSize = 10.sp,
                color = CivisBlue
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        EmptyReportsMessage()

        Spacer(modifier = Modifier.weight(1f))

        ResidentBottomBar()
    }
}

@Composable
private fun CivisLogo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(11.dp)
                .background(CivisGreen, CircleShape)
        )

        Spacer(modifier = Modifier.width(3.dp))

        Text(
            text = "CIVIS",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF003078)
        )
    }
}

@Composable
private fun EmptyReportsMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Reports Yet",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Have an issue to report?\nSubmit your first report to help improve your community.",
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Your reports will appear here once submitted.",
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ResidentBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 2.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem("⌂", "Home", selected = true)
        BottomBarItem("▤", "Reports", selected = false)
        BottomBarItem("◎", "Profile", selected = false)
    }
}

@Composable
private fun BottomBarItem(
    symbol: String,
    label: String,
    selected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = symbol,
            fontSize = 15.sp,
            color = if (selected) CivisBlue else Color.DarkGray
        )

        Text(
            text = label,
            fontSize = 9.sp,
            color = if (selected) CivisBlue else Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResidentHomeScreenPreview() {
    MaterialTheme {
        ResidentHomeScreen()
    }
}
