package com.civis.it114.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoleSelectionScreen(
    onViewAsResident: () -> Unit,
    onViewAsStaff: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Box(
            modifier = Modifier
                .size(32.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(6.dp)
                )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "CIVIS",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your trusted community service application",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = onViewAsResident,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("View as Resident")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onViewAsStaff,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("View as Staff")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun RoleSelectionScreenPreview() {
    MaterialTheme {
        RoleSelectionScreen(
            onViewAsResident = {},
            onViewAsStaff = {}
        )
    }
}