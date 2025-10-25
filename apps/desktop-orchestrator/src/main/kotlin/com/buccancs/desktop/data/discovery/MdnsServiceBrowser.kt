package com.buccancs.desktop.data.discovery

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.slf4j.LoggerFactory
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceListener

data class DiscoveredDevice(
    val serviceName: String,
    val serviceType: String,
    val hostName: String,
    val addresses: List<InetAddress>,
    val port: Int,
    val attributes: Map<String, String>
)

class MdnsServiceBrowser(
    private val scope: CoroutineScope
) {
    private val logger =
        LoggerFactory.getLogger(
            MdnsServiceBrowser::class.java
        )
    private val mutex =
        Mutex()
    private var jmdns: JmDNS? =
        null
    private val _browsing =
        MutableStateFlow(
            false
        )
    val browsing: StateFlow<Boolean> =
        _browsing.asStateFlow()

    private val discovered =
        mutableMapOf<String, DiscoveredDevice>()
    private val _devices =
        MutableStateFlow<List<DiscoveredDevice>>(
            emptyList()
        )
    val devices: StateFlow<List<DiscoveredDevice>> =
        _devices.asStateFlow()

    fun start(
        serviceType: String = "_buccancs._tcp.local."
    ) {
        scope.launch(
            Dispatchers.IO
        ) {
            mutex.withLock {
                try {
                    if (_browsing.value) {
                        logger.warn(
                            "mDNS browser already running"
                        )
                        return@launch
                    }

                    logger.info(
                        "Starting mDNS service discovery for $serviceType"
                    )
                    jmdns =
                        JmDNS.create(
                            InetAddress.getLocalHost()
                        )
                            .also { dns ->
                                dns.addServiceListener(
                                    serviceType,
                                    serviceListener
                                )
                                _browsing.value =
                                    true
                                logger.info(
                                    "mDNS browser started successfully"
                                )
                            }
                } catch (e: Exception) {
                    logger.error(
                        "Failed to start mDNS browser",
                        e
                    )
                    _browsing.value =
                        false
                }
            }
        }
    }

    fun stop() {
        scope.launch(
            Dispatchers.IO
        ) {
            mutex.withLock {
                try {
                    logger.info(
                        "Stopping mDNS service discovery"
                    )
                    jmdns?.close()
                    jmdns =
                        null
                    _browsing.value =
                        false
                    discovered.clear()
                    _devices.value =
                        emptyList()
                    logger.info(
                        "mDNS browser stopped"
                    )
                } catch (e: Exception) {
                    logger.error(
                        "Error stopping mDNS browser",
                        e
                    )
                    // Ensure state is reset even on error
                    _browsing.value =
                        false
                    discovered.clear()
                    _devices.value =
                        emptyList()
                }
            }
        }
    }

    private val serviceListener =
        object :
            ServiceListener {
            override fun serviceAdded(
                event: ServiceEvent
            ) {
                logger.debug(
                    "Service discovered: ${event.name}"
                )
                jmdns?.requestServiceInfo(
                    event.type,
                    event.name
                )
            }

            override fun serviceResolved(
                event: ServiceEvent
            ) {
                val info =
                    event.info
                logger.info(
                    "Service resolved: ${info.name} at ${info.hostAddresses.joinToString()}"
                )

                val attrs =
                    mutableMapOf<String, String>()
                val propNames =
                    info.propertyNames
                while (propNames.hasMoreElements()) {
                    val key =
                        propNames.nextElement()
                    val value =
                        info.getPropertyString(
                            key
                        )
                    if (value != null) {
                        attrs[key] =
                            value
                    }
                }

                val device =
                    DiscoveredDevice(
                        serviceName = info.name,
                        serviceType = info.type,
                        hostName = info.server
                            ?: "",
                        addresses = info.inet4Addresses.toList(),
                        port = info.port,
                        attributes = attrs
                    )

                scope.launch {
                    discovered[info.name] =
                        device
                    _devices.value =
                        discovered.values.toList()
                }
            }

            override fun serviceRemoved(
                event: ServiceEvent
            ) {
                logger.info(
                    "Service lost: ${event.name}"
                )
                scope.launch {
                    discovered.remove(
                        event.name
                    )
                    _devices.value =
                        discovered.values.toList()
                }
            }
        }
}
