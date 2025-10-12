@file:Suppress("DEPRECATION")
package com.buccancs.control

@kotlin.jvm.JvmName("-initializepreviewFrame")
public inline fun previewFrame(block: com.buccancs.control.PreviewFrameKt.Dsl.() -> kotlin.Unit): com.buccancs.control.PreviewFrame =
  com.buccancs.control.PreviewFrameKt.Dsl._create(com.buccancs.control.PreviewFrame.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `com.buccancs.control.PreviewFrame`
 */
public object PreviewFrameKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: com.buccancs.control.PreviewFrame.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.buccancs.control.PreviewFrame.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.buccancs.control.PreviewFrame = _builder.build()

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
     * `string camera_id = 2;`
     */
    public var cameraId: kotlin.String
      get() = _builder.getCameraId()
      set(value) {
        _builder.setCameraId(value)
      }
    /**
     * `string camera_id = 2;`
     */
    public fun clearCameraId() {
      _builder.clearCameraId()
    }

    /**
     * `int64 frame_timestamp_epoch_ms = 3;`
     */
    public var frameTimestampEpochMs: kotlin.Long
      get() = _builder.getFrameTimestampEpochMs()
      set(value) {
        _builder.setFrameTimestampEpochMs(value)
      }
    /**
     * `int64 frame_timestamp_epoch_ms = 3;`
     */
    public fun clearFrameTimestampEpochMs() {
      _builder.clearFrameTimestampEpochMs()
    }

    /**
     * `bytes encoded_frame = 4;`
     */
    public var encodedFrame: com.google.protobuf.ByteString
      get() = _builder.getEncodedFrame()
      set(value) {
        _builder.setEncodedFrame(value)
      }
    /**
     * `bytes encoded_frame = 4;`
     */
    public fun clearEncodedFrame() {
      _builder.clearEncodedFrame()
    }

    /**
     * `string mime_type = 5;`
     */
    public var mimeType: kotlin.String
      get() = _builder.getMimeType()
      set(value) {
        _builder.setMimeType(value)
      }
    /**
     * `string mime_type = 5;`
     */
    public fun clearMimeType() {
      _builder.clearMimeType()
    }

    /**
     * `uint32 width = 6;`
     */
    public var width: kotlin.Int
      get() = _builder.getWidth()
      set(value) {
        _builder.setWidth(value)
      }
    /**
     * `uint32 width = 6;`
     */
    public fun clearWidth() {
      _builder.clearWidth()
    }

    /**
     * `uint32 height = 7;`
     */
    public var height: kotlin.Int
      get() = _builder.getHeight()
      set(value) {
        _builder.setHeight(value)
      }
    /**
     * `uint32 height = 7;`
     */
    public fun clearHeight() {
      _builder.clearHeight()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun com.buccancs.control.PreviewFrame.copy(block: `com.buccancs.control`.PreviewFrameKt.Dsl.() -> kotlin.Unit): com.buccancs.control.PreviewFrame =
  `com.buccancs.control`.PreviewFrameKt.Dsl._create(this.toBuilder()).apply { block() }._build()

