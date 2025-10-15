package com.buccancs.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PolicyUiState(
    val title: String = "",
    val content: String = "",
    val isLoading: Boolean = true
)

@HiltViewModel
class PolicyViewModel @Inject constructor() : ViewModel() {
    
    private val _uiState = MutableStateFlow(PolicyUiState())
    val uiState: StateFlow<PolicyUiState> = _uiState.asStateFlow()
    
    fun loadPolicy(themeType: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            delay(500)
            
            val (title, content) = when (themeType) {
                1 -> "User Agreement" to generateUserAgreement()
                2 -> "Privacy Policy" to generatePrivacyPolicy()
                3 -> "Terms of Service" to generateTermsOfService()
                else -> "Policy" to "Policy content not available."
            }
            
            _uiState.value = PolicyUiState(
                title = title,
                content = content,
                isLoading = false
            )
        }
    }
    
    private fun generateUserAgreement(): String = """
        USER AGREEMENT
        
        Last Updated: 2025
        
        This User Agreement governs your use of the thermal imaging application and services.
        
        1. ACCEPTANCE OF TERMS
        By accessing or using this application, you agree to be bound by these terms.
        
        2. USER RESPONSIBILITIES
        - Use the application in accordance with applicable laws
        - Maintain the security of your account credentials
        - Report any security vulnerabilities
        
        3. DATA COLLECTION
        We collect thermal imaging data and device information for service improvement.
        
        4. PROHIBITED CONDUCT
        - Reverse engineering the application
        - Interfering with service operation
        - Violating privacy of others
        
        5. TERMINATION
        We reserve the right to terminate access for violations of these terms.
        
        6. LIMITATION OF LIABILITY
        The service is provided "as is" without warranties of any kind.
        
        For questions, contact support.
    """.trimIndent()
    
    private fun generatePrivacyPolicy(): String = """
        PRIVACY POLICY
        
        Last Updated: 2025
        
        This Privacy Policy describes how we collect, use, and protect your information.
        
        1. INFORMATION WE COLLECT
        - Thermal imaging data
        - Device information
        - Usage statistics
        - Location data (when permitted)
        
        2. HOW WE USE INFORMATION
        - Provide and improve services
        - Analyse usage patterns
        - Ensure application security
        - Communicate with users
        
        3. DATA SHARING
        We do not sell personal data. We may share data with:
        - Service providers
        - Legal authorities (when required)
        
        4. DATA SECURITY
        We implement industry-standard security measures to protect your data.
        
        5. YOUR RIGHTS
        - Access your data
        - Request data deletion
        - Opt out of data collection
        
        6. CHILDREN'S PRIVACY
        This service is not intended for users under 13 years of age.
        
        7. CHANGES TO POLICY
        We may update this policy and will notify users of significant changes.
        
        Contact us for privacy concerns.
    """.trimIndent()
    
    private fun generateTermsOfService(): String = """
        TERMS OF SERVICE
        
        Last Updated: 2025
        
        These Terms of Service govern your use of our thermal imaging services.
        
        1. SERVICE DESCRIPTION
        We provide thermal imaging capture, analysis, and management services.
        
        2. ACCOUNT REGISTRATION
        - Provide accurate information
        - Maintain account security
        - Do not share credentials
        
        3. ACCEPTABLE USE
        - Use for lawful purposes only
        - Respect intellectual property rights
        - Do not transmit harmful content
        
        4. INTELLECTUAL PROPERTY
        All application code, designs, and trademarks are our property.
        
        5. SERVICE AVAILABILITY
        We strive for high availability but do not guarantee uninterrupted service.
        
        6. MODIFICATIONS
        We may modify features and pricing with notice to users.
        
        7. TERMINATION
        Either party may terminate the service relationship.
        
        8. DISPUTE RESOLUTION
        Disputes will be resolved through arbitration.
        
        9. GOVERNING LAW
        These terms are governed by applicable local laws.
        
        Contact support for assistance.
    """.trimIndent()
}
