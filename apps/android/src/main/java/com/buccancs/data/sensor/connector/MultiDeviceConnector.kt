package com.buccancs.data.sensor.connector

import com.buccancs.domain.connector.MultiDeviceConnector as DomainMultiDeviceConnector

/**
 * Historic code referenced the data-layer package for the multi-device connector contract. During the
 * build fixes we removed the old declaration, which broke modules that still import it. This extender keeps
 * the legacy import path alive while delegating to the domain definition.
 */
interface MultiDeviceConnector : DomainMultiDeviceConnector
