package com.civis.it114.ui.screens.resident

import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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

@Composable
fun ReviewReportScreen(
    category: String,
    description: String,
    location: String,
    photoAdded: Boolean,
    submitted: Boolean,
    status: String,
    inspectionNotes: String,
    completionNotes: String,
    canModify: Boolean,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onSubmitReport: () -> Unit,
    onBackToHome: () -> Unit
) {
    var showDeleteConfirmation by remember {
        mutableStateOf(false)
    }

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
            TextButton(
                onClick = onBack
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
                    text = "Review Report",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.size(48.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        ReviewSection(
            label = "Category",
            value = category
        )

        Spacer(modifier = Modifier.height(18.dp))

        ReviewSection(
            label = "Description",
            value = description
        )

        Spacer(modifier = Modifier.height(18.dp))

        ReviewSection(
            label = "Location",
            value = location
        )

        if (submitted) {
            Spacer(modifier = Modifier.height(18.dp))

            ReviewSection(
                label = "Status",
                value = status
            )

            if (
                status != "Resolved" &&
                inspectionNotes.isNotBlank()
            ) {
                Spacer(modifier = Modifier.height(18.dp))

                ReviewSection(
                    label = "Inspection Notes",
                    value = inspectionNotes
                )
            }

            if (completionNotes.isNotBlank()) {
                Spacer(modifier = Modifier.height(18.dp))

                ReviewSection(
                    label = "Completion Notes",
                    value = completionNotes
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Photo",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (photoAdded) {
                    "Photo Added"
                } else {
                    "No Photo Added"
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (submitted) {
            OutlinedButton(
                onClick = onEdit,
                enabled = canModify,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Edit Report")
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = {
                    showDeleteConfirmation = true
                },
                enabled = canModify,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Delete Report")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onBackToHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Back to Home")
            }
        } else {
            OutlinedButton(
                onClick = onEdit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Edit")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onSubmitReport,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Submit Report")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = {
                showDeleteConfirmation = false
            },
            title = {
                Text("Delete Case?")
            },
            text = {
                Text(
                    "Are you sure you want to delete this case? This action cannot be undone."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmation = false
                        onDelete()
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmation = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun ReviewSection(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(14.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(
    name = "Review Before Submission",
    showBackground = true
)
@Composable
private fun ReviewReportPreview() {
    MaterialTheme {
        ReviewReportScreen(
            category = "Broken Streetlight",
            description = "Streetlight is not working at night.",
            location = "Brgy. Mapua, Laguna",
            photoAdded = true,
            submitted = false,
            status = "",
            inspectionNotes = "",
            completionNotes = "",
            canModify = false,
            onBack = {},
            onEdit = {},
            onDelete = {},
            onSubmitReport = {},
            onBackToHome = {}
        )
    }
}

@Preview(
    name = "Submitted Editable Report",
    showBackground = true
)
@Composable
private fun SubmittedEditableReportPreview() {
    MaterialTheme {
        ReviewReportScreen(
            category = "Broken Streetlight",
            description = "Streetlight is not working at night.",
            location = "Brgy. Mapua, Laguna",
            photoAdded = true,
            submitted = true,
            status = "Submitted",
            inspectionNotes = "",
            completionNotes = "",
            canModify = true,
            onBack = {},
            onEdit = {},
            onDelete = {},
            onSubmitReport = {},
            onBackToHome = {}
        )
    }
}

@Preview(
    name = "Needs Repair Locked Report",
    showBackground = true
)
@Composable
private fun NeedsRepairReportPreview() {
    MaterialTheme {
        ReviewReportScreen(
            category = "Broken Streetlight",
            description = "Streetlight is not working at night.",
            location = "Brgy. Mapua, Laguna",
            photoAdded = true,
            submitted = true,
            status = "Needs Repair",
            inspectionNotes = "The wiring is damaged and requires replacement.",
            completionNotes = "",
            canModify = false,
            onBack = {},
            onEdit = {},
            onDelete = {},
            onSubmitReport = {},
            onBackToHome = {}
        )
    }
}

@Preview(
    name = "Resolved Locked Report",
    showBackground = true
)
@Composable
private fun ResolvedReportPreview() {
    MaterialTheme {
        ReviewReportScreen(
            category = "Broken Streetlight",
            description = "Streetlight is not working at night.",
            location = "Brgy. Mapua, Laguna",
            photoAdded = true,
            submitted = true,
            status = "Resolved",
            inspectionNotes = "The wiring is damaged and requires replacement.",
            completionNotes = "Streetlight wiring replaced and tested.",
            canModify = false,
            onBack = {},
            onEdit = {},
            onDelete = {},
            onSubmitReport = {},
            onBackToHome = {}
        )
    }
}