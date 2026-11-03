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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun RecordInspectionScreen(
    caseId: String,
    submissionDate: String,
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

    var showDateDialog by remember {
        mutableStateOf(false)
    }

    var showErrors by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableStateOf(Calendar.getInstance())
    }

    val dateFormat = remember {
        SimpleDateFormat(
            "MMMM d, yyyy",
            Locale.ENGLISH
        ).apply {
            isLenient = false
        }
    }

    val submissionDateValue = remember(submissionDate) {
        try {
            dateFormat.parse(submissionDate)
        } catch (_: Exception) {
            null
        }
    }

    val inspectionDateValue = remember(inspectionDate) {
        try {
            dateFormat.parse(inspectionDate)
        } catch (_: Exception) {
            null
        }
    }

    val dateError = when {
        inspectionDate.isBlank() -> {
            if (showErrors) {
                "Inspection date is required."
            } else {
                null
            }
        }

        submissionDateValue == null ->
            "The case submission date is unavailable or invalid."

        inspectionDateValue == null ->
            "Please select a valid inspection date."

        inspectionDateValue.before(submissionDateValue) ->
            "Inspection date cannot be earlier than the submission date."

        else -> null
    }

    val staffError = showErrors && staffMember.isBlank()
    val findingsError = showErrors && findings.isBlank()
    val resultError = showErrors && result.isBlank()

    val canSave =
        caseId.isNotBlank() &&
        submissionDateValue != null &&
        inspectionDateValue != null &&
        dateError == null &&
        staffMember.isNotBlank() &&
        findings.isNotBlank() &&
        result.isNotBlank()

    val staffMembers = listOf(
        "Staff Member 1",
        "Staff Member 2"
    )

    val results = listOf(
        "Needs Repair",
        "In Progress",
        "Resolved"
    )

    fun openDateDialog() {
        selectedDate = Calendar.getInstance()

        if (inspectionDateValue != null) {
            selectedDate.time = inspectionDateValue
        }

        showDateDialog = true
    }

    if (showDateDialog) {
        AlertDialog(
            onDismissRequest = {
                showDateDialog = false
            },
            title = {
                Text("Select Inspection Date")
            },
            text = {
                Column {
                    Text(
                        text = dateFormat.format(selectedDate.time),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Month")

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            onClick = {
                                selectedDate =
                                    (selectedDate.clone() as Calendar).apply {
                                        add(Calendar.MONTH, -1)
                                    }
                            }
                        ) {
                            Text("Previous")
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(
                            onClick = {
                                selectedDate =
                                    (selectedDate.clone() as Calendar).apply {
                                        add(Calendar.MONTH, 1)
                                    }
                            }
                        ) {
                            Text("Next")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Day")

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            onClick = {
                                selectedDate =
                                    (selectedDate.clone() as Calendar).apply {
                                        add(Calendar.DAY_OF_MONTH, -1)
                                    }
                            }
                        ) {
                            Text("Previous")
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(
                            onClick = {
                                selectedDate =
                                    (selectedDate.clone() as Calendar).apply {
                                        add(Calendar.DAY_OF_MONTH, 1)
                                    }
                            }
                        ) {
                            Text("Next")
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onInspectionDateChange(
                            dateFormat.format(selectedDate.time)
                        )
                        showDateDialog = false
                    }
                ) {
                    Text("Select")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDateDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

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

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Submitted: $submissionDate",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    openDateDialog()
                }
        ) {
            OutlinedTextField(
                value = inspectionDate,
                onValueChange = {},
                readOnly = true,
                isError = dateError != null,
                label = {
                    Text("Inspection Date")
                },
                placeholder = {
                    Text("Select inspection date")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            openDateDialog()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Select inspection date"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (dateError != null) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = dateError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = staffMember,
                onValueChange = {},
                readOnly = true,
                isError = staffError,
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
                },
                modifier = Modifier.fillMaxWidth()
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

        if (staffError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Please select a staff member.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = findings,
            onValueChange = onFindingsChange,
            isError = findingsError,
            label = {
                Text("Inspection Findings")
            },
            placeholder = {
                Text("Enter inspection findings")
            },
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        if (findingsError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Inspection findings are required.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

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
                isError = resultError,
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
                },
                modifier = Modifier.fillMaxWidth()
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

        if (resultError) {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Please select an inspection result.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                showErrors = true

                if (canSave) {
                    onSaveInspection()
                }
            },
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
            submissionDate = "November 3, 2026",
            inspectionDate = "November 3, 2026",
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