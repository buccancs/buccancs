@file:Suppress("DEPRECATION")
package com.buccancs.control

@kotlin.jvm.JvmName("-initializedeviceStatus")
public inline fun deviceStatus(block: com.buccancs.control.DeviceStatusKt.Dsl.() -> kotlin.Unit): com.buccancs.control.DeviceStatus =
  com.buccancs.control.DeviceStatusKt.Dsl._create(com.buccancs.control.DeviceStatus.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `com.buccancs.control.DeviceStatus`
 */
public object DeviceStatusKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: com.buccancs.control.DeviceStatus.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.buccancs.control.DeviceStatus.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.buccancs.control.DeviceStatus = _builder.build()

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
     * `bool online = 2;`
     */
    public var online: kotlin.Boolean
      get() = _builder.getOnline()
      set(value) {
        _builder.setOnline(value)
      }
    /**
     * `bool online = 2;`
     */
    public fun clearOnline() {
      _builder.clearOnline()
    }

    /**
     * `bool recording = 3;`
     */
    public var recording: kotlin.Boolean
      get() = _builder.getRecording()
      set(value) {
        _builder.setRecording(value)
      }
    /**
     * `bool recording = 3;`
     */
    public fun clearRecording() {
      _builder.clearRecording()
    }

    /**
     * `double battery_percent = 4;`
     */
    public var batteryPercent: kotlin.Double
      get() = _builder.getBatteryPercent()
      set(value) {
        _builder.setBatteryPercent(value)
      }
    /**
     * `double battery_percent = 4;`
     */
    public fun clearBatteryPercent() {
      _builder.clearBatteryPercent()
    }

    /**
     * `int64 last_heartbeat_epoch_ms = 5;`
     */
    public var lastHeartbeatEpochMs: kotlin.Long
      get() = _builder.getLastHeartbeatEpochMs()
      set(value) {
        _builder.setLastHeartbeatEpochMs(value)
      }
    /**
     * `int64 last_heartbeat_epoch_ms = 5;`
     */
    public fun clearLastHeartbeatEpochMs() {
      _builder.clearLastHeartbeatEpochMs()
    }

    /**
     * `string session_id = 6;`
     */
    public var sessionId: kotlin.String
      get() = _builder.getSessionId()
      set(value) {
        _builder.setSessionId(value)
      }
    /**
     * `string session_id = 6;`
     */
    public fun clearSessionId() {
      _builder.clearSessionId()
    }

    /**
     * `double preview_latency_ms = 7;`
     */
    public var previewLatencyMs: kotlin.Double
      get() = _builder.getPreviewLatencyMs()
      set(value) {
        _builder.setPreviewLatencyMs(value)
      }
    /**
     * `double preview_latency_ms = 7;`
     */
    public fun clearPreviewLatencyMs() {
      _builder.clearPreviewLatencyMs()
    }

    /**
     * `double clock_offset_ms = 8;`
     */
    public var clockOffsetMs: kotlin.Double
      get() = _builder.getClockOffsetMs()
      set(value) {
        _builder.setClockOffsetMs(value)
      }
    /**
     * `double clock_offset_ms = 8;`
     */
    public fun clearClockOffsetMs() {
      _builder.clearClockOffsetMs()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun com.buccancs.control.DeviceStatus.copy(block: `com.buccancs.control`.DeviceStatusKt.Dsl.() -> kotlin.Unit): com.buccancs.control.DeviceStatus =
  `com.buccancs.control`.DeviceStatusKt.Dsl._create(this.toBuilder()).apply { block() }._build()

