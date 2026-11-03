package com.civis.it114.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CivisCase(
    val id: String,
    val category: String,
    val description: String,
    val location: String,
    val photoAdded: Boolean,
    val date: String,
    val status: String,
    val priority: String,
    val assignedStaff: String,
    val inspectionDate: String,
    val inspectionStaffMember: String,
    val inspectionFindings: String,
    val inspectionNotes: String,
    val inspectionResult: String,
    val completionNotes: String
)

class CivisAppState(
    reportCategoryState: MutableState<String>,
    reportDescriptionState: MutableState<String>,
    reportLocationState: MutableState<String>,
    reportPhotoAddedState: MutableState<Boolean>,
    nextCaseNumberState: MutableState<Int>,
    caseRecordsState: MutableState<List<List<String>>>,
    caseIdState: MutableState<String>,
    caseStatusState: MutableState<String>,
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

    var nextCaseNumber by nextCaseNumberState
    var caseRecords by caseRecordsState

    var caseId by caseIdState
    var caseStatus by caseStatusState

    var priority by priorityState
    var assignedStaff by assignedStaffState

    var inspectionDate by inspectionDateState
    var inspectionStaffMember by inspectionStaffMemberState
    var inspectionFindings by inspectionFindingsState
    var inspectionNotes by inspectionNotesState
    var inspectionResult by inspectionResultState

    var selectedUpdateStatus by selectedUpdateStatusState
    var completionNotes by completionNotesState

    private val idIndex = 0
    private val categoryIndex = 1
    private val descriptionIndex = 2
    private val locationIndex = 3
    private val photoAddedIndex = 4
    private val dateIndex = 5
    private val statusIndex = 6
    private val priorityIndex = 7
    private val assignedStaffIndex = 8
    private val inspectionDateIndex = 9
    private val inspectionStaffMemberIndex = 10
    private val inspectionFindingsIndex = 11
    private val inspectionNotesIndex = 12
    private val inspectionResultIndex = 13
    private val completionNotesIndex = 14

    private fun recordToCase(
        record: List<String>
    ): CivisCase {
        return CivisCase(
            id = record[idIndex],
            category = record[categoryIndex],
            description = record[descriptionIndex],
            location = record[locationIndex],
            photoAdded = record[photoAddedIndex].toBoolean(),
            date = record[dateIndex],
            status = record[statusIndex],
            priority = record[priorityIndex],
            assignedStaff = record[assignedStaffIndex],
            inspectionDate = record[inspectionDateIndex],
            inspectionStaffMember = record[inspectionStaffMemberIndex],
            inspectionFindings = record[inspectionFindingsIndex],
            inspectionNotes = record[inspectionNotesIndex],
            inspectionResult = record[inspectionResultIndex],
            completionNotes = record[completionNotesIndex]
        )
    }

    val cases: List<CivisCase>
        get() = caseRecords.map { record ->
            recordToCase(record)
        }

    val currentCase: CivisCase?
        get() = cases.firstOrNull {
            it.id == caseId
        }

    val latestCase: CivisCase?
        get() = cases.lastOrNull()

    val canResidentModifyCurrentCase: Boolean
        get() =
            caseStatus == "Submitted" ||
                caseStatus == "Pending"

    fun clearReportDraft() {
        reportCategory = ""
        reportDescription = ""
        reportLocation = ""
        reportPhotoAdded = false
    }

    fun submitReport() {
        val newCaseId =
            "CIV-${nextCaseNumber.toString().padStart(4, '0')}"

        val currentDate =
            SimpleDateFormat(
                "MMMM d, yyyy",
                Locale.getDefault()
            ).format(Date())

        nextCaseNumber += 1

        val newCaseRecord =
            listOf(
                newCaseId,
                reportCategory,
                reportDescription,
                reportLocation,
                reportPhotoAdded.toString(),
                currentDate,
                "Submitted",
                "Medium",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )

        caseRecords = caseRecords + listOf(newCaseRecord)

        caseId = newCaseId
        caseStatus = "Submitted"
        priority = "Medium"
        assignedStaff = ""
        inspectionDate = ""
        inspectionStaffMember = ""
        inspectionFindings = ""
        inspectionNotes = ""
        inspectionResult = ""
        selectedUpdateStatus = ""
        completionNotes = ""

        clearReportDraft()
    }

    fun selectCase(id: String) {
        val selectedCase =
            cases.firstOrNull {
                it.id == id
            }

        if (selectedCase != null) {
            caseId = selectedCase.id
            caseStatus = selectedCase.status
            priority = selectedCase.priority
            assignedStaff = selectedCase.assignedStaff
            inspectionDate = selectedCase.inspectionDate
            inspectionStaffMember =
                selectedCase.inspectionStaffMember
            inspectionFindings =
                selectedCase.inspectionFindings
            inspectionNotes =
                selectedCase.inspectionNotes
            inspectionResult =
                selectedCase.inspectionResult
            completionNotes =
                selectedCase.completionNotes
        }
    }

    fun prepareReportEdit() {
        val selectedCase = currentCase

        if (
            selectedCase != null &&
            canResidentModifyCurrentCase
        ) {
            reportCategory = selectedCase.category
            reportDescription = selectedCase.description
            reportLocation = selectedCase.location
            reportPhotoAdded = selectedCase.photoAdded
        }
    }

    fun saveReportEdit() {
        if (!canResidentModifyCurrentCase) {
            return
        }

        caseRecords =
            caseRecords.map { record ->
                if (record[idIndex] == caseId) {
                    record.mapIndexed { index, value ->
                        when (index) {
                            categoryIndex -> reportCategory
                            descriptionIndex -> reportDescription
                            locationIndex -> reportLocation
                            photoAddedIndex -> reportPhotoAdded.toString()
                            else -> value
                        }
                    }
                } else {
                    record
                }
            }

        clearReportDraft()
    }

    fun deleteCurrentCase() {
        if (!canResidentModifyCurrentCase) {
            return
        }

        val caseExists =
            caseRecords.any { record ->
                record[idIndex] == caseId
            }

        if (caseExists) {
            caseRecords =
                caseRecords.filter { record ->
                    record[idIndex] != caseId
                }

            caseId = ""
            caseStatus = ""
            priority = ""
            assignedStaff = ""
            inspectionDate = ""
            inspectionStaffMember = ""
            inspectionFindings = ""
            inspectionNotes = ""
            inspectionResult = ""
            selectedUpdateStatus = ""
            completionNotes = ""
        }
    }

    fun assignCase() {
        val caseExists =
            caseRecords.any { record ->
                record[idIndex] == caseId
            }

        if (caseExists) {
            caseStatus = "Assigned"

            caseRecords =
                caseRecords.map { record ->
                    if (record[idIndex] == caseId) {
                        record.mapIndexed { index, value ->
                            when (index) {
                                statusIndex -> caseStatus
                                priorityIndex -> priority
                                assignedStaffIndex -> assignedStaff
                                else -> value
                            }
                        }
                    } else {
                        record
                    }
                }
        }
    }

    fun saveInspection() {
        val caseExists =
            caseRecords.any { record ->
                record[idIndex] == caseId
            }

        if (
            caseExists &&
            inspectionResult.isNotBlank()
        ) {
            caseStatus = inspectionResult

            caseRecords =
                caseRecords.map { record ->
                    if (record[idIndex] == caseId) {
                        record.mapIndexed { index, value ->
                            when (index) {
                                statusIndex -> caseStatus
                                inspectionDateIndex -> inspectionDate
                                inspectionStaffMemberIndex ->
                                    inspectionStaffMember
                                inspectionFindingsIndex ->
                                    inspectionFindings
                                inspectionNotesIndex ->
                                    inspectionNotes
                                inspectionResultIndex ->
                                    inspectionResult
                                else -> value
                            }
                        }
                    } else {
                        record
                    }
                }
        }
    }

    fun prepareCaseUpdate() {
        selectedUpdateStatus = caseStatus
        completionNotes =
            currentCase?.completionNotes ?: ""
    }

    fun saveCaseUpdate() {
        val caseExists =
            caseRecords.any { record ->
                record[idIndex] == caseId
            }

        if (
            caseExists &&
            selectedUpdateStatus.isNotBlank()
        ) {
            caseStatus = selectedUpdateStatus

            caseRecords =
                caseRecords.map { record ->
                    if (record[idIndex] == caseId) {
                        record.mapIndexed { index, value ->
                            when (index) {
                                statusIndex -> caseStatus
                                completionNotesIndex ->
                                    completionNotes
                                else -> value
                            }
                        }
                    } else {
                        record
                    }
                }
        }
    }

    fun reopenCase() {
        val selectedCase = currentCase

        if (
            selectedCase != null &&
            selectedCase.status == "Resolved"
        ) {
            caseStatus = "In Progress"

            caseRecords =
                caseRecords.map { record ->
                    if (record[idIndex] == caseId) {
                        record.mapIndexed { index, value ->
                            if (index == statusIndex) {
                                caseStatus
                            } else {
                                value
                            }
                        }
                    } else {
                        record
                    }
                }
        }
    }
}

@Composable
fun rememberCivisAppState(): CivisAppState {
    val reportCategoryState =
        rememberSaveable {
            mutableStateOf("")
        }

    val reportDescriptionState =
        rememberSaveable {
            mutableStateOf("")
        }

    val reportLocationState =
        rememberSaveable {
            mutableStateOf("")
        }

    val reportPhotoAddedState =
        rememberSaveable {
            mutableStateOf(false)
        }

    val nextCaseNumberState =
        rememberSaveable {
            mutableStateOf(1)
        }

    val caseRecordsState =
        rememberSaveable {
            mutableStateOf(
                emptyList<List<String>>()
            )
        }

    val caseIdState =
        rememberSaveable {
            mutableStateOf("")
        }

    val caseStatusState =
        rememberSaveable {
            mutableStateOf("")
        }

    val priorityState =
        rememberSaveable {
            mutableStateOf("Medium")
        }

    val assignedStaffState =
        rememberSaveable {
            mutableStateOf("")
        }

    val inspectionDateState =
        rememberSaveable {
            mutableStateOf("")
        }

    val inspectionStaffMemberState =
        rememberSaveable {
            mutableStateOf("")
        }

    val inspectionFindingsState =
        rememberSaveable {
            mutableStateOf("")
        }

    val inspectionNotesState =
        rememberSaveable {
            mutableStateOf("")
        }

    val inspectionResultState =
        rememberSaveable {
            mutableStateOf("")
        }

    val selectedUpdateStatusState =
        rememberSaveable {
            mutableStateOf("")
        }

    val completionNotesState =
        rememberSaveable {
            mutableStateOf("")
        }

    return remember {
        CivisAppState(
            reportCategoryState = reportCategoryState,
            reportDescriptionState = reportDescriptionState,
            reportLocationState = reportLocationState,
            reportPhotoAddedState = reportPhotoAddedState,
            nextCaseNumberState = nextCaseNumberState,
            caseRecordsState = caseRecordsState,
            caseIdState = caseIdState,
            caseStatusState = caseStatusState,
            priorityState = priorityState,
            assignedStaffState = assignedStaffState,
            inspectionDateState = inspectionDateState,
            inspectionStaffMemberState =
                inspectionStaffMemberState,
            inspectionFindingsState =
                inspectionFindingsState,
            inspectionNotesState =
                inspectionNotesState,
            inspectionResultState =
                inspectionResultState,
            selectedUpdateStatusState =
                selectedUpdateStatusState,
            completionNotesState =
                completionNotesState
        )
    }
}