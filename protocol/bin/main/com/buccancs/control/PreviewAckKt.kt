@file:Suppress("DEPRECATION")
package com.buccancs.control

@kotlin.jvm.JvmName("-initializepreviewAck")
public inline fun previewAck(block: com.buccancs.control.PreviewAckKt.Dsl.() -> kotlin.Unit): com.buccancs.control.PreviewAck =
  com.buccancs.control.PreviewAckKt.Dsl._create(com.buccancs.control.PreviewAck.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `com.buccancs.control.PreviewAck`
 */
public object PreviewAckKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: com.buccancs.control.PreviewAck.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.buccancs.control.PreviewAck.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.buccancs.control.PreviewAck = _builder.build()

    /**
     * `bool received = 1;`
     */
    public var received: kotlin.Boolean
      get() = _builder.getReceived()
      set(value) {
        _builder.setReceived(value)
      }
    /**
     * `bool received = 1;`
     */
    public fun clearReceived() {
      _builder.clearReceived()
    }

    /**
     * `string info = 2;`
     */
    public var info: kotlin.String
      get() = _builder.getInfo()
      set(value) {
        _builder.setInfo(value)
      }
    /**
     * `string info = 2;`
     */
    public fun clearInfo() {
      _builder.clearInfo()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun com.buccancs.control.PreviewAck.copy(block: `com.buccancs.control`.PreviewAckKt.Dsl.() -> kotlin.Unit): com.buccancs.control.PreviewAck =
  `com.buccancs.control`.PreviewAckKt.Dsl._create(this.toBuilder()).apply { block() }._build()

