package com.civis.it114.ui.screens.staff

import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
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

private val caseStatuses = listOf(
    "Assigned",
    "For Inspection",
    "In Progress",
    "Resolved"
)

@Composable
fun CaseUpdateScreen(
    caseId: String,
    category: String,
    currentStatus: String,
    selectedStatus: String,
    completionNotes: String,
    onStatusChange: (String) -> Unit,
    onCompletionNotesChange: (String) -> Unit,
    onBack: () -> Unit,
    onSaveUpdate: () -> Unit
) {
    var statusMenuExpanded by remember {
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
                    text = "Update Case",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.size(48.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Case ID",
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = caseId,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Current Status",
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = currentStatus,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Status",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedStatus,
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    Text("Select Status")
                },
                trailingIcon = {
                    Text(
                        text = "▼",
                        modifier = Modifier.clickable {
                            statusMenuExpanded = true
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        statusMenuExpanded = true
                    }
            )

            DropdownMenu(
                expanded = statusMenuExpanded,
                onDismissRequest = {
                    statusMenuExpanded = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                caseStatuses.forEach { status ->
                    DropdownMenuItem(
                        text = {
                            Text(status)
                        },
                        onClick = {
                            onStatusChange(status)
                            statusMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Completion Notes",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = completionNotes,
            onValueChange = onCompletionNotesChange,
            placeholder = {
                Text("Enter completion notes")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSaveUpdate,
            enabled = selectedStatus.isNotBlank() &&
                    completionNotes.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Save Update")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(
    name = "Case Update",
    showBackground = true
)
@Composable
private fun CaseUpdateScreenPreview() {
    MaterialTheme {
        CaseUpdateScreen(
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            currentStatus = "In Progress",
            selectedStatus = "Resolved",
            completionNotes = "Streetlight repaired and tested.",
            onStatusChange = {},
            onCompletionNotesChange = {},
            onBack = {},
            onSaveUpdate = {}
        )
    }
}