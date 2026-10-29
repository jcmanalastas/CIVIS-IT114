package com.civis.it114.ui.screens.staff

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.civis.it114.ui.CivisCase

@Composable
fun StaffCasesScreen(
    cases: List<CivisCase>,
    onViewCase: (String) -> Unit,
    onBack: () -> Unit,
    onHome: () -> Unit
) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    val filteredCases = cases.filter { case ->
        searchQuery.isBlank() ||
            case.id.contains(
                searchQuery.trim(),
                ignoreCase = true
            )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {
            StaffCasesHeader(
                onBack = onBack
            )

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "All Cases",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Review and manage submitted community cases.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                label = {
                    Text("Search Case ID")
                },
                placeholder = {
                    Text("CIV-0001")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (cases.isEmpty()) {
                EmptyStaffCasesMessage()
            } else if (filteredCases.isEmpty()) {
                NoMatchingCasesMessage()
            } else {
                filteredCases.reversed().forEach { case ->
                    StaffCaseCard(
                        caseId = case.id,
                        category = case.category,
                        location = case.location,
                        status = case.status,
                        onViewCase = {
                            onViewCase(case.id)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        StaffCasesBottomBar(
            onHome = onHome,
            onCases = {}
        )
    }
}

@Composable
private fun StaffCasesHeader(
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        TextButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    )
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = "CIVIS",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun EmptyStaffCasesMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Cases Yet",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Submitted community cases will appear here.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NoMatchingCasesMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Matching Cases",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "No cases match the entered Case ID.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun StaffCaseCard(
    caseId: String,
    category: String,
    location: String,
    status: String,
    onViewCase: () -> Unit
) {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = caseId,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = status,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = category,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = location,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = onViewCase,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Case")
        }
    }
}

@Composable
private fun StaffCasesBottomBar(
    onHome: () -> Unit,
    onCases: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = onHome,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("⌂")
                Text("Home")
            }
        }

        TextButton(
            onClick = onCases,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("▤")
                Text("Cases")
            }
        }
    }
}

@Preview(
    name = "Staff Cases",
    showBackground = true
)
@Composable
private fun StaffCasesScreenPreview() {
    MaterialTheme {
        StaffCasesScreen(
            cases = listOf(
                CivisCase(
                    id = "CIV-0002",
                    category = "Road Damage",
                    description = "Large pothole near the intersection.",
                    location = "Main Road",
                    photoAdded = true,
                    date = "Sept. 10, 2026",
                    status = "Needs Repair",
                    priority = "Medium",
                    assignedStaff = "Staff Member 1",
                    inspectionDate = "Sept. 14, 2026",
                    inspectionStaffMember = "Staff Member 1",
                    inspectionFindings = "Road surface requires repair.",
                    inspectionNotes = "",
                    inspectionResult = "Needs Repair",
                    completionNotes = ""
                ),
                CivisCase(
                    id = "CIV-0001",
                    category = "Broken Streetlight",
                    description = "Streetlight is not working at night.",
                    location = "Brgy. Mapua, Laguna",
                    photoAdded = true,
                    date = "Sept. 10, 2026",
                    status = "Submitted",
                    priority = "Medium",
                    assignedStaff = "",
                    inspectionDate = "",
                    inspectionStaffMember = "",
                    inspectionFindings = "",
                    inspectionNotes = "",
                    inspectionResult = "",
                    completionNotes = ""
                )
            ),
            onViewCase = {},
            onBack = {},
            onHome = {}
        )
    }
}