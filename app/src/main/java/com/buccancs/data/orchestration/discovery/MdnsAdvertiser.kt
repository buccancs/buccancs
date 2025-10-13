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
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MdnsAdvertiser @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope private val scope: CoroutineScope
) {
    private val nsdManager = context.getSystemService(Context.NSD_SERVICE) as? NsdManager
        ?: throw IllegalStateException("NSD service not available")
    private val handler = Handler(Looper.getMainLooper())
    private val active = AtomicBoolean(false)
    private val _state = MutableStateFlow(State(inProgress = false, errorMessage = null))
    val state: StateFlow<State> = _state.asStateFlow()

    fun start(serviceName: String, serviceType: String, port: Int, attributes: Map<String, String> = emptyMap()) {
        if (!active.compareAndSet(false, true)) return
        _state.value = State(inProgress = true, errorMessage = null)
        val info = NsdServiceInfo().apply {
            this.serviceName = serviceName
            this.serviceType = serviceType
            this.port = port
            attributes.forEach { (key, value) -> setAttribute(key, value) }
        }
        handler.post {
            nsdManager.registerService(info, NsdManager.PROTOCOL_DNS_SD, registrationListener)
        }
    }

    fun stop() {
        if (!active.compareAndSet(true, false)) return
        handler.post {
            runCatching { nsdManager.unregisterService(registrationListener) }
                .onFailure { error -> updateState(false, error.message) }
        }
    }

    private val registrationListener = object : NsdManager.RegistrationListener {
        override fun onServiceRegistered(serviceInfo: NsdServiceInfo) {
            updateState(true, null)
        }

        override fun onRegistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            updateState(false, "Registration failed: $errorCode")
            active.set(false)
        }

        override fun onServiceUnregistered(serviceInfo: NsdServiceInfo) {
            updateState(false, null)
            active.set(false)
        }

        override fun onUnregistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            updateState(false, "Unregister failed: $errorCode")
            active.set(false)
        }
    }

    private fun updateState(active: Boolean, error: String?) {
        scope.launch {
            _state.value = State(inProgress = active, errorMessage = error)
        }
    }

    data class State(
        val inProgress: Boolean,
        val errorMessage: String?
    )
}

