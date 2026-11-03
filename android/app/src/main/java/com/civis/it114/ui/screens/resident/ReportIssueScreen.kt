package com.civis.it114.ui.screens.resident

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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

    var showMissingFields by remember {
        mutableStateOf(false)
    }

    val categoryError = showMissingFields && selectedCategory.isBlank()
    val descriptionError = showMissingFields && description.isBlank()
    val locationError = showMissingFields && location.isBlank()
    val photoError = showMissingFields && !photoAdded

    val missingFields = listOfNotNull(
        if (selectedCategory.isBlank()) "Issue Category" else null,
        if (description.isBlank()) "Description" else null,
        if (location.isBlank()) "Location" else null,
        if (!photoAdded) "Photo" else null
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onCancel
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
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
                isError = categoryError,
                placeholder = {
                    Text("Select Category")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            categoryMenuExpanded = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Open category menu"
                        )
                    }
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

        if (categoryError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Please select an issue category.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
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
            isError = descriptionError,
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

        if (descriptionError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Please enter a description.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

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
            isError = locationError,
            placeholder = {
                Text("Enter location")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (locationError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Please enter the issue location.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

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

        if (photoError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Please add a photo before continuing.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (missingFields.isEmpty()) {
                    showMissingFields = false
                    onContinue()
                } else {
                    showMissingFields = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue")
        }

        Spacer(modifier = Modifier.height(20.dp))
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