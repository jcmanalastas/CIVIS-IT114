package com.civis.it114.ui.screens.staff

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
import androidx.compose.foundation.rememberScrollState
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

private val inspectionResults = listOf(
    "For Inspection",
    "Needs Repair",
    "Resolved"
)

@Composable
fun RecordInspectionScreen(
    caseId: String,
    inspectionDate: String,
    staffMember: String,
    findings: String,
    notes: String,
    result: String,
    onInspectionDateChange: (String) -> Unit,
    onStaffMemberChange: (String) -> Unit,
    onFindingsChange: (String) -> Unit,
    onNotesChange: (String) -> Unit,
    onResultChange: (String) -> Unit,
    onBack: () -> Unit,
    onSaveInspection: () -> Unit
) {
    var resultMenuExpanded by remember {
        mutableStateOf(false)
    }
    var showDiscardDialog by remember { mutableStateOf(false) }

    BackHandler(!showDiscardDialog) { showDiscardDialog = true}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                onClick = { showDiscardDialog = true }
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
                    text = "Record Inspection",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.size(48.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = caseId,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Inspection Date",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = inspectionDate,
            onValueChange = onInspectionDateChange,
            placeholder = {
                Text("Enter inspection date")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Staff Member",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = staffMember,
            onValueChange = onStaffMemberChange,
            placeholder = {
                Text("Enter staff member")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Findings",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = findings,
            onValueChange = onFindingsChange,
            placeholder = {
                Text("Enter inspection findings")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Notes",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = notes,
            onValueChange = onNotesChange,
            placeholder = {
                Text("Enter additional notes")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Result",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = result,
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    Text("Select Result")
                },
                trailingIcon = {
                    Text(
                        text = "▼",
                        modifier = Modifier.clickable {
                            resultMenuExpanded = true
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        resultMenuExpanded = true
                    }
            )

            DropdownMenu(
                expanded = resultMenuExpanded,
                onDismissRequest = {
                    resultMenuExpanded = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                inspectionResults.forEach { inspectionResult ->
                    DropdownMenuItem(
                        text = {
                            Text(inspectionResult)
                        },
                        onClick = {
                            onResultChange(inspectionResult)
                            resultMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSaveInspection,
            enabled = inspectionDate.isNotBlank() &&
                    staffMember.isNotBlank() &&
                    findings.isNotBlank() &&
                    result.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Save Inspection Record")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
    if (showDiscardDialog) {
        AlertDialog(
            onDismissRequest = {
                showDiscardDialog = false
            },
            title = {
                Text("Discard Record Inspection?")
            },
            text = {
                Text("Your inspection details haven't been saved. Are you sure you want to discard your changes?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDiscardDialog = false
                        onBack()
                    }
                ) {
                    Text("Discard Changes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDiscardDialog = false
                    }
                ) {
                    Text("Continue Editing")
                }
            }
        )
    }
}

@Preview(
    name = "Record Inspection",
    showBackground = true
)
@Composable
private fun RecordInspectionScreenPreview() {
    MaterialTheme {
        RecordInspectionScreen(
            caseId = "CIV-0001",
            inspectionDate = "Sept. 14, 2026",
            staffMember = "Staff Member 1",
            findings = "Streetlight requires repair.",
            notes = "Replacement parts are needed.",
            result = "For Inspection",
            onInspectionDateChange = {},
            onStaffMemberChange = {},
            onFindingsChange = {},
            onNotesChange = {},
            onResultChange = {},
            onBack = {},
            onSaveInspection = {}
        )
    }
}