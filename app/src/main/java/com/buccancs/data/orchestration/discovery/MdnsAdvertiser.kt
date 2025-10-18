package com.buccancs.data.orchestration.discovery

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.os.Handler
import android.os.Looper
import com.buccancs.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MdnsAdvertiser @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope private val scope: CoroutineScope
) {
    private val nsdManager =
        context.getSystemService(
            Context.NSD_SERVICE
        ) as? NsdManager
            ?: throw IllegalStateException(
                "NSD service not available"
            )
    private val handler =
        Handler(
            Looper.getMainLooper()
        )
    private val stateMutex =
        Mutex()
    private val _state =
        MutableStateFlow(
            State(
                isActive = false,
                inProgress = false,
                errorMessage = null
            )
        )
    val state: StateFlow<State> =
        _state.asStateFlow()

    fun start(
        serviceName: String,
        serviceType: String,
        port: Int,
        attributes: Map<String, String> = emptyMap()
    ) {
        scope.launch {
            stateMutex.withLock {
                if (_state.value.isActive) return@launch
                _state.update {
                    it.copy(
                        isActive = true,
                        inProgress = true,
                        errorMessage = null
                    )
                }
            }
            val info =
                NsdServiceInfo().apply {
                    this.serviceName =
                        serviceName
                    this.serviceType =
                        serviceType
                    this.port =
                        port
                    attributes.forEach { (key, value) ->
                        setAttribute(
                            key,
                            value
                        )
                    }
                }
            handler.post {
                nsdManager.registerService(
                    info,
                    NsdManager.PROTOCOL_DNS_SD,
                    registrationListener
                )
            }
        }
    }

    fun stop() {
        scope.launch {
            stateMutex.withLock {
                if (!_state.value.isActive) return@launch
                _state.update {
                    it.copy(
                        isActive = false
                    )
                }
            }
            handler.post {
                runCatching {
                    nsdManager.unregisterService(
                        registrationListener
                    )
                }
                    .onFailure { error ->
                        updateState(
                            false,
                            false,
                            error.message
                        )
                    }
            }
        }
    }

    private val registrationListener =
        object :
            NsdManager.RegistrationListener {
            override fun onServiceRegistered(
                serviceInfo: NsdServiceInfo
            ) {
                updateState(
                    isActive = true,
                    inProgress = true,
                    error = null
                )
            }

            override fun onRegistrationFailed(
                serviceInfo: NsdServiceInfo,
                errorCode: Int
            ) {
                updateState(
                    isActive = false,
                    inProgress = false,
                    error = "Registration failed: $errorCode"
                )
            }

            override fun onServiceUnregistered(
                serviceInfo: NsdServiceInfo
            ) {
                updateState(
                    isActive = false,
                    inProgress = false,
                    error = null
                )
            }

            override fun onUnregistrationFailed(
                serviceInfo: NsdServiceInfo,
                errorCode: Int
            ) {
                updateState(
                    isActive = false,
                    inProgress = false,
                    error = "Unregister failed: $errorCode"
                )
            }
        }

    private fun updateState(
        isActive: Boolean,
        inProgress: Boolean,
        error: String?
    ) {
        scope.launch {
            _state.update {
                it.copy(
                    isActive = isActive,
                    inProgress = inProgress,
                    errorMessage = error
                )
            }
        }
    }

    data class State(
        val isActive: Boolean,
        val inProgress: Boolean,
        val errorMessage: String?
    )
}

