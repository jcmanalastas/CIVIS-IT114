package com.civis.it114.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class CivisAppState(
    reportCategoryState: MutableState<String>,
    reportDescriptionState: MutableState<String>,
    reportLocationState: MutableState<String>,
    reportPhotoAddedState: MutableState<Boolean>,
    hasReportState: MutableState<Boolean>,
    caseIdState: MutableState<String>,
    caseStatusState: MutableState<String>,
    caseDateState: MutableState<String>,
    priorityState: MutableState<String>,
    assignedStaffState: MutableState<String>,
    inspectionDateState: MutableState<String>,
    inspectionStaffMemberState: MutableState<String>,
    inspectionFindingsState: MutableState<String>,
    inspectionNotesState: MutableState<String>,
    inspectionResultState: MutableState<String>,
    selectedUpdateStatusState: MutableState<String>,
    completionNotesState: MutableState<String>
) {
    var reportCategory by reportCategoryState
    var reportDescription by reportDescriptionState
    var reportLocation by reportLocationState
    var reportPhotoAdded by reportPhotoAddedState

    var hasReport by hasReportState
    var caseId by caseIdState
    var caseStatus by caseStatusState
    var caseDate by caseDateState

    var priority by priorityState
    var assignedStaff by assignedStaffState

    var inspectionDate by inspectionDateState
    var inspectionStaffMember by inspectionStaffMemberState
    var inspectionFindings by inspectionFindingsState
    var inspectionNotes by inspectionNotesState
    var inspectionResult by inspectionResultState

    var selectedUpdateStatus by selectedUpdateStatusState
    var completionNotes by completionNotesState

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
    val reportCategoryState = rememberSaveable {
        mutableStateOf("")
    }
    val reportDescriptionState = rememberSaveable {
        mutableStateOf("")
    }
    val reportLocationState = rememberSaveable {
        mutableStateOf("")
    }
    val reportPhotoAddedState = rememberSaveable {
        mutableStateOf(false)
    }

    val hasReportState = rememberSaveable {
        mutableStateOf(false)
    }
    val caseIdState = rememberSaveable {
        mutableStateOf("")
    }
    val caseStatusState = rememberSaveable {
        mutableStateOf("")
    }
    val caseDateState = rememberSaveable {
        mutableStateOf("Sept. 10, 2026")
    }

    val priorityState = rememberSaveable {
        mutableStateOf("Medium")
    }
    val assignedStaffState = rememberSaveable {
        mutableStateOf("")
    }

    val inspectionDateState = rememberSaveable {
        mutableStateOf("")
    }
    val inspectionStaffMemberState = rememberSaveable {
        mutableStateOf("")
    }
    val inspectionFindingsState = rememberSaveable {
        mutableStateOf("")
    }
    val inspectionNotesState = rememberSaveable {
        mutableStateOf("")
    }
    val inspectionResultState = rememberSaveable {
        mutableStateOf("")
    }

    val selectedUpdateStatusState = rememberSaveable {
        mutableStateOf("")
    }
    val completionNotesState = rememberSaveable {
        mutableStateOf("")
    }

    return remember {
        CivisAppState(
            reportCategoryState = reportCategoryState,
            reportDescriptionState = reportDescriptionState,
            reportLocationState = reportLocationState,
            reportPhotoAddedState = reportPhotoAddedState,
            hasReportState = hasReportState,
            caseIdState = caseIdState,
            caseStatusState = caseStatusState,
            caseDateState = caseDateState,
            priorityState = priorityState,
            assignedStaffState = assignedStaffState,
            inspectionDateState = inspectionDateState,
            inspectionStaffMemberState = inspectionStaffMemberState,
            inspectionFindingsState = inspectionFindingsState,
            inspectionNotesState = inspectionNotesState,
            inspectionResultState = inspectionResultState,
            selectedUpdateStatusState = selectedUpdateStatusState,
            completionNotesState = completionNotesState
        )
    }
}