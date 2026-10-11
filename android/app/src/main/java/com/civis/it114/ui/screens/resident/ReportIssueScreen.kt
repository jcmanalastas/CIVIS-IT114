package com.civis.it114.ui.screens.resident

import android.R
import android.R.attr.textStyle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Size
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import kotlin.math.exp


private val InputTextStyle = TextStyle(
    fontSize = 14.sp
)

private val issueCategories = listOf(
    "Garbage and Illegal Dumping",
    "Clogged Drainage",
    "Flooding",
    "Road Damage and Potholes",
    "Broken Streetlights",
    "Water Supply Problems",
    "Illegal Parking",
    "Road Obstruction",
    "Noise Complaint",
    "Stray Animals",
    "Damaged Public Property",
    "Illegal Structures",
    "Tree and Branch Hazards",
    "Public Sanitation",
    "Electrical Hazards",
    "Other"
)

@Composable
private fun inputFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
)

@Composable
fun ReportIssueScreen(onContinue: () -> Unit, onCancel: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ReportIssueHeader(onCancel = onCancel)
        IssueCategorySection()
        DescriptionSection()
        LocationSection()
        PhotoSection()
        Spacer(modifier = Modifier.weight(1f))
        ContinueButton(onContinue = onContinue)
    }
}

@Composable
fun ReportIssueHeader(onCancel: () -> Unit) {
    var showCancelDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = "‹",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable {
                    showCancelDialog = true
                }
        )

        Text(
            text = "Report an Issue",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )
        if (showCancelDialog) {
            AlertDialog(
                onDismissRequest = {
                    showCancelDialog = false
                },
                title = {
                    Text("Cancel Report?")
                },
                text = {
                    Text("Are you sure you want to cancel this report? Your entered details may be lost.")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showCancelDialog = false
                            onCancel()
                        }
                    ) {
                        Text("Yes, Cancel", style = InputTextStyle)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showCancelDialog = false
                        }
                    ) {
                        Text("Keep Editing", style = InputTextStyle)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueCategorySection() {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Issue Category",
            style = MaterialTheme.typography.titleSmall
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    Text(
                        "Select an issue category",
                        style = InputTextStyle
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = (inputFieldColors()
                ),
                textStyle = InputTextStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                        MaterialTheme.shapes.small
                    )
                    .menuAnchor(
                        ExposedDropdownMenuAnchorType.PrimaryNotEditable
                    )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                issueCategories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedText = category
                            expanded = false
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun DescriptionSection() {
    val descriptionState = rememberTextFieldState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleSmall
        )

        TextField(
            state = descriptionState,
            placeholder = {
                Text("Describe an issue", style = InputTextStyle)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    MaterialTheme.shapes.small
                ),
            lineLimits = TextFieldLineLimits.MultiLine(1, 4),
            inputTransformation = InputTransformation.maxLength(200),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                cursorColor = Color.Black
            ),
            textStyle = InputTextStyle
        )

        Text(
            text = "${descriptionState.text.length}/200",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 12.sp
        )
    }
}

@Composable
fun LocationSection() {
    val locationState = rememberTextFieldState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Location",
            style = MaterialTheme.typography.titleSmall
        )

        TextField(
            state = locationState,
            placeholder = {
                Text("Enter a location", style = InputTextStyle)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    MaterialTheme.shapes.small
                ),
            colors = inputFieldColors()
        )
    }
}

@Composable
fun PhotoSection() {
    var showPopup by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Photo",
            style = MaterialTheme.typography.titleSmall
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    MaterialTheme.shapes.small
                )
                .clickable { showPopup = true },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+ Add Photo",
                style = InputTextStyle,
                color = Color.Gray
            )
            if (showPopup) {
                AlertDialog(
                    onDismissRequest = { showPopup = false },
                    title = { Text("Photo Added") },
                    text = { Text("A sample photo has been added to your report.") },
                    confirmButton = {
                        TextButton(onClick = { showPopup = false }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ContinueButton(onContinue: () -> Unit) {
    Button(
        onClick = onContinue,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text("Continue")
    }
}


@Preview(showBackground = true)
@Composable
private fun ReportIssueScreenPreview() {
    MaterialTheme {
        ReportIssueScreen(onContinue = {}, onCancel = {})
    }
}