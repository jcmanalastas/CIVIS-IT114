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
import androidx.compose.material3.Button
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

@Composable
fun ResidentHomeScreen(
    hasReport: Boolean,
    caseId: String,
    category: String,
    location: String,
    status: String,
    onReportIssue: () -> Unit,
    onTrackReports: () -> Unit,
    onViewReport: () -> Unit,
    onBack: () -> Unit
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
            ResidentHeader(
                onBack = onBack
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Good day, Resident!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "How can we help your community?",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onReportIssue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("+ Report an Issue")
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onTrackReports,
                enabled = hasReport,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Track My Reports")
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Report",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                if (hasReport) {
                    TextButton(
                        onClick = onTrackReports
                    ) {
                        Text("View All")
                    }
                } else {
                    Text(
                        text = "View All",
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        ),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (hasReport) {
                RecentReportCard(
                    caseId = caseId,
                    category = category,
                    location = location,
                    status = status,
                    onViewReport = onViewReport
                )
            } else {
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
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        ResidentBottomBar(
            hasReport = hasReport,
            onReports = onTrackReports
        )
    }
}

@Composable
private fun ResidentHeader(
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
private fun RecentReportCard(
    caseId: String,
    category: String,
    location: String,
    status: String,
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
            onClick = onViewReport,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Report")
        }
    }
}

@Composable
private fun ResidentBottomBar(
    hasReport: Boolean,
    onReports: () -> Unit
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
        BottomBarLabel(
            symbol = "⌂",
            label = "Home",
            modifier = Modifier.weight(1f)
        )

        if (hasReport) {
            TextButton(
                onClick = onReports,
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("▤")
                    Text("Reports")
                }
            }
        } else {
            BottomBarLabel(
                symbol = "▤",
                label = "Reports",
                modifier = Modifier.weight(1f)
            )
        }

        BottomBarLabel(
            symbol = "○",
            label = "Profile",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun BottomBarLabel(
    symbol: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(symbol)
        Text(label)
    }
}

@Preview(
    name = "Resident Home Empty",
    showBackground = true
)
@Composable
private fun ResidentHomeEmptyPreview() {
    MaterialTheme {
        ResidentHomeScreen(
            hasReport = false,
            caseId = "",
            category = "",
            location = "",
            status = "",
            onReportIssue = {},
            onTrackReports = {},
            onViewReport = {},
            onBack = {}
        )
    }
}

@Preview(
    name = "Resident Home With Report",
    showBackground = true
)
@Composable
private fun ResidentHomeWithReportPreview() {
    MaterialTheme {
        ResidentHomeScreen(
            hasReport = true,
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            location = "Brgy. Mapua, Laguna",
            status = "Submitted",
            onReportIssue = {},
            onTrackReports = {},
            onViewReport = {},
            onBack = {}
        )
    }
}