package com.civis.it114.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class CivisAppState {
    var reportCategory by mutableStateOf("")
    var reportDescription by mutableStateOf("")
    var reportLocation by mutableStateOf("")
    var reportPhotoAdded by mutableStateOf(false)

    var hasReport by mutableStateOf(false)
    var caseId by mutableStateOf("")
    var caseStatus by mutableStateOf("")
    var caseDate by mutableStateOf("Sept. 10, 2026")

    var priority by mutableStateOf("Medium")
    var assignedStaff by mutableStateOf("")

    var inspectionDate by mutableStateOf("")
    var inspectionStaffMember by mutableStateOf("")
    var inspectionFindings by mutableStateOf("")
    var inspectionNotes by mutableStateOf("")
    var inspectionResult by mutableStateOf("")

    var selectedUpdateStatus by mutableStateOf("")
    var completionNotes by mutableStateOf("")

    fun clearReportDraft() {
        reportCategory = ""
        reportDescription = ""
        reportLocation = ""
        reportPhotoAdded = false
    }

    fun submitReport() {
        hasReport = true
        caseId = "CIV-0001"
        caseStatus = "Submitted"
    }

    fun assignCase() {
        caseStatus = "Assigned"
    }

    fun saveInspection() {
        caseStatus = "For Inspection"
    }

    fun prepareCaseUpdate() {
        selectedUpdateStatus = caseStatus
        completionNotes = ""
    }

    fun saveCaseUpdate() {
        if (selectedUpdateStatus.isNotBlank()) {
            caseStatus = selectedUpdateStatus
        }
    }
}

@Composable
fun rememberCivisAppState(): CivisAppState {
    return remember {
        CivisAppState()
    }
}