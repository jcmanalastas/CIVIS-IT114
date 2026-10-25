# CIVIS - Community Infrastructure, Visibility, and Integrated Services

CIVIS is an Android mobile application project developed for IT114 - Mobile Development Fundamentals.

The application provides barangay residents with a centralized way to report and monitor non-emergency community and infrastructure issues, while allowing barangay personnel to review, assign, inspect, and resolve reported cases.

## Project Scope

CIVIS focuses on a controlled issue-to-resolution workflow for community and infrastructure concerns.

The application supports two user roles:

- Resident
- Barangay Staff

The Resident workflow allows users to report an issue, review the report before submission, receive a case ID and status, and view a submitted case.

The Staff workflow allows barangay personnel to review case information, set a priority, assign a case, record inspection information, and update the case status through resolution.

## Current Implementation

The project implements the approved CIVIS prototype using Kotlin and Jetpack Compose.

The current implementation includes:

- Jetpack Compose screen layouts
- Column, Row, and Box layout structures
- Modifier usage for layout and spacing
- Reusable composable components
- State-driven user interaction
- Form input and validation
- Conditional UI rendering
- Shared application state
- State preservation for supported values
- Navigation Compose
- Centralized route definitions
- Forward and reverse navigation
- Resident and Staff workflows
- Case status updates
- Confirmation and completion states

## Resident Flow

The primary Resident flow is:

Resident Home → Report an Issue → Review Report → Submit Report Confirmation → Submitted / Case Status

Residents can provide the required issue category, description, location, and photo state before continuing with a report.

## Staff Flow

The Staff workflow supports:

Staff Home → Case Details → Case Assignment → Inspection → Case Update → Resolution

Staff can review case information, set a priority, assign personnel, record inspection information, and update the case status.

## Development Stack

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Android Studio
- Gradle

## Project Status

The CIVIS project has completed the implementation and stabilization activities covered through Week 9 of IT114 - Mobile Development Fundamentals.

The application has been tested for its primary Resident and Staff workflows, navigation behavior, state consistency, required report input, reverse navigation, rapid user interaction, and alignment with the approved prototype.

The project builds successfully using Gradle.
