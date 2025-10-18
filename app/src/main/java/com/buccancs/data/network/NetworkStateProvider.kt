package com.buccancs.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.buccancs.domain.model.NetworkSnapshot
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStateProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun snapshot(): NetworkSnapshot {
        val connectivityManager =
            context.getSystemService(
                ConnectivityManager::class.java
            )
        val activeNetwork =
            connectivityManager?.activeNetwork
        val capabilities =
            activeNetwork?.let {
                connectivityManager.getNetworkCapabilities(
                    it
                )
            }
        val connected =
            capabilities?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) == true
        val transport =
            when {
                capabilities == null -> "NONE"
                capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) -> "WIFI"

                capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                ) -> "CELLULAR"

                capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_ETHERNET
                ) -> "ETHERNET"

                capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_BLUETOOTH
                ) -> "BLUETOOTH"

                capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_USB
                ) -> "USB"

                capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_LOWPAN
                ) -> "LOWPAN"

                capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_VPN
                ) -> "VPN"

                else -> "UNKNOWN"
            }
        val metered =
            connectivityManager?.isActiveNetworkMetered
                ?: false
        return NetworkSnapshot(
            connected = connected,
            transport = transport,
            metered = metered
        )
    }
}
