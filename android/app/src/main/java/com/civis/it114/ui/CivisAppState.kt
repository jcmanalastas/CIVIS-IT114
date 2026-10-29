package com.civis.it114.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

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
    caseIdsState: MutableState<List<String>>,
    caseCategoriesState: MutableState<List<String>>,
    caseDescriptionsState: MutableState<List<String>>,
    caseLocationsState: MutableState<List<String>>,
    casePhotoAddedState: MutableState<List<Boolean>>,
    caseDatesState: MutableState<List<String>>,
    caseStatusesState: MutableState<List<String>>,
    casePrioritiesState: MutableState<List<String>>,
    caseAssignedStaffState: MutableState<List<String>>,
    caseInspectionDatesState: MutableState<List<String>>,
    caseInspectionStaffMembersState: MutableState<List<String>>,
    caseInspectionFindingsState: MutableState<List<String>>,
    caseInspectionNotesState: MutableState<List<String>>,
    caseInspectionResultsState: MutableState<List<String>>,
    caseCompletionNotesState: MutableState<List<String>>,
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

    var caseIds by caseIdsState
    var caseCategories by caseCategoriesState
    var caseDescriptions by caseDescriptionsState
    var caseLocations by caseLocationsState
    var casePhotoAdded by casePhotoAddedState
    var caseDates by caseDatesState
    var caseStatuses by caseStatusesState
    var casePriorities by casePrioritiesState
    var caseAssignedStaff by caseAssignedStaffState
    var caseInspectionDates by caseInspectionDatesState
    var caseInspectionStaffMembers by caseInspectionStaffMembersState
    var caseInspectionFindings by caseInspectionFindingsState
    var caseInspectionNotes by caseInspectionNotesState
    var caseInspectionResults by caseInspectionResultsState
    var caseCompletionNotes by caseCompletionNotesState

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

    val cases: List<CivisCase>
        get() {
            return caseIds.indices.map { index ->
                CivisCase(
                    id = caseIds[index],
                    category = caseCategories[index],
                    description = caseDescriptions[index],
                    location = caseLocations[index],
                    photoAdded = casePhotoAdded[index],
                    date = caseDates[index],
                    status = caseStatuses[index],
                    priority = casePriorities[index],
                    assignedStaff = caseAssignedStaff[index],
                    inspectionDate = caseInspectionDates[index],
                    inspectionStaffMember = caseInspectionStaffMembers[index],
                    inspectionFindings = caseInspectionFindings[index],
                    inspectionNotes = caseInspectionNotes[index],
                    inspectionResult = caseInspectionResults[index],
                    completionNotes = caseCompletionNotes[index]
                )
            }
        }

    val hasReport: Boolean
        get() = caseIds.isNotEmpty()

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
        val highestCaseNumber = caseIds
            .map {
                it.removePrefix("CIV-").toIntOrNull() ?: 0
            }
            .maxOrNull() ?: 0

        val newCaseId =
            "CIV-${(highestCaseNumber + 1).toString().padStart(4, '0')}"

        caseIds = caseIds + newCaseId
        caseCategories = caseCategories + reportCategory
        caseDescriptions = caseDescriptions + reportDescription
        caseLocations = caseLocations + reportLocation
        casePhotoAdded = casePhotoAdded + reportPhotoAdded
        caseDates = caseDates + "Sept. 10, 2026"
        caseStatuses = caseStatuses + "Submitted"
        casePriorities = casePriorities + "Medium"
        caseAssignedStaff = caseAssignedStaff + ""
        caseInspectionDates = caseInspectionDates + ""
        caseInspectionStaffMembers = caseInspectionStaffMembers + ""
        caseInspectionFindings = caseInspectionFindings + ""
        caseInspectionNotes = caseInspectionNotes + ""
        caseInspectionResults = caseInspectionResults + ""
        caseCompletionNotes = caseCompletionNotes + ""

        caseId = newCaseId
        caseStatus = "Submitted"
        caseDate = "Sept. 10, 2026"
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
        val index = caseIds.indexOf(id)

        if (index >= 0) {
            caseId = caseIds[index]
            caseStatus = caseStatuses[index]
            caseDate = caseDates[index]
            priority = casePriorities[index]
            assignedStaff = caseAssignedStaff[index]
            inspectionDate = caseInspectionDates[index]
            inspectionStaffMember = caseInspectionStaffMembers[index]
            inspectionFindings = caseInspectionFindings[index]
            inspectionNotes = caseInspectionNotes[index]
            inspectionResult = caseInspectionResults[index]
            completionNotes = caseCompletionNotes[index]
        }
    }

    fun selectLatestCase() {
        val latestCaseId = caseIds.lastOrNull()

        if (latestCaseId != null) {
            selectCase(latestCaseId)
        }
    }

    fun prepareReportEdit() {
        val currentCase = currentCase

        if (
            currentCase != null &&
            canResidentModifyCurrentCase
        ) {
            reportCategory = currentCase.category
            reportDescription = currentCase.description
            reportLocation = currentCase.location
            reportPhotoAdded = currentCase.photoAdded
        }
    }

    fun saveReportEdit() {
        val index = caseIds.indexOf(caseId)

        if (
            index >= 0 &&
            canResidentModifyCurrentCase
        ) {
            caseCategories =
                caseCategories.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        reportCategory
                    } else {
                        value
                    }
                }

            caseDescriptions =
                caseDescriptions.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        reportDescription
                    } else {
                        value
                    }
                }

            caseLocations =
                caseLocations.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        reportLocation
                    } else {
                        value
                    }
                }

            casePhotoAdded =
                casePhotoAdded.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        reportPhotoAdded
                    } else {
                        value
                    }
                }

            clearReportDraft()
        }
    }

    fun deleteCurrentCase() {
        val index = caseIds.indexOf(caseId)

        if (
            index >= 0 &&
            canResidentModifyCurrentCase
        ) {
            caseIds = caseIds.filterIndexed { itemIndex, _ ->
                itemIndex != index
            }
            caseCategories =
                caseCategories.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseDescriptions =
                caseDescriptions.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseLocations =
                caseLocations.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            casePhotoAdded =
                casePhotoAdded.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseDates =
                caseDates.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseStatuses =
                caseStatuses.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            casePriorities =
                casePriorities.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseAssignedStaff =
                caseAssignedStaff.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseInspectionDates =
                caseInspectionDates.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseInspectionStaffMembers =
                caseInspectionStaffMembers.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseInspectionFindings =
                caseInspectionFindings.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseInspectionNotes =
                caseInspectionNotes.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseInspectionResults =
                caseInspectionResults.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }
            caseCompletionNotes =
                caseCompletionNotes.filterIndexed { itemIndex, _ ->
                    itemIndex != index
                }

            caseId = ""
            caseStatus = ""
            caseDate = ""
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
        val index = caseIds.indexOf(caseId)

        if (index >= 0) {
            caseStatus = "Assigned"

            caseStatuses = caseStatuses.mapIndexed { itemIndex, value ->
                if (itemIndex == index) {
                    caseStatus
                } else {
                    value
                }
            }

            casePriorities = casePriorities.mapIndexed { itemIndex, value ->
                if (itemIndex == index) {
                    priority
                } else {
                    value
                }
            }

            caseAssignedStaff =
                caseAssignedStaff.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        assignedStaff
                    } else {
                        value
                    }
                }
        }
    }

    fun saveInspection() {
        val index = caseIds.indexOf(caseId)

        if (index >= 0 && inspectionResult.isNotBlank()) {
            caseStatus = inspectionResult

            caseStatuses = caseStatuses.mapIndexed { itemIndex, value ->
                if (itemIndex == index) {
                    caseStatus
                } else {
                    value
                }
            }

            caseInspectionDates =
                caseInspectionDates.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        inspectionDate
                    } else {
                        value
                    }
                }

            caseInspectionStaffMembers =
                caseInspectionStaffMembers.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        inspectionStaffMember
                    } else {
                        value
                    }
                }

            caseInspectionFindings =
                caseInspectionFindings.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        inspectionFindings
                    } else {
                        value
                    }
                }

            caseInspectionNotes =
                caseInspectionNotes.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        inspectionNotes
                    } else {
                        value
                    }
                }

            caseInspectionResults =
                caseInspectionResults.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        inspectionResult
                    } else {
                        value
                    }
                }
        }
    }

    fun prepareCaseUpdate() {
        selectedUpdateStatus = caseStatus
        completionNotes = currentCase?.completionNotes ?: ""
    }

    fun saveCaseUpdate() {
        val index = caseIds.indexOf(caseId)

        if (index >= 0 && selectedUpdateStatus.isNotBlank()) {
            caseStatus = selectedUpdateStatus

            caseStatuses = caseStatuses.mapIndexed { itemIndex, value ->
                if (itemIndex == index) {
                    caseStatus
                } else {
                    value
                }
            }

            caseCompletionNotes =
                caseCompletionNotes.mapIndexed { itemIndex, value ->
                    if (itemIndex == index) {
                        completionNotes
                    } else {
                        value
                    }
                }
        }
    }

    fun reopenCase() {
        val index = caseIds.indexOf(caseId)

        if (
            index >= 0 &&
            caseStatuses[index] == "Resolved"
        ) {
            caseStatus = "In Progress"

            caseStatuses = caseStatuses.mapIndexed { itemIndex, value ->
                if (itemIndex == index) {
                    caseStatus
                } else {
                    value
                }
            }
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

    val caseIdsState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseCategoriesState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseDescriptionsState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseLocationsState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val casePhotoAddedState = rememberSaveable {
        mutableStateOf(emptyList<Boolean>())
    }
    val caseDatesState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseStatusesState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val casePrioritiesState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseAssignedStaffState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseInspectionDatesState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseInspectionStaffMembersState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseInspectionFindingsState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseInspectionNotesState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseInspectionResultsState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }
    val caseCompletionNotesState = rememberSaveable {
        mutableStateOf(emptyList<String>())
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
            caseIdsState = caseIdsState,
            caseCategoriesState = caseCategoriesState,
            caseDescriptionsState = caseDescriptionsState,
            caseLocationsState = caseLocationsState,
            casePhotoAddedState = casePhotoAddedState,
            caseDatesState = caseDatesState,
            caseStatusesState = caseStatusesState,
            casePrioritiesState = casePrioritiesState,
            caseAssignedStaffState = caseAssignedStaffState,
            caseInspectionDatesState = caseInspectionDatesState,
            caseInspectionStaffMembersState = caseInspectionStaffMembersState,
            caseInspectionFindingsState = caseInspectionFindingsState,
            caseInspectionNotesState = caseInspectionNotesState,
            caseInspectionResultsState = caseInspectionResultsState,
            caseCompletionNotesState = caseCompletionNotesState,
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