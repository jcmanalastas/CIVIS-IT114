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
    onPriorityChange: (String) -> Unit,
    onAssignedStaffChange: (String) -> Unit,
    onBack: () -> Unit,
    onAssignCase: () -> Unit,
    onRecordInspection: () -> Unit,
    onUpdateCase: () -> Unit
) {
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

        Spacer(modifier = Modifier.height(16.dp))

        CaseSummary(
            caseId = caseId,
            category = category,
            date = date,
            status = status
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = description,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Location",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = location,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(18.dp))

        if (status == "Submitted" || status == "Pending") {
            PrioritySelector(
                selectedPriority = priority,
                onPrioritySelected = onPriorityChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            StaffSelector(
                selectedStaff = assignedStaff,
                onStaffSelected = onAssignedStaffChange
            )

            Spacer(modifier = Modifier.height(24.dp))

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
        } else {
            Text(
                text = "Priority",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = priority,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Assigned Staff",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = assignedStaff,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (status == "Assigned") {
                Button(
                    onClick = onRecordInspection,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Record Inspection")
                }
            }

            if (status == "For Inspection") {
                Button(
                    onClick = onUpdateCase,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Update Case")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun CaseSummary(
    caseId: String,
    category: String,
    date: String,
    status: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = caseId,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = category,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text(
            text = status,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun PrioritySelector(
    selectedPriority: String,
    onPrioritySelected: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column {
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
                            expanded = true
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = true
                    }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
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
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun StaffSelector(
    selectedStaff: String,
    onStaffSelected: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column {
        Text(
            text = "Assign to Staff",
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
                            expanded = true
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = true
                    }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
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
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Case Details Before Assignment",
    showBackground = true
)
@Composable
private fun CaseDetailsBeforeAssignmentPreview() {
    MaterialTheme {
        CaseDetailsScreen(
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            date = "Sept. 10, 2026",
            status = "Submitted",
            description = "Streetlight is not working at night.",
            location = "Brgy. Mapua, Laguna",
            priority = "Medium",
            assignedStaff = "",
            onPriorityChange = {},
            onAssignedStaffChange = {},
            onBack = {},
            onAssignCase = {},
            onRecordInspection = {},
            onUpdateCase = {}
        )
    }
}

@Preview(
    name = "Case Details Assigned",
    showBackground = true
)
@Composable
private fun CaseDetailsAssignedPreview() {
    MaterialTheme {
        CaseDetailsScreen(
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            date = "Sept. 10, 2026",
            status = "Assigned",
            description = "Streetlight is not working at night.",
            location = "Brgy. Mapua, Laguna",
            priority = "Medium",
            assignedStaff = "Staff Member 1",
            onPriorityChange = {},
            onAssignedStaffChange = {},
            onBack = {},
            onAssignCase = {},
            onRecordInspection = {},
            onUpdateCase = {}
        )
    }
}