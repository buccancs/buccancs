package com.buccancs.data.orchestration.discovery

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.os.Handler
import android.os.Looper
import android.util.Base64
import com.buccancs.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.InetAddress
import java.util.concurrent.atomic.AtomicBoolean
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
    private val browsing = AtomicBoolean(false)
    private val discovered = mutableMapOf<String, MdnsService>()
    private val _services = MutableStateFlow<List<MdnsService>>(emptyList())
    val services: StateFlow<List<MdnsService>> = _services.asStateFlow()

    fun start(serviceType: String) {
        if (!browsing.compareAndSet(false, true)) return
        handler.post {
            nsdManager.discoverServices(
                serviceType,
                NsdManager.PROTOCOL_DNS_SD,
                discoveryListener
            )
        }
    }

    fun stop() {
        if (!browsing.compareAndSet(true, false)) return
        handler.post {
            runCatching { nsdManager.stopServiceDiscovery(discoveryListener) }
        }
        updateServices(emptyList())
    }

    private val discoveryListener = object : NsdManager.DiscoveryListener {
        override fun onDiscoveryStarted(serviceType: String) {
            updateServices(emptyList())
        }

        override fun onServiceFound(serviceInfo: NsdServiceInfo) {
            resolveService(serviceInfo)
        }

        override fun onServiceLost(serviceInfo: NsdServiceInfo) {
            discovered.remove(serviceInfo.serviceName)
            updateServices(discovered.values.toList())
        }

        override fun onDiscoveryStopped(serviceType: String) {
            browsing.set(false)
            updateServices(emptyList())
        }

        override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
            browsing.set(false)
            updateServices(emptyList())
        }

        override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
            browsing.set(false)
        }
    }

    private fun resolveService(info: NsdServiceInfo) {
        handler.post {
            nsdManager.resolveService(
                info,
                object : NsdManager.ResolveListener {
                    override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                    }

                    override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
                        val entry = MdnsService(
                            name = serviceInfo.serviceName,
                            type = serviceInfo.serviceType,
                            host = serviceInfo.host,
                            port = serviceInfo.port,
                            attributes = encodeAttributes(serviceInfo.attributes)
                        )
                        discovered[serviceInfo.serviceName] = entry
                        updateServices(discovered.values.toList())
                    }
                }
            )
        }
    }

    private fun updateServices(items: List<MdnsService>) {
        scope.launch {
            _services.value = items.sortedBy { it.name.lowercase() }
        }
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
