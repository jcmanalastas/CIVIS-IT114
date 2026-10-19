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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StaffHomeScreen(
    pendingCount: Int,
    underReviewCount: Int,
    inProgressCount: Int,
    resolvedCount: Int,
    caseId: String,
    category: String,
    location: String,
    caseStatus: String,
    onViewCase: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {
            StaffHeader(
                onBack = onBack
            )

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Good day, Staff!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Here is the current case overview.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatusCard(
                    label = "Pending",
                    count = pendingCount,
                    modifier = Modifier.weight(1f)
                )

                StatusCard(
                    label = "Under Review",
                    count = underReviewCount,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatusCard(
                    label = "In Progress",
                    count = inProgressCount,
                    modifier = Modifier.weight(1f)
                )

                StatusCard(
                    label = "Resolved",
                    count = resolvedCount,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Recent Cases",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            RecentCaseCard(
                caseId = caseId,
                category = category,
                location = location,
                status = caseStatus,
                onViewCase = onViewCase
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        StaffBottomBar()
    }
}

@Composable
private fun StaffHeader(
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
private fun StatusCard(
    label: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(90.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun RecentCaseCard(
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

        OutlinedButton(
            onClick = onViewCase,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Case")
        }
    }
}

@Composable
private fun StaffBottomBar() {
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
        StaffBottomBarLabel(
            symbol = "⌂",
            label = "Home",
            modifier = Modifier.weight(1f)
        )

        StaffBottomBarLabel(
            symbol = "▤",
            label = "Cases",
            modifier = Modifier.weight(1f)
        )

        StaffBottomBarLabel(
            symbol = "○",
            label = "Profile",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StaffBottomBarLabel(
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
    name = "Staff Home Pending",
    showBackground = true
)
@Composable
private fun StaffHomePendingPreview() {
    MaterialTheme {
        StaffHomeScreen(
            pendingCount = 5,
            underReviewCount = 3,
            inProgressCount = 4,
            resolvedCount = 2,
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            location = "Brgy. Mapua, Laguna",
            caseStatus = "Pending",
            onViewCase = {},
            onBack = {}
        )
    }
}

@Preview(
    name = "Staff Home Assigned",
    showBackground = true
)
@Composable
private fun StaffHomeAssignedPreview() {
    MaterialTheme {
        StaffHomeScreen(
            pendingCount = 5,
            underReviewCount = 3,
            inProgressCount = 4,
            resolvedCount = 2,
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            location = "Brgy. Mapua, Laguna",
            caseStatus = "Assigned",
            onViewCase = {},
            onBack = {}
        )
    }
}

@Preview(
    name = "Staff Home Resolved",
    showBackground = true
)
@Composable
private fun StaffHomeResolvedPreview() {
    MaterialTheme {
        StaffHomeScreen(
            pendingCount = 5,
            underReviewCount = 3,
            inProgressCount = 4,
            resolvedCount = 2,
            caseId = "CIV-0001",
            category = "Broken Streetlight",
            location = "Brgy. Mapua, Laguna",
            caseStatus = "Resolved",
            onViewCase = {},
            onBack = {}
        )
    }
}