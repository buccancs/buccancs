package com.buccancs.data.orchestration.discovery

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Base64
import androidx.core.content.ContextCompat
import com.buccancs.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.net.InetAddress
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MdnsBrowser @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope private val scope: CoroutineScope
) {
    private val nsdManager = context.getSystemService(Context.NSD_SERVICE) as? NsdManager
        ?: throw IllegalStateException("NSD service not available")
    private val handler = Handler(Looper.getMainLooper())
    private val resolveExecutor = ContextCompat.getMainExecutor(context)
    private val stateMutex = Mutex()
    private val _browsing = MutableStateFlow(false)
    val browsing: StateFlow<Boolean> = _browsing.asStateFlow()
    private val discovered = mutableMapOf<String, MdnsService>()
    private val _services = MutableStateFlow<List<MdnsService>>(emptyList())
    val services: StateFlow<List<MdnsService>> = _services.asStateFlow()

    fun start(serviceType: String) {
        scope.launch {
            stateMutex.withLock {
                if (_browsing.value) return@launch
                _browsing.value = true
            }
            handler.post {
                nsdManager.discoverServices(
                    serviceType,
                    NsdManager.PROTOCOL_DNS_SD,
                    discoveryListener
                )
            }
        }
    }

    fun stop() {
        scope.launch {
            stateMutex.withLock {
                if (!_browsing.value) return@launch
                _browsing.value = false
            }
            handler.post {
                runCatching { nsdManager.stopServiceDiscovery(discoveryListener) }
            }
            updateServices(emptyList())
        }
    }

    private val discoveryListener = object : NsdManager.DiscoveryListener {
        override fun onDiscoveryStarted(serviceType: String) {
            updateServices(emptyList())
        }

        override fun onServiceFound(serviceInfo: NsdServiceInfo) {
            resolveService(serviceInfo)
        }

        override fun onServiceLost(serviceInfo: NsdServiceInfo) {
            scope.launch {
                stateMutex.withLock {
                    discovered.remove(serviceInfo.serviceName)
                    updateServices(discovered.values.toList())
                }
            }
        }

        override fun onDiscoveryStopped(serviceType: String) {
            scope.launch {
                _browsing.value = false
                updateServices(emptyList())
            }
        }

        override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
            scope.launch {
                _browsing.value = false
                updateServices(emptyList())
            }
        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
            scope.launch {
                _browsing.value = false
            }
        }
    }

    private fun resolveService(info: NsdServiceInfo) {
        val listener = object : NsdManager.ResolveListener {
            override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            }

            override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
                scope.launch {
                    stateMutex.withLock {
                        val hostAddress = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            serviceInfo.hostAddresses.firstOrNull()
                        } else {
                            @Suppress("DEPRECATION")
                            serviceInfo.host
                        }
                        val entry = MdnsService(
                            name = serviceInfo.serviceName,
                            type = serviceInfo.serviceType,
                            host = hostAddress,
                            port = serviceInfo.port,
                            attributes = encodeAttributes(serviceInfo.attributes)
                        )
                        discovered[serviceInfo.serviceName] = entry
                        updateServices(discovered.values.toList())
                    }
                }
            }
        }
        handler.post {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                nsdManager.resolveService(info, resolveExecutor, listener)
            } else {
                @Suppress("DEPRECATION")
                nsdManager.resolveService(info, listener)
            }
        }
    }

    private fun updateServices(items: List<MdnsService>) {
        _services.value = items.sortedBy { it.name.lowercase() }
    }

    private fun encodeAttributes(raw: Map<String, ByteArray>): Map<String, String> =
        raw.mapValues { (_, value) -> Base64.encodeToString(value, Base64.NO_WRAP) }

    data class MdnsService(
        val name: String,
        val type: String,
        val host: InetAddress?,
        val port: Int,
        val attributes: Map<String, String>
    )
}
