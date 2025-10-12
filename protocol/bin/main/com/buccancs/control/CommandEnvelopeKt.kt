@file:Suppress("DEPRECATION")
package com.buccancs.control

@kotlin.jvm.JvmName("-initializecommandEnvelope")
public inline fun commandEnvelope(block: com.buccancs.control.CommandEnvelopeKt.Dsl.() -> kotlin.Unit): com.buccancs.control.CommandEnvelope =
  com.buccancs.control.CommandEnvelopeKt.Dsl._create(com.buccancs.control.CommandEnvelope.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `com.buccancs.control.CommandEnvelope`
 */
public object CommandEnvelopeKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: com.buccancs.control.CommandEnvelope.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.buccancs.control.CommandEnvelope.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.buccancs.control.CommandEnvelope = _builder.build()

    /**
     * `string command_id = 1;`
     */
    public var commandId: kotlin.String
      get() = _builder.getCommandId()
      set(value) {
        _builder.setCommandId(value)
      }
    /**
     * `string command_id = 1;`
     */
    public fun clearCommandId() {
      _builder.clearCommandId()
    }

    /**
     * `string session_id = 2;`
     */
    public var sessionId: kotlin.String
      get() = _builder.getSessionId()
      set(value) {
        _builder.setSessionId(value)
      }
    /**
     * `string session_id = 2;`
     */
    public fun clearSessionId() {
      _builder.clearSessionId()
    }

    /**
     * `string device_id = 3;`
     */
    public var deviceId: kotlin.String
      get() = _builder.getDeviceId()
      set(value) {
        _builder.setDeviceId(value)
      }
    /**
     * `string device_id = 3;`
     */
    public fun clearDeviceId() {
      _builder.clearDeviceId()
    }

    /**
     * `int64 issued_epoch_ms = 4;`
     */
    public var issuedEpochMs: kotlin.Long
      get() = _builder.getIssuedEpochMs()
      set(value) {
        _builder.setIssuedEpochMs(value)
      }
    /**
     * `int64 issued_epoch_ms = 4;`
     */
    public fun clearIssuedEpochMs() {
      _builder.clearIssuedEpochMs()
    }

    /**
     * `int64 execute_epoch_ms = 5;`
     */
    public var executeEpochMs: kotlin.Long
      get() = _builder.getExecuteEpochMs()
      set(value) {
        _builder.setExecuteEpochMs(value)
      }
    /**
     * `int64 execute_epoch_ms = 5;`
     */
    public fun clearExecuteEpochMs() {
      _builder.clearExecuteEpochMs()
    }

    /**
     * `string command_json = 6;`
     */
    public var commandJson: kotlin.String
      get() = _builder.getCommandJson()
      set(value) {
        _builder.setCommandJson(value)
      }
    /**
     * `string command_json = 6;`
     */
    public fun clearCommandJson() {
      _builder.clearCommandJson()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun com.buccancs.control.CommandEnvelope.copy(block: `com.buccancs.control`.CommandEnvelopeKt.Dsl.() -> kotlin.Unit): com.buccancs.control.CommandEnvelope =
  `com.buccancs.control`.CommandEnvelopeKt.Dsl._create(this.toBuilder()).apply { block() }._build()

