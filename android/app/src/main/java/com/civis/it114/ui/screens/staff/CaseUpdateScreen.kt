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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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

private val caseStatuses = listOf(
    "For Inspection",
    "Needs Repair",
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
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    var showErrors by remember {
        mutableStateOf(false)
    }

    val statusError = showErrors && selectedStatus.isBlank()
    val notesError = showErrors && completionNotes.isBlank()

    val canSave = selectedStatus.isNotBlank() &&
        completionNotes.isNotBlank()

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
                onClick = onBack
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
                    text = "Case Update",
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
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Update Status",
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
                isError = statusError,
                placeholder = {
                    Text("Select Status")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            menuExpanded = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Open status menu"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        menuExpanded = true
                    }
            )

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = {
                    menuExpanded = false
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
                            menuExpanded = false
                        }
                    )
                }
            }
        }

        if (statusError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Please select an update status.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = completionNotes,
            onValueChange = onCompletionNotesChange,
            isError = notesError,
            label = {
                Text("Completion Notes")
            },
            placeholder = {
                Text("Enter case update or completion notes")
            },
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        if (notesError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Please enter case update or completion notes.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                showErrors = true

                if (canSave) {
                    onSaveUpdate()
                }
            },
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
            currentStatus = "Needs Repair",
            selectedStatus = "In Progress",
            completionNotes = "Replacement work has started.",
            onStatusChange = {},
            onCompletionNotesChange = {},
            onBack = {},
            onSaveUpdate = {}
        )
    }
}