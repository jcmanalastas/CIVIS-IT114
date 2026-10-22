package com.civis.it114.ui.screens.resident

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val issueCategories = listOf(
    "Broken Streetlight",
    "Road Damage",
    "Garbage",
    "Drainage",
    "Other"
)

@Composable
fun ReportIssueScreen(
    selectedCategory: String,
    description: String,
    location: String,
    photoAdded: Boolean,
    onCategoryChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onLocationChange: (String) -> Unit,
    onPhotoChange: (Boolean) -> Unit,
    onContinue: () -> Unit,
    onCancel: () -> Unit
) {
    var categoryMenuExpanded by remember {
        mutableStateOf(false)
    }

    var showCancelDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .safeDrawingPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    showCancelDialog = true
                }
            ) {
                Text(
                    text = "‹",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Report an Issue",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.size(48.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Issue Category",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    Text("Select Category")
                },
                trailingIcon = {
                    Text(
                        text = "▼",
                        modifier = Modifier.clickable {
                            categoryMenuExpanded = true
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        categoryMenuExpanded = true
                    }
            )

            DropdownMenu(
                expanded = categoryMenuExpanded,
                onDismissRequest = {
                    categoryMenuExpanded = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                issueCategories.forEach { category ->
                    DropdownMenuItem(
                        text = {
                            Text(category)
                        },
                        onClick = {
                            onCategoryChange(category)
                            categoryMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = description,
            onValueChange = {
                if (it.length <= 200) {
                    onDescriptionChange(it)
                }
            },
            placeholder = {
                Text("Describe the issue")
            },
            supportingText = {
                Text("${description.length}/200")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Location",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = location,
            onValueChange = onLocationChange,
            placeholder = {
                Text("Enter location")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Photo",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedButton(
            onClick = {
                onPhotoChange(!photoAdded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            if (photoAdded) {
                Text("Photo Added")
            } else {
                Text("+ Add Photo")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onContinue,
            enabled = selectedCategory.isNotBlank() &&
                    description.isNotBlank() &&
                    location.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue")
        }
    }

    if (showCancelDialog) {
        AlertDialog(
            onDismissRequest = {
                showCancelDialog = false
            },
            title = {
                Text("Cancel Report?")
            },
            text = {
                Text("Your current report information will be cleared.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showCancelDialog = false
                        onCancel()
                    }
                ) {
                    Text("Cancel Report")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showCancelDialog = false
                    }
                ) {
                    Text("Keep Editing")
                }
            }
        )
    }
}

@Preview(
    name = "Report Issue",
    showBackground = true
)
@Composable
private fun ReportIssueScreenPreview() {
    MaterialTheme {
        ReportIssueScreen(
            selectedCategory = "Broken Streetlight",
            description = "Streetlight is not working at night.",
            location = "Brgy. Mapua, Laguna",
            photoAdded = true,
            onCategoryChange = {},
            onDescriptionChange = {},
            onLocationChange = {},
            onPhotoChange = {},
            onContinue = {},
            onCancel = {}
        )
    }
}