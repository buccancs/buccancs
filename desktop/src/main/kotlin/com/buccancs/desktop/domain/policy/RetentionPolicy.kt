package com.buccancs.desktop.domain.policy

data class RetentionPolicy(
    val perSessionCapBytes: Long,
    val perDeviceCapBytes: Long,
    val globalCapBytes: Long
)
