package com.civis.it114.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.civis.it114.ui.screens.RoleSelectionScreen
import com.civis.it114.ui.screens.resident.CaseStatusScreen
import com.civis.it114.ui.screens.resident.ReportIssueScreen
import com.civis.it114.ui.screens.resident.ResidentHomeScreen
import com.civis.it114.ui.screens.resident.ReviewReportScreen
import com.civis.it114.ui.screens.resident.SubmitConfirmationScreen
import com.civis.it114.ui.screens.staff.CaseDetailsScreen
import com.civis.it114.ui.screens.staff.CaseUpdateScreen
import com.civis.it114.ui.screens.staff.RecordInspectionScreen
import com.civis.it114.ui.screens.staff.StaffConfirmationScreen
import com.civis.it114.ui.screens.staff.StaffHomeScreen

object CivisRoutes {
    const val RoleSelection = "role_selection"
    const val ResidentHome = "resident_home"
    const val ReportIssue = "report_issue"
    const val ReviewReport = "review_report"
    const val ViewReport = "view_report"
    const val SubmitConfirmation = "submit_confirmation"
    const val CaseStatus = "case_status"
    const val StaffHome = "staff_home"
    const val CaseDetails = "case_details"
    const val AssignmentConfirmation = "assignment_confirmation"
    const val RecordInspection = "record_inspection"
    const val InspectionConfirmation = "inspection_confirmation"
    const val CaseUpdate = "case_update"
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
                    navController.navigate(CivisRoutes.ResidentHome)
                },
                onViewAsStaff = {
                    navController.navigate(CivisRoutes.StaffHome)
                }
            )
        }

        composable(CivisRoutes.ResidentHome) {
            ResidentHomeScreen(
                hasReport = appState.hasReport,
                caseId = appState.caseId,
                category = appState.reportCategory,
                location = appState.reportLocation,
                status = appState.caseStatus,
                onReportIssue = {
                    navController.navigate(CivisRoutes.ReportIssue)
                },
                onTrackReports = {
                    if (appState.hasReport) {
                        navController.navigate(CivisRoutes.CaseStatus)
                    }
                },
                onViewReport = {
                    if (appState.hasReport) {
                        navController.navigate(CivisRoutes.ViewReport)
                    }
                },
                onBack = {
                    navController.popBackStack()
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
                    navController.navigate(CivisRoutes.ReviewReport)
                },
                onCancel = {
                    appState.clearReportDraft()
                    navController.popBackStack()
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
                onBack = {
                    navController.popBackStack(
                        CivisRoutes.ReportIssue,
                        false
                    )
                },
                onEdit = {
                    navController.popBackStack()
                },
                onSubmitReport = {
                    navController.navigate(
                        CivisRoutes.SubmitConfirmation
                    )
                },
                onBackToHome = {}
            )
        }

        composable(CivisRoutes.ViewReport) {
            ReviewReportScreen(
                category = appState.reportCategory,
                description = appState.reportDescription,
                location = appState.reportLocation,
                photoAdded = appState.reportPhotoAdded,
                submitted = true,
                onBack = {
                    navController.popBackStack()
                },
                onEdit = {},
                onSubmitReport = {},
                onBackToHome = {
                    navController.popBackStack(
                        CivisRoutes.ResidentHome,
                        false
                    )
                }
            )
        }

        composable(CivisRoutes.SubmitConfirmation) {
            SubmitConfirmationScreen(
                onCancel = {
                    navController.popBackStack()
                },
                onSubmit = {
                    appState.submitReport()

                    navController.popBackStack(
                        CivisRoutes.ResidentHome,
                        false
                    )

                    navController.navigate(
                        CivisRoutes.CaseStatus
                    )
                }
            )
        }

        composable(CivisRoutes.CaseStatus) {
            CaseStatusScreen(
                caseId = appState.caseId,
                status = appState.caseStatus,
                onViewCase = {
                    navController.navigate(CivisRoutes.ViewReport)
                },
                onBackToHome = {
                    navController.popBackStack(
                        CivisRoutes.ResidentHome,
                        false
                    )
                }
            )
        }

        composable(CivisRoutes.StaffHome) {
            StaffHomeScreen(
                pendingCount = 5,
                underReviewCount = 3,
                inProgressCount = 4,
                resolvedCount = 2,
                caseId = appState.caseId.ifBlank {
                    "CIV-0001"
                },
                category = appState.reportCategory.ifBlank {
                    "Broken Streetlight"
                },
                location = appState.reportLocation.ifBlank {
                    "Brgy. Mapag, Laguna"
                },
                caseStatus = appState.caseStatus.ifBlank {
                    "Pending"
                },
                onViewCase = {
                    navController.navigate(CivisRoutes.CaseDetails)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(CivisRoutes.CaseDetails) {
            CaseDetailsScreen(
                caseId = appState.caseId.ifBlank {
                    "CIV-0001"
                },
                category = appState.reportCategory.ifBlank {
                    "Broken Streetlight"
                },
                date = appState.caseDate,
                status = appState.caseStatus.ifBlank {
                    "Pending"
                },
                description = appState.reportDescription.ifBlank {
                    "Streetlight is not working at night."
                },
                location = appState.reportLocation.ifBlank {
                    "Brgy. Mapag, Laguna"
                },
                priority = appState.priority,
                assignedStaff = appState.assignedStaff,
                onPriorityChange = {
                    appState.priority = it
                },
                onAssignedStaffChange = {
                    appState.assignedStaff = it
                },
                onBack = {
                    navController.popBackStack(
                        CivisRoutes.StaffHome,
                        false
                    )
                },
                onAssignCase = {
                    appState.assignCase()

                    navController.popBackStack(
                        CivisRoutes.StaffHome,
                        false
                    )

                    navController.navigate(
                        CivisRoutes.AssignmentConfirmation
                    )
                },
                onRecordInspection = {
                    navController.navigate(
                        CivisRoutes.RecordInspection
                    )
                },
                onUpdateCase = {
                    appState.prepareCaseUpdate()

                    navController.navigate(
                        CivisRoutes.CaseUpdate
                    )
                }
            )
        }

        composable(CivisRoutes.AssignmentConfirmation) {
            StaffConfirmationScreen(
                title = "Case Assigned",
                message = "The case has been assigned successfully.",
                caseId = appState.caseId.ifBlank {
                    "CIV-0001"
                },
                status = appState.caseStatus,
                onBackToHome = {
                    navController.popBackStack(
                        CivisRoutes.StaffHome,
                        false
                    )
                }
            )
        }

        composable(CivisRoutes.RecordInspection) {
            RecordInspectionScreen(
                caseId = appState.caseId.ifBlank {
                    "CIV-0001"
                },
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
                    navController.popBackStack()
                },
                onSaveInspection = {
                    appState.saveInspection()

                    navController.popBackStack(
                        CivisRoutes.StaffHome,
                        false
                    )

                    navController.navigate(
                        CivisRoutes.InspectionConfirmation
                    )
                }
            )
        }

        composable(CivisRoutes.InspectionConfirmation) {
            StaffConfirmationScreen(
                title = "Inspection Record Saved",
                message = "The inspection record has been saved successfully.",
                caseId = appState.caseId.ifBlank {
                    "CIV-0001"
                },
                status = appState.caseStatus,
                onBackToHome = {
                    navController.popBackStack(
                        CivisRoutes.StaffHome,
                        false
                    )
                }
            )
        }

        composable(CivisRoutes.CaseUpdate) {
            CaseUpdateScreen(
                caseId = appState.caseId.ifBlank {
                    "CIV-0001"
                },
                category = appState.reportCategory.ifBlank {
                    "Broken Streetlight"
                },
                currentStatus = appState.caseStatus,
                selectedStatus = appState.selectedUpdateStatus,
                completionNotes = appState.completionNotes,
                onStatusChange = {
                    appState.selectedUpdateStatus = it
                },
                onCompletionNotesChange = {
                    appState.completionNotes = it
                },
                onBack = {
                    navController.popBackStack()
                },
                onSaveUpdate = {
                    appState.saveCaseUpdate()

                    navController.popBackStack(
                        CivisRoutes.StaffHome,
                        false
                    )
                }
            )
        }
    }
}