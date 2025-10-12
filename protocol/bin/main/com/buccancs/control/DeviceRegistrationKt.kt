@file:Suppress("DEPRECATION")
package com.buccancs.control

@kotlin.jvm.JvmName("-initializedeviceRegistration")
public inline fun deviceRegistration(block: com.buccancs.control.DeviceRegistrationKt.Dsl.() -> kotlin.Unit): com.buccancs.control.DeviceRegistration =
  com.buccancs.control.DeviceRegistrationKt.Dsl._create(com.buccancs.control.DeviceRegistration.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `com.buccancs.control.DeviceRegistration`
 */
public object DeviceRegistrationKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: com.buccancs.control.DeviceRegistration.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.buccancs.control.DeviceRegistration.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.buccancs.control.DeviceRegistration = _builder.build()

    /**
     * `string device_id = 1;`
     */
    public var deviceId: kotlin.String
      get() = _builder.getDeviceId()
      set(value) {
        _builder.setDeviceId(value)
      }
    /**
     * `string device_id = 1;`
     */
    public fun clearDeviceId() {
      _builder.clearDeviceId()
    }

    /**
     * `string model = 2;`
     */
    public var model: kotlin.String
      get() = _builder.getModel()
      set(value) {
        _builder.setModel(value)
      }
    /**
     * `string model = 2;`
     */
    public fun clearModel() {
      _builder.clearModel()
    }

    /**
     * `string platform = 3;`
     */
    public var platform: kotlin.String
      get() = _builder.getPlatform()
      set(value) {
        _builder.setPlatform(value)
      }
    /**
     * `string platform = 3;`
     */
    public fun clearPlatform() {
      _builder.clearPlatform()
    }

    /**
     * `string software_version = 4;`
     */
    public var softwareVersion: kotlin.String
      get() = _builder.getSoftwareVersion()
      set(value) {
        _builder.setSoftwareVersion(value)
      }
    /**
     * `string software_version = 4;`
     */
    public fun clearSoftwareVersion() {
      _builder.clearSoftwareVersion()
    }

    /**
     * An uninstantiable, behaviorless type to represent the field in
     * generics.
     */
    @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
    public class CapabilitiesProxy private constructor() : com.google.protobuf.kotlin.DslProxy()
    /**
     * `repeated string capabilities = 5;`
     * @return A list containing the capabilities.
     */
    public val capabilities: com.google.protobuf.kotlin.DslList<kotlin.String, CapabilitiesProxy>
      @kotlin.jvm.JvmSynthetic
      get() = com.google.protobuf.kotlin.DslList(
        _builder.getCapabilitiesList()
      )
    /**
     * `repeated string capabilities = 5;`
     * @param value The capabilities to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addCapabilities")
    public fun com.google.protobuf.kotlin.DslList<kotlin.String, CapabilitiesProxy>.add(value: kotlin.String) {
      _builder.addCapabilities(value)
    }
    /**
     * `repeated string capabilities = 5;`
     * @param value The capabilities to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignCapabilities")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<kotlin.String, CapabilitiesProxy>.plusAssign(value: kotlin.String) {
      add(value)
    }
    /**
     * `repeated string capabilities = 5;`
     * @param values The capabilities to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addAllCapabilities")
    public fun com.google.protobuf.kotlin.DslList<kotlin.String, CapabilitiesProxy>.addAll(values: kotlin.collections.Iterable<kotlin.String>) {
      _builder.addAllCapabilities(values)
    }
    /**
     * `repeated string capabilities = 5;`
     * @param values The capabilities to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignAllCapabilities")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<kotlin.String, CapabilitiesProxy>.plusAssign(values: kotlin.collections.Iterable<kotlin.String>) {
      addAll(values)
    }
    /**
     * `repeated string capabilities = 5;`
     * @param index The index to set the value at.
     * @param value The capabilities to set.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("setCapabilities")
    public operator fun com.google.protobuf.kotlin.DslList<kotlin.String, CapabilitiesProxy>.set(index: kotlin.Int, value: kotlin.String) {
      _builder.setCapabilities(index, value)
    }/**
     * `repeated string capabilities = 5;`
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("clearCapabilities")
    public fun com.google.protobuf.kotlin.DslList<kotlin.String, CapabilitiesProxy>.clear() {
      _builder.clearCapabilities()
    }}
}
@kotlin.jvm.JvmSynthetic
public inline fun com.buccancs.control.DeviceRegistration.copy(block: `com.buccancs.control`.DeviceRegistrationKt.Dsl.() -> kotlin.Unit): com.buccancs.control.DeviceRegistration =
  `com.buccancs.control`.DeviceRegistrationKt.Dsl._create(this.toBuilder()).apply { block() }._build()

