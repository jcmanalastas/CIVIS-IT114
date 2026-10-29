package com.civis.it114.ui.screens.resident

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.civis.it114.ui.CivisCase

@Composable
fun ResidentReportsScreen(
    cases: List<CivisCase>,
    onTrackReport: (String) -> Unit,
    onViewReport: (String) -> Unit,
    onBack: () -> Unit,
    onHome: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
        ) {
            ResidentReportsHeader(
                onBack = onBack
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "My Reports",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "View and track your submitted reports.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (cases.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No Reports Yet",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Your submitted reports will appear here.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                cases.reversed().forEach { case ->
                    ResidentReportCard(
                        caseId = case.id,
                        category = case.category,
                        location = case.location,
                        status = case.status,
                        onTrackReport = {
                            onTrackReport(case.id)
                        },
                        onViewReport = {
                            onViewReport(case.id)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        ResidentReportsBottomBar(
            onHome = onHome
        )
    }
}

@Composable
private fun ResidentReportsHeader(
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
private fun ResidentReportCard(
    caseId: String,
    category: String,
    location: String,
    status: String,
    onTrackReport: () -> Unit,
    onViewReport: () -> Unit
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

        OutlinedButton(
            onClick = onTrackReport,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Track Report")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onViewReport,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Report")
        }
    }
}

@Composable
private fun ResidentReportsBottomBar(
    onHome: () -> Unit
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
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("▤")
                Text("Reports")
            }
        }
    }
}

@Preview(
    name = "Resident Reports",
    showBackground = true
)
@Composable
private fun ResidentReportsPreview() {
    MaterialTheme {
        ResidentReportsScreen(
            cases = listOf(
                CivisCase(
                    id = "CIV-0001",
                    category = "Broken Streetlight",
                    description = "Streetlight is not working at night.",
                    location = "01",
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
                ),
                CivisCase(
                    id = "CIV-0002",
                    category = "Drainage",
                    description = "Blocked drainage.",
                    location = "02",
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
            onTrackReport = {},
            onViewReport = {},
            onBack = {},
            onHome = {}
        )
    }
}