# CIVIS - Community Infrastructure, Visibility, and Integrated Services

CIVIS is an Android mobile application developed for **IT114 - Mobile Development Fundamentals**.

The application provides barangay residents with a centralized way to report and monitor non-emergency community and infrastructure issues. It also provides barangay staff with a structured workflow for reviewing, assigning, inspecting, updating, and resolving reported cases.

## Project Purpose

Community and infrastructure concerns can be difficult to monitor when reports are handled through disconnected or informal channels. CIVIS demonstrates a mobile issue-to-resolution workflow that keeps the reporting process organized and gives both residents and barangay staff clear access to case information and status.

The project focuses on the mobile application concepts covered in IT114, including requirements-based screen design, Jetpack Compose UI implementation, state-driven interaction, navigation, usability, and application stabilization.

## User Roles

CIVIS supports two user roles:

- **Resident**
- **Barangay Staff**

The application begins with role selection so that the Resident and Staff workflows can be demonstrated without implementing an authentication system.

## Resident Features

Residents can:

- Create a new community or infrastructure report
- Select an issue category
- Enter an issue description
- Enter a location
- Add the report's photo state
- Review report information before submission
- Confirm report submission
- Receive a unique CIV case ID
- View submitted reports through My Reports
- Track the current status of an individual case
- View the complete report details
- View inspection notes when applicable
- View completion notes when provided by staff
- Edit a report while it is still eligible for resident modification
- Delete an eligible report with confirmation

Resident editing and deletion are restricted after staff processing begins so that a case being handled by barangay personnel is not unexpectedly changed or removed.

## Resident Workflow

The primary reporting flow is:

**Resident Home → Report an Issue → Review Report → Submit Confirmation → Case Status**

Submitted reports can also be accessed through:

**Resident Home → My Reports → Track Report**

or:

**Resident Home → My Reports → View Report**

Residents can manage eligible reports through the View Report screen before staff processing begins.

## Staff Features

Barangay staff can:

- View dynamic case counts on the Staff Home dashboard
- View the latest reported case
- Open the complete Staff Cases list
- Search cases using a CIV case ID
- Review individual case details
- Set case priority
- Assign a staff member
- Update an existing assignment
- Record inspection information
- Select the staff member performing an inspection
- Record inspection findings and notes
- Set an inspection result
- Update a case after inspection
- Add update or completion notes
- Resolve a case
- Reopen a resolved case when additional work is required

## Staff Workflow

The primary Staff workflow is:

**Staff Home → Case Details → Case Assignment → Record Inspection → Case Update → Resolution**

Staff can also access all reports through:

**Staff Home → Cases → Case Details**

Case assignment and inspection actions provide confirmation states so that users receive clear feedback after completing important tasks.

## Case Management

CIVIS supports multiple independent cases during the application session.

Each submitted report receives its own sequential case ID:

- `CIV-0001`
- `CIV-0002`
- `CIV-0003`
- and so on

Case IDs are not reused after a case is deleted during the active application state.

Each case maintains its own report information, status, priority, assignment, inspection information, and completion notes.

## Case Statuses

The application uses case statuses to represent progress through the workflow, including:

- Submitted
- Assigned
- For Inspection
- Needs Repair
- In Progress
- Resolved

Inspection results affect the case status so that inspection activity is reflected in the Resident and Staff workflows.

The Staff dashboard groups cases into broader categories for quick monitoring:

- Pending
- Under Review
- In Progress
- Resolved

Dashboard counts are calculated from the current cases rather than displayed as fixed placeholder values.

## Inspection and Resolution

Staff can record:

- Inspection date
- Staff member
- Findings
- Inspection notes
- Inspection result

Inspection results update the case's status.

When additional work is required, staff can continue updating the case. When a case is resolved, completion notes can be recorded and made visible to the Resident through View Report.

Resolved cases can also be reopened as **In Progress** if additional work becomes necessary.

## Application State

CIVIS uses state concepts covered in IT114 to connect the interface to the application workflow.

The implementation uses:

- `remember`
- `rememberSaveable`
- `mutableStateOf`
- State hoisting
- Conditional rendering
- Event callbacks
- Recomposition

Application state is shared with the navigation layer so that individual screens can receive values and communicate user actions through callbacks.

Supported application values are preserved across configuration changes such as device rotation through `rememberSaveable`.

## Navigation

CIVIS uses Navigation Compose with:

- One `NavController`
- One `NavHost`
- Centralized route definitions
- `navigate()`
- `popBackStack()`
- Explicit user-driven navigation

Navigation routes are defined in `CivisRoutes` rather than repeated as route strings throughout the application.

Navigation actions are also checked against the current destination where needed to reduce duplicate navigation caused by repeated rapid taps.

## User Interface

The application is implemented using Kotlin and Jetpack Compose.

The interface uses Compose components and concepts covered in the course, including:

- `Column`
- `Row`
- `Box`
- `Text`
- `Button`
- `OutlinedButton`
- `OutlinedTextField`
- `DropdownMenu`
- `AlertDialog`
- `Modifier`
- Material 3 components
- Reusable composables
- Conditional UI rendering

Screens are designed around clear primary tasks, visible actions, readable information, and predictable forward and reverse navigation.

## Project Structure

The application separates major responsibilities into dedicated Kotlin files.

The project includes:

- Application entry point
- Shared application state
- Centralized navigation
- Resident screens
- Staff screens
- Reusable UI components
- Theme files

This structure keeps individual screens focused on presentation and user interaction while shared state and navigation coordinate the overall workflow.

## Development Stack

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Android Studio
- Gradle

## Testing and Stabilization

The application has been manually tested for the primary Resident and Staff workflows.

Testing has included:

- Report creation
- Required report input
- Report review and submission
- Multiple case creation
- Unique sequential case IDs
- Individual case selection
- Resident report tracking
- Resident report viewing
- Resident report editing
- Resident report deletion
- Delete confirmation
- Staff case listing
- Case ID search
- Case assignment
- Case reassignment
- Inspection recording
- Inspection-driven status changes
- Case updates
- Case resolution
- Completion notes
- Case reopening
- Dynamic dashboard counts
- Forward navigation
- Reverse navigation
- Rapid repeated taps
- Multiple-case isolation
- State preservation during device rotation
- Regression testing after refinements

The project has also been checked for leftover debug logging, TODO/FIXME markers, and hardcoded navigation route strings.

## Terminal Assessment Refinements

The application was refined in preparation for the IT114 Terminal Assessment.

Major refinements include:

- Separating the new-report draft from submitted case data
- Supporting multiple submitted cases instead of only one case
- Generating independent sequential CIV case IDs
- Preventing deleted case IDs from being reused
- Adding a dedicated Resident My Reports screen
- Supporting individual case selection and tracking
- Adding eligible Resident report editing and deletion
- Adding delete confirmation
- Adding a complete Staff Cases screen
- Adding CIV case ID search
- Replacing fixed Staff dashboard values with dynamic case counts
- Supporting staff assignment and reassignment
- Allowing inspection results to determine case status
- Connecting inspection notes to the Resident report view
- Adding case update and completion notes
- Supporting resolved-case reopening
- Adding protection against duplicate navigation from repeated taps
- Cleaning unused application-state members

These refinements strengthen the connection between the application's interface, state, and complete case workflow while remaining within the concepts used in IT114.

## Current Limitations

CIVIS is an academic mobile application project and intentionally maintains a controlled scope.

Current limitations include:

- Case data is stored in application memory rather than a permanent database
- Data is not designed to persist after the application process is terminated
- No backend or remote server is connected
- No user authentication or account system is implemented
- Staff members are selected from predefined application choices
- Photo handling is represented by application state rather than a complete production media-storage system
- The application does not implement a full historical timeline of every case modification
- No separate production work-order management system is implemented

These limitations keep the implementation focused on the requirements, UI, state, navigation, testing, and stabilization concepts covered by the course.

## Build and Run

Open the Android project in Android Studio and allow Gradle synchronization to complete.

The application can then be run using an Android emulator or compatible Android device.

A command-line debug build can be performed from the `android` directory with:

```bash
./gradlew clean assembleDebug
```

## Project Status

CIVIS has completed the functional implementation and stabilization work for the IT114 Terminal Assessment.

The current application demonstrates a complete Resident reporting workflow and Staff case-management workflow using Kotlin, Jetpack Compose, state-driven UI, and Navigation Compose.

The project builds successfully with Gradle and has undergone functional, navigation, state, multiple-case, and regression testing.
