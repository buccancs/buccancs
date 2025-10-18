package com.buccancs.desktop.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.buccancs.desktop.ui.components.BuccancsCard
import com.buccancs.desktop.ui.components.PrimaryButton
import com.buccancs.desktop.ui.components.ScreenHeader
import com.buccancs.desktop.ui.components.SecondaryButton
import com.buccancs.desktop.ui.components.StatusCard
import com.buccancs.desktop.ui.components.StatusType
import com.buccancs.desktop.ui.components.TertiaryButton
import com.buccancs.desktop.ui.theme.BuccancsTheme
import com.buccancs.desktop.ui.theme.Spacing

/**
 * User management screen for operators and subjects
 */
@Composable
fun UsersScreen() {
    var showAddDialog by remember {
        mutableStateOf(
            false
        )
    }
    var selectedTab by remember {
        mutableStateOf(
            0
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                Spacing.Large
            ),
        verticalArrangement = Arrangement.spacedBy(
            Spacing.Medium
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScreenHeader(
                title = "User Management",
                subtitle = "Manage operators, subjects, and their participation in sessions",
                modifier = Modifier.weight(
                    1f
                )
            )

            PrimaryButton(
                text = "Add User",
                onClick = {
                    showAddDialog =
                        true
                }
            )
        }

        // Tabs
        PrimaryTabRow(
            selectedTabIndex = selectedTab
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = {
                    selectedTab =
                        0
                },
                text = {
                    Text(
                        "Operators"
                    )
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = {
                    selectedTab =
                        1
                },
                text = {
                    Text(
                        "Subjects"
                    )
                }
            )
        }

        when (selectedTab) {
            0 -> OperatorsContent()
            1 -> SubjectsContent()
        }
    }

    if (showAddDialog) {
        AddUserDialog(
            onDismiss = {
                showAddDialog =
                    false
            },
            onConfirm = {
                showAddDialog =
                    false
            }
        )
    }
}

@Composable
private fun OperatorsContent() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            Spacing.Medium
        )
    ) {
        items(
            getSampleOperators()
        ) { operator ->
            OperatorCard(
                operator
            )
        }
    }
}

@Composable
private fun SubjectsContent() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            Spacing.Medium
        )
    ) {
        items(
            getSampleSubjects()
        ) { subject ->
            SubjectCard(
                subject
            )
        }
    }
}

@Composable
private fun OperatorCard(
    operator: Operator
) {
    BuccancsCard(
        title = operator.name,
        subtitle = operator.id
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "Email",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        operator.email,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "Role",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        operator.role,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "Sessions Conducted",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        operator.sessionsCount.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "Last Active",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        operator.lastActive,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            HorizontalDivider()

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Small
                )
            ) {
                SecondaryButton(
                    text = "Edit",
                    onClick = { /* Edit */ }
                )
                SecondaryButton(
                    text = "View Sessions",
                    onClick = { /* View */ }
                )
                if (!operator.active) {
                    SecondaryButton(
                        text = "Activate",
                        onClick = { /* Activate */ }
                    )
                }
            }
        }
    }
}

@Composable
private fun SubjectCard(
    subject: Subject
) {
    BuccancsCard(
        title = subject.id,
        subtitle = if (subject.consentGiven) "Consent: ✓ Given" else "Consent: ⚠ Pending"
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Spacing.Small
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "Age Group",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        subject.ageGroup,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "Gender",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        subject.gender,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "Sessions Participated",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        subject.sessionsCount.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "Last Session",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        subject.lastSession,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (!subject.consentGiven) {
                HorizontalDivider()
                StatusCard(
                    title = "Consent Required",
                    status = StatusType.Warning
                ) {
                    Text(
                        "Informed consent form must be completed before participation.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            HorizontalDivider()

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    Spacing.Small
                )
            ) {
                SecondaryButton(
                    text = "Edit",
                    onClick = { /* Edit */ }
                )
                SecondaryButton(
                    text = "View Sessions",
                    onClick = { /* View */ }
                )
                if (subject.canErase) {
                    SecondaryButton(
                        text = "Erase Data",
                        onClick = { /* Erase */ }
                    )
                }
            }
        }
    }
}

@Composable
private fun AddUserDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var userType by remember {
        mutableStateOf(
            "Operator"
        )
    }
    var userId by remember {
        mutableStateOf(
            ""
        )
    }
    var userName by remember {
        mutableStateOf(
            ""
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Add User"
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    Spacing.Medium
                )
            ) {
                var expanded by remember {
                    mutableStateOf(
                        false
                    )
                }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded =
                            it
                    }
                ) {
                    OutlinedTextField(
                        value = userType,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text(
                                "User Type"
                            )
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(
                                ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                                enabled = true
                            )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded =
                                false
                        }
                    ) {
                        listOf(
                            "Operator",
                            "Subject"
                        ).forEach { type ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        type
                                    )
                                },
                                onClick = {
                                    userType =
                                        type
                                    expanded =
                                        false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = userId,
                    onValueChange = {
                        userId =
                            it
                    },
                    label = {
                        Text(
                            "User ID"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                if (userType == "Operator") {
                    OutlinedTextField(
                        value = userName,
                        onValueChange = {
                            userName =
                                it
                        },
                        label = {
                            Text(
                                "Full Name"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }
        },
        confirmButton = {
            PrimaryButton(
                text = "Add",
                onClick = onConfirm
            )
        },
        dismissButton = {
            TertiaryButton(
                text = "Cancel",
                onClick = onDismiss
            )
        }
    )
}

private fun getSampleOperators(): List<Operator> {
    return listOf(
        Operator(
            "OP-001",
            "Dr. Jane Smith",
            "jane.smith@buccancs.org",
            "Principal Investigator",
            45,
            "16 Oct 2025",
            true
        ),
        Operator(
            "OP-002",
            "Alex Johnson",
            "alex.j@buccancs.org",
            "Research Assistant",
            28,
            "15 Oct 2025",
            true
        ),
        Operator(
            "OP-003",
            "Dr. Michael Chen",
            "m.chen@buccancs.org",
            "Co-Investigator",
            12,
            "10 Oct 2025",
            true
        )
    )
}

private fun getSampleSubjects(): List<Subject> {
    return listOf(
        Subject(
            "SUBJ-A001",
            "25-30",
            "Female",
            true,
            8,
            "16 Oct 2025",
            true
        ),
        Subject(
            "SUBJ-A002",
            "18-24",
            "Male",
            true,
            12,
            "15 Oct 2025",
            true
        ),
        Subject(
            "SUBJ-A003",
            "31-40",
            "Female",
            false,
            0,
            "—",
            false
        ),
        Subject(
            "SUBJ-A004",
            "25-30",
            "Non-binary",
            true,
            5,
            "14 Oct 2025",
            true
        )
    )
}

private data class Operator(
    val id: String,
    val name: String,
    val email: String,
    val role: String,
    val sessionsCount: Int,
    val lastActive: String,
    val active: Boolean
)

private data class Subject(
    val id: String,
    val ageGroup: String,
    val gender: String,
    val consentGiven: Boolean,
    val sessionsCount: Int,
    val lastSession: String,
    val canErase: Boolean
)


@Preview
@Composable
private fun UsersScreenPreview() {
    BuccancsTheme {
        Surface {
            UsersScreen()
        }
    }
}
