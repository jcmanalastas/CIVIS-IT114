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

private val priorities = listOf(
    "Low",
    "Medium",
    "High"
)

private val staffMembers = listOf(
    "Staff Member 1",
    "Staff Member 2"
)

@Composable
fun CaseDetailsScreen(
    caseId: String,
    category: String,
    date: String,
    status: String,
    description: String,
    location: String,
    priority: String,
    assignedStaff: String,
    completionNotes: String,
    onPriorityChange: (String) -> Unit,
    onAssignedStaffChange: (String) -> Unit,
    onBack: () -> Unit,
    onAssignCase: () -> Unit,
    onUpdateAssignment: () -> Unit,
    onRecordInspection: () -> Unit,
    onUpdateCase: () -> Unit,
    onReopenCase: () -> Unit
) {
    val assignmentEditable =
        status == "Submitted" ||
            status == "Pending" ||
            status == "Assigned"

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
                    text = "Case Details",
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
                text = date,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Status",
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            if (completionNotes.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Completion Notes",
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = completionNotes,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Location",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = location,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (assignmentEditable) {
            PrioritySelector(
                selectedPriority = priority,
                onPrioritySelected = onPriorityChange
            )

            Spacer(modifier = Modifier.height(20.dp))

            StaffSelector(
                selectedStaff = assignedStaff,
                onStaffSelected = onAssignedStaffChange
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (status == "Assigned") {
                Button(
                    onClick = onUpdateAssignment,
                    enabled = priority.isNotBlank() &&
                        assignedStaff.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Update Assignment")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onRecordInspection,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Record Inspection")
                }
            } else {
                Button(
                    onClick = onAssignCase,
                    enabled = priority.isNotBlank() &&
                        assignedStaff.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Assign Case")
                }
            }
        } else {
            Text(
                text = "Priority",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = priority,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Assigned Staff",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = assignedStaff,
                style = MaterialTheme.typography.bodyMedium
            )

            if (
                status == "For Inspection" ||
                status == "Needs Repair" ||
                status == "In Progress"
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onUpdateCase,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Update Case")
                }
            }

            if (status == "Resolved") {
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onReopenCase,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Reopen Case")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun PrioritySelector(
    selectedPriority: String,
    onPrioritySelected: (String) -> Unit
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Text(
        text = "Priority",
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(6.dp))

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedPriority,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text("Select Priority")
            },
            trailingIcon = {
                Text(
                    text = "▼",
                    modifier = Modifier.clickable {
                        menuExpanded = true
                    }
                )
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
            priorities.forEach { priority ->
                DropdownMenuItem(
                    text = {
                        Text(priority)
                    },
                    onClick = {
                        onPrioritySelected(priority)
                        menuExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun StaffSelector(
    selectedStaff: String,
    onStaffSelected: (String) -> Unit
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Text(
        text = "Assign Staff",
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(6.dp))

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedStaff,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text("Select Staff Member")
            },
            trailingIcon = {
                Text(
                    text = "▼",
                    modifier = Modifier.clickable {
                        menuExpanded = true
                    }
                )
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
            staffMembers.forEach { staff ->
                DropdownMenuItem(
                    text = {
                        Text(staff)
                    },
                    onClick = {
                        onStaffSelected(staff)
                        menuExpanded = false
                    }
                )
            }
        }
    }
}

@Preview(
    name = "Unassigned Case",
    showBackground = true
)
@Composable
private fun UnassignedCaseDetailsPreview() {
    MaterialTheme {
        CaseDetailsScreen(
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            date = "October 29, 2026",
            status = "Submitted",
            description = "Streetlight is not working.",
            location = "Barangay Road",
            priority = "",
            assignedStaff = "",
            completionNotes = "",
            onPriorityChange = {},
            onAssignedStaffChange = {},
            onBack = {},
            onAssignCase = {},
            onUpdateAssignment = {},
            onRecordInspection = {},
            onUpdateCase = {},
            onReopenCase = {}
        )
    }
}

@Preview(
    name = "Assigned Case",
    showBackground = true
)
@Composable
private fun AssignedCaseDetailsPreview() {
    MaterialTheme {
        CaseDetailsScreen(
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            date = "October 29, 2026",
            status = "Assigned",
            description = "Streetlight is not working.",
            location = "Barangay Road",
            priority = "High",
            assignedStaff = "Staff Member 1",
            completionNotes = "",
            onPriorityChange = {},
            onAssignedStaffChange = {},
            onBack = {},
            onAssignCase = {},
            onUpdateAssignment = {},
            onRecordInspection = {},
            onUpdateCase = {},
            onReopenCase = {}
        )
    }
}

@Preview(
    name = "Needs Repair Case",
    showBackground = true
)
@Composable
private fun NeedsRepairCaseDetailsPreview() {
    MaterialTheme {
        CaseDetailsScreen(
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            date = "October 29, 2026",
            status = "Needs Repair",
            description = "Streetlight is not working.",
            location = "Barangay Road",
            priority = "High",
            assignedStaff = "Staff Member 1",
            completionNotes = "Replacement parts are required.",
            onPriorityChange = {},
            onAssignedStaffChange = {},
            onBack = {},
            onAssignCase = {},
            onUpdateAssignment = {},
            onRecordInspection = {},
            onUpdateCase = {},
            onReopenCase = {}
        )
    }
}

@Preview(
    name = "Resolved Case",
    showBackground = true
)
@Composable
private fun ResolvedCaseDetailsPreview() {
    MaterialTheme {
        CaseDetailsScreen(
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            date = "October 29, 2026",
            status = "Resolved",
            description = "Streetlight is not working.",
            location = "Barangay Road",
            priority = "High",
            assignedStaff = "Staff Member 1",
            completionNotes = "Streetlight repaired and tested.",
            onPriorityChange = {},
            onAssignedStaffChange = {},
            onBack = {},
            onAssignCase = {},
            onUpdateAssignment = {},
            onRecordInspection = {},
            onUpdateCase = {},
            onReopenCase = {}
        )
    }
}