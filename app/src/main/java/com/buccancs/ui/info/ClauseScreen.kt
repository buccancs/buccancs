package com.buccancs.ui.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ClauseRoute(
    onNavigateToMain: () -> Unit,
    onNavigateToPolicy: (Int) -> Unit,
    onFinish: () -> Unit,
    viewModel: ClauseViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    
    ClauseScreen(
        appName = state.appName,
        version = state.version,
        yearRange = state.yearRange,
        onAgree = {
            viewModel.acceptClause()
            onNavigateToMain()
        },
        onDisagree = {
            viewModel.showDisagreeDialog()
        },
        onNavigateToPolicy = onNavigateToPolicy,
        onFinish = onFinish
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClauseScreen(
    appName: String,
    version: String,
    yearRange: String,
    onAgree: () -> Unit,
    onDisagree: () -> Unit,
    onNavigateToPolicy: (Int) -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Terms & Conditions") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Welcome to $appName",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Before using this application, please read and agree to:",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    TextButton(
                        onClick = { onNavigateToPolicy(1) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("User Agreement")
                    }
                    
                    TextButton(
                        onClick = { onNavigateToPolicy(2) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Privacy Policy")
                    }
                    
                    TextButton(
                        onClick = { onNavigateToPolicy(3) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Terms of Service")
                    }
                }
            }
            
            Text(
                text = "By agreeing, you confirm that you have read and understood the above policies.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onDisagree,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Disagree")
                }
                
                Button(
                    onClick = onAgree,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Agree & Continue")
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "$yearRange\nVersion $version",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
