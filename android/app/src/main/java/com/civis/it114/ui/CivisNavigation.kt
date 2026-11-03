package com.civis.it114.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.civis.it114.ui.screens.RoleSelectionScreen
import com.civis.it114.ui.screens.resident.CaseStatusScreen
import com.civis.it114.ui.screens.resident.ReportIssueScreen
import com.civis.it114.ui.screens.resident.ResidentHomeScreen
import com.civis.it114.ui.screens.resident.ResidentReportsScreen
import com.civis.it114.ui.screens.resident.ReviewReportScreen
import com.civis.it114.ui.screens.resident.SubmitConfirmationScreen
import com.civis.it114.ui.screens.staff.CaseDetailsScreen
import com.civis.it114.ui.screens.staff.CaseUpdateScreen
import com.civis.it114.ui.screens.staff.RecordInspectionScreen
import com.civis.it114.ui.screens.staff.StaffCasesScreen
import com.civis.it114.ui.screens.staff.StaffConfirmationScreen
import com.civis.it114.ui.screens.staff.StaffHomeScreen

object CivisRoutes {
    const val RoleSelection = "role_selection"
    const val ResidentHome = "resident_home"
    const val ResidentReports = "resident_reports"
    const val ReportIssue = "report_issue"
    const val ReviewReport = "review_report"
    const val ViewReport = "view_report"
    const val EditReport = "edit_report"
    const val ReviewEditReport = "review_edit_report"
    const val SubmitConfirmation = "submit_confirmation"
    const val CaseStatus = "case_status"
    const val StaffHome = "staff_home"
    const val StaffCases = "staff_cases"
    const val CaseDetails = "case_details"
    const val AssignmentConfirmation = "assignment_confirmation"
    const val AssignmentUpdateConfirmation = "assignment_update_confirmation"
    const val RecordInspection = "record_inspection"
    const val InspectionConfirmation = "inspection_confirmation"
    const val CaseUpdate = "case_update"
}

private fun navigateFrom(
    navController: NavHostController,
    sourceRoute: String,
    destinationRoute: String
) {
    if (navController.currentDestination?.route == sourceRoute) {
        navController.navigate(destinationRoute)
    }
}

private fun popBackFrom(
    navController: NavHostController,
    sourceRoute: String
) {
    if (navController.currentDestination?.route == sourceRoute) {
        navController.popBackStack()
    }
}

@Composable
fun CivisNavigation(
    appState: CivisAppState
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CivisRoutes.RoleSelection
    ) {
        composable(CivisRoutes.RoleSelection) {
            RoleSelectionScreen(
                onViewAsResident = {
                    navigateFrom(
                        navController,
                        CivisRoutes.RoleSelection,
                        CivisRoutes.ResidentHome
                    )
                },
                onViewAsStaff = {
                    navigateFrom(
                        navController,
                        CivisRoutes.RoleSelection,
                        CivisRoutes.StaffHome
                    )
                }
            )
        }

        composable(CivisRoutes.ResidentHome) {
            ResidentHomeScreen(
                cases = appState.cases,
                onReportIssue = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ResidentHome
                    ) {
                        appState.clearReportDraft()

                        navigateFrom(
                            navController,
                            CivisRoutes.ResidentHome,
                            CivisRoutes.ReportIssue
                        )
                    }
                },
                onTrackReports = {
                    navigateFrom(
                        navController,
                        CivisRoutes.ResidentHome,
                        CivisRoutes.ResidentReports
                    )
                },
                onViewReport = { caseId ->
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ResidentHome
                    ) {
                        appState.selectCase(caseId)

                        navigateFrom(
                            navController,
                            CivisRoutes.ResidentHome,
                            CivisRoutes.ViewReport
                        )
                    }
                },
                onBack = {
                    popBackFrom(
                        navController,
                        CivisRoutes.ResidentHome
                    )
                }
            )
        }

        composable(CivisRoutes.ResidentReports) {
            ResidentReportsScreen(
                cases = appState.cases,
                onTrackReport = { caseId ->
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ResidentReports
                    ) {
                        appState.selectCase(caseId)

                        navigateFrom(
                            navController,
                            CivisRoutes.ResidentReports,
                            CivisRoutes.CaseStatus
                        )
                    }
                },
                onViewReport = { caseId ->
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ResidentReports
                    ) {
                        appState.selectCase(caseId)

                        navigateFrom(
                            navController,
                            CivisRoutes.ResidentReports,
                            CivisRoutes.ViewReport
                        )
                    }
                },
                onBack = {
                    popBackFrom(
                        navController,
                        CivisRoutes.ResidentReports
                    )
                },
                onHome = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ResidentReports
                    ) {
                        navController.popBackStack(
                            CivisRoutes.ResidentHome,
                            false
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.ReportIssue) {
            ReportIssueScreen(
                selectedCategory = appState.reportCategory,
                description = appState.reportDescription,
                location = appState.reportLocation,
                photoAdded = appState.reportPhotoAdded,
                onCategoryChange = {
                    appState.reportCategory = it
                },
                onDescriptionChange = {
                    appState.reportDescription = it
                },
                onLocationChange = {
                    appState.reportLocation = it
                },
                onPhotoChange = {
                    appState.reportPhotoAdded = it
                },
                onContinue = {
                    navigateFrom(
                        navController,
                        CivisRoutes.ReportIssue,
                        CivisRoutes.ReviewReport
                    )
                },
                onCancel = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ReportIssue
                    ) {
                        appState.clearReportDraft()

                        popBackFrom(
                            navController,
                            CivisRoutes.ReportIssue
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.ReviewReport) {
            ReviewReportScreen(
                category = appState.reportCategory,
                description = appState.reportDescription,
                location = appState.reportLocation,
                photoAdded = appState.reportPhotoAdded,
                submitted = false,
                status = "",
                inspectionNotes = "",
                completionNotes = "",
                canModify = false,
                onBack = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ReviewReport
                    ) {
                        navController.popBackStack(
                            CivisRoutes.ReportIssue,
                            false
                        )
                    }
                },
                onEdit = {
                    popBackFrom(
                        navController,
                        CivisRoutes.ReviewReport
                    )
                },
                onDelete = {},
                onSubmitReport = {
                    navigateFrom(
                        navController,
                        CivisRoutes.ReviewReport,
                        CivisRoutes.SubmitConfirmation
                    )
                },
                onBackToHome = {}
            )
        }

        composable(CivisRoutes.ViewReport) {
            val currentCase = appState.currentCase

            ReviewReportScreen(
                category = currentCase?.category ?: "",
                description = currentCase?.description ?: "",
                location = currentCase?.location ?: "",
                photoAdded = currentCase?.photoAdded ?: false,
                submitted = true,
                status = currentCase?.status ?: "",
                inspectionNotes = currentCase?.inspectionNotes ?: "",
                completionNotes = currentCase?.completionNotes ?: "",
                canModify = appState.canResidentModifyCurrentCase,
                onBack = {
                    popBackFrom(
                        navController,
                        CivisRoutes.ViewReport
                    )
                },
                onEdit = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ViewReport &&
                        appState.canResidentModifyCurrentCase
                    ) {
                        appState.prepareReportEdit()

                        navigateFrom(
                            navController,
                            CivisRoutes.ViewReport,
                            CivisRoutes.EditReport
                        )
                    }
                },
                onDelete = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ViewReport &&
                        appState.canResidentModifyCurrentCase
                    ) {
                        appState.deleteCurrentCase()

                        navController.popBackStack(
                            CivisRoutes.ResidentHome,
                            false
                        )
                    }
                },
                onSubmitReport = {},
                onBackToHome = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ViewReport
                    ) {
                        navController.popBackStack(
                            CivisRoutes.ResidentHome,
                            false
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.EditReport) {
            ReportIssueScreen(
                selectedCategory = appState.reportCategory,
                description = appState.reportDescription,
                location = appState.reportLocation,
                photoAdded = appState.reportPhotoAdded,
                onCategoryChange = {
                    appState.reportCategory = it
                },
                onDescriptionChange = {
                    appState.reportDescription = it
                },
                onLocationChange = {
                    appState.reportLocation = it
                },
                onPhotoChange = {
                    appState.reportPhotoAdded = it
                },
                onContinue = {
                    navigateFrom(
                        navController,
                        CivisRoutes.EditReport,
                        CivisRoutes.ReviewEditReport
                    )
                },
                onCancel = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.EditReport
                    ) {
                        appState.clearReportDraft()

                        popBackFrom(
                            navController,
                            CivisRoutes.EditReport
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.ReviewEditReport) {
            ReviewReportScreen(
                category = appState.reportCategory,
                description = appState.reportDescription,
                location = appState.reportLocation,
                photoAdded = appState.reportPhotoAdded,
                submitted = false,
                status = "",
                inspectionNotes = "",
                completionNotes = "",
                canModify = false,
                onBack = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ReviewEditReport
                    ) {
                        navController.popBackStack(
                            CivisRoutes.EditReport,
                            false
                        )
                    }
                },
                onEdit = {
                    popBackFrom(
                        navController,
                        CivisRoutes.ReviewEditReport
                    )
                },
                onDelete = {},
                onSubmitReport = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.ReviewEditReport &&
                        appState.canResidentModifyCurrentCase
                    ) {
                        appState.saveReportEdit()

                        navController.popBackStack(
                            CivisRoutes.ResidentHome,
                            false
                        )
                    }
                },
                onBackToHome = {}
            )
        }

        composable(CivisRoutes.SubmitConfirmation) {
            SubmitConfirmationScreen(
                onCancel = {
                    popBackFrom(
                        navController,
                        CivisRoutes.SubmitConfirmation
                    )
                },
                onSubmit = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.SubmitConfirmation
                    ) {
                        appState.submitReport()

                        navController.popBackStack(
                            CivisRoutes.ResidentHome,
                            false
                        )

                        navigateFrom(
                            navController,
                            CivisRoutes.ResidentHome,
                            CivisRoutes.CaseStatus
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.CaseStatus) {
            val currentCase = appState.currentCase

            CaseStatusScreen(
                caseId = currentCase?.id ?: "",
                status = currentCase?.status ?: "",
                onViewCase = {
                    if (currentCase != null) {
                        navigateFrom(
                            navController,
                            CivisRoutes.CaseStatus,
                            CivisRoutes.ViewReport
                        )
                    }
                },
                onBackToHome = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.CaseStatus
                    ) {
                        navController.popBackStack(
                            CivisRoutes.ResidentHome,
                            false
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.StaffHome) {
            val latestCase = appState.latestCase

            val pendingCount = appState.cases.count {
                it.status == "Submitted" ||
                    it.status == "Pending"
            }

            val underReviewCount = appState.cases.count {
                it.status == "Assigned" ||
                    it.status == "For Inspection"
            }

            val inProgressCount = appState.cases.count {
                it.status == "Needs Repair" ||
                    it.status == "In Progress"
            }

            val resolvedCount = appState.cases.count {
                it.status == "Resolved"
            }

            StaffHomeScreen(
                pendingCount = pendingCount,
                underReviewCount = underReviewCount,
                inProgressCount = inProgressCount,
                resolvedCount = resolvedCount,
                caseId = latestCase?.id ?: "",
                category = latestCase?.category ?: "",
                location = latestCase?.location ?: "",
                caseStatus = latestCase?.status ?: "",
                hasCases = appState.cases.isNotEmpty(),
                onViewCase = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.StaffHome &&
                        latestCase != null
                    ) {
                        appState.selectCase(latestCase.id)

                        navigateFrom(
                            navController,
                            CivisRoutes.StaffHome,
                            CivisRoutes.CaseDetails
                        )
                    }
                },
                onViewAllCases = {
                    navigateFrom(
                        navController,
                        CivisRoutes.StaffHome,
                        CivisRoutes.StaffCases
                    )
                },
                onBack = {
                    popBackFrom(
                        navController,
                        CivisRoutes.StaffHome
                    )
                }
            )
        }

        composable(CivisRoutes.StaffCases) {
            StaffCasesScreen(
                cases = appState.cases,
                onViewCase = { caseId ->
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.StaffCases
                    ) {
                        appState.selectCase(caseId)

                        navigateFrom(
                            navController,
                            CivisRoutes.StaffCases,
                            CivisRoutes.CaseDetails
                        )
                    }
                },
                onBack = {
                    popBackFrom(
                        navController,
                        CivisRoutes.StaffCases
                    )
                },
                onHome = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.StaffCases
                    ) {
                        navController.popBackStack(
                            CivisRoutes.StaffHome,
                            false
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.CaseDetails) {
            val currentCase = appState.currentCase

            CaseDetailsScreen(
                caseId = currentCase?.id ?: "",
                category = currentCase?.category ?: "",
                date = currentCase?.date ?: "",
                status = currentCase?.status ?: "",
                description = currentCase?.description ?: "",
                location = currentCase?.location ?: "",
                priority = appState.priority,
                assignedStaff = appState.assignedStaff,
                completionNotes = currentCase?.completionNotes ?: "",
                onPriorityChange = {
                    appState.priority = it
                },
                onAssignedStaffChange = {
                    appState.assignedStaff = it
                },
                onBack = {
                    popBackFrom(
                        navController,
                        CivisRoutes.CaseDetails
                    )
                },
                onAssignCase = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.CaseDetails
                    ) {
                        navController.popBackStack(
                            CivisRoutes.StaffHome,
                            false
                        )

                        appState.assignCase()

                        navigateFrom(
                            navController,
                            CivisRoutes.StaffHome,
                            CivisRoutes.AssignmentConfirmation
                        )
                    }
                },
                onUpdateAssignment = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.CaseDetails
                    ) {
                        appState.assignCase()

                        navigateFrom(
                            navController,
                            CivisRoutes.CaseDetails,
                            CivisRoutes.AssignmentUpdateConfirmation
                        )
                    }
                },
                onRecordInspection = {
                    navigateFrom(
                        navController,
                        CivisRoutes.CaseDetails,
                        CivisRoutes.RecordInspection
                    )
                },
                onUpdateCase = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.CaseDetails
                    ) {
                        appState.prepareCaseUpdate()

                        navigateFrom(
                            navController,
                            CivisRoutes.CaseDetails,
                            CivisRoutes.CaseUpdate
                        )
                    }
                },
                onReopenCase = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.CaseDetails &&
                        currentCase?.status == "Resolved"
                    ) {
                        appState.reopenCase()
                    }
                }
            )
        }

        composable(CivisRoutes.AssignmentConfirmation) {
            val currentCase = appState.currentCase

            StaffConfirmationScreen(
                title = "Case Assigned",
                message = "The case has been assigned successfully.",
                caseId = currentCase?.id ?: "",
                status = currentCase?.status ?: "",
                onBackToHome = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.AssignmentConfirmation
                    ) {
                        navController.popBackStack(
                            CivisRoutes.StaffHome,
                            false
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.AssignmentUpdateConfirmation) {
            val currentCase = appState.currentCase

            StaffConfirmationScreen(
                title = "Case Updated",
                message = "The case assignment has been updated successfully.",
                caseId = currentCase?.id ?: "",
                status = currentCase?.status ?: "",
                onBackToHome = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.AssignmentUpdateConfirmation
                    ) {
                        navController.popBackStack(
                            CivisRoutes.StaffHome,
                            false
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.RecordInspection) {
            val currentCase = appState.currentCase

            RecordInspectionScreen(
                caseId = currentCase?.id ?: "",
                submissionDate = appState.currentCase?.date.orEmpty(),
                inspectionDate = appState.inspectionDate,
                staffMember = appState.inspectionStaffMember,
                findings = appState.inspectionFindings,
                notes = appState.inspectionNotes,
                result = appState.inspectionResult,
                onInspectionDateChange = {
                    appState.inspectionDate = it
                },
                onStaffMemberChange = {
                    appState.inspectionStaffMember = it
                },
                onFindingsChange = {
                    appState.inspectionFindings = it
                },
                onNotesChange = {
                    appState.inspectionNotes = it
                },
                onResultChange = {
                    appState.inspectionResult = it
                },
                onBack = {
                    popBackFrom(
                        navController,
                        CivisRoutes.RecordInspection
                    )
                },
                onSaveInspection = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.RecordInspection
                    ) {
                        appState.saveInspection()

                        navController.popBackStack(
                            CivisRoutes.StaffHome,
                            false
                        )

                        navigateFrom(
                            navController,
                            CivisRoutes.StaffHome,
                            CivisRoutes.InspectionConfirmation
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.InspectionConfirmation) {
            val currentCase = appState.currentCase

            StaffConfirmationScreen(
                title = "Inspection Record Saved",
                message = "The inspection record has been saved successfully.",
                caseId = currentCase?.id ?: "",
                status = currentCase?.status ?: "",
                onBackToHome = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.InspectionConfirmation
                    ) {
                        navController.popBackStack(
                            CivisRoutes.StaffHome,
                            false
                        )
                    }
                }
            )
        }

        composable(CivisRoutes.CaseUpdate) {
            val currentCase = appState.currentCase

            CaseUpdateScreen(
                caseId = currentCase?.id ?: "",
                category = currentCase?.category ?: "",
                currentStatus = currentCase?.status ?: "",
                selectedStatus = appState.selectedUpdateStatus,
                completionNotes = appState.completionNotes,
                onStatusChange = {
                    appState.selectedUpdateStatus = it
                },
                onCompletionNotesChange = {
                    appState.completionNotes = it
                },
                onBack = {
                    popBackFrom(
                        navController,
                        CivisRoutes.CaseUpdate
                    )
                },
                onSaveUpdate = {
                    if (
                        navController.currentDestination?.route ==
                        CivisRoutes.CaseUpdate
                    ) {
                        appState.saveCaseUpdate()

                        navController.popBackStack(
                            CivisRoutes.StaffHome,
                            false
                        )
                    }
                }
            )
        }
    }
}