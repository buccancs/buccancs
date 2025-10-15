package com.buccancs.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashRoute(
    onNavigateToClause: () -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(state.navigationTarget) {
        when (state.navigationTarget) {
            NavigationTarget.CLAUSE -> onNavigateToClause()
            NavigationTarget.MAIN -> onNavigateToMain()
            NavigationTarget.NONE -> {}
        }
    }
    
    SplashScreen(appName = state.appName)
}

@Composable
private fun SplashScreen(appName: String) {
    val alphaAnimation = remember { Animatable(0f) }
    
    LaunchedEffect(Unit) {
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800)
        )
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1a1a2e),
                        Color(0xFF16213e)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.alpha(alphaAnimation.value)
        ) {
            Text(
                text = "THERMAL",
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
            
            Text(
                text = "IMAGING",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(280.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "○",
                        fontSize = 48.sp,
                        color = Color.White
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Text(
                    text = appName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        }
    }
}
