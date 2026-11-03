package com.civis.it114.ui.screens.staff

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun RecordInspectionScreen(
    caseId: String,
    inspectionDate: String,
    onInspectionDateChange: (String) -> Unit,
    staffMember: String,
    onStaffMemberChange: (String) -> Unit,
    findings: String,
    onFindingsChange: (String) -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit,
    result: String,
    onResultChange: (String) -> Unit,
    onSaveInspection: () -> Unit,
    onBack: () -> Unit
) {
    var staffMenuExpanded by remember {
        mutableStateOf(false)
    }

    var resultMenuExpanded by remember {
        mutableStateOf(false)
    }

    val staffMembers = listOf(
        "Staff Member 1",
        "Staff Member 2",
        "Staff Member 3"
    )

    val results = listOf(
        "Needs Repair",
        "In Progress",
        "Resolved"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Text(
                text = "Record Inspection",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Case ID",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = caseId,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = inspectionDate,
            onValueChange = onInspectionDateChange,
            label = {
                Text("Inspection Date")
            },
            placeholder = {
                Text("Enter inspection date")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = staffMember,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Staff Member")
                },
                placeholder = {
                    Text("Select staff member")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            staffMenuExpanded = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Open staff member menu"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        staffMenuExpanded = true
                    }
            )

            DropdownMenu(
                expanded = staffMenuExpanded,
                onDismissRequest = {
                    staffMenuExpanded = false
                }
            ) {
                staffMembers.forEach { member ->
                    DropdownMenuItem(
                        text = {
                            Text(member)
                        },
                        onClick = {
                            onStaffMemberChange(member)
                            staffMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = findings,
            onValueChange = onFindingsChange,
            label = {
                Text("Inspection Findings")
            },
            placeholder = {
                Text("Enter inspection findings")
            },
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = notes,
            onValueChange = onNotesChange,
            label = {
                Text("Inspection Notes")
            },
            placeholder = {
                Text("Enter additional notes")
            },
            minLines = 3,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = result,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Inspection Result")
                },
                placeholder = {
                    Text("Select result")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            resultMenuExpanded = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Open result menu"
                        )
                    }
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
                }
            ) {
                results.forEach { inspectionResult ->
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

        Spacer(modifier = Modifier.height(28.dp))

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
            Text("Save Inspection")
        }

        Spacer(modifier = Modifier.height(20.dp))
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
            onInspectionDateChange = {},
            staffMember = "Staff Member 1",
            onStaffMemberChange = {},
            findings = "Streetlight requires replacement.",
            onFindingsChange = {},
            notes = "Replacement part is required.",
            onNotesChange = {},
            result = "Needs Repair",
            onResultChange = {},
            onSaveInspection = {},
            onBack = {}
        )
    }
}