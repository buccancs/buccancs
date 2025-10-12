@file:Suppress("DEPRECATION")
package com.buccancs.control

@kotlin.jvm.JvmName("-initializesensorSample")
public inline fun sensorSample(block: com.buccancs.control.SensorSampleKt.Dsl.() -> kotlin.Unit): com.buccancs.control.SensorSample =
  com.buccancs.control.SensorSampleKt.Dsl._create(com.buccancs.control.SensorSample.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `com.buccancs.control.SensorSample`
 */
public object SensorSampleKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: com.buccancs.control.SensorSample.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.buccancs.control.SensorSample.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.buccancs.control.SensorSample = _builder.build()

    /**
     * `int64 timestamp_epoch_ms = 1;`
     */
    public var timestampEpochMs: kotlin.Long
      get() = _builder.getTimestampEpochMs()
      set(value) {
        _builder.setTimestampEpochMs(value)
      }
    /**
     * `int64 timestamp_epoch_ms = 1;`
     */
    public fun clearTimestampEpochMs() {
      _builder.clearTimestampEpochMs()
    }

    /**
     * An uninstantiable, behaviorless type to represent the field in
     * generics.
     */
    @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
    public class ValuesProxy private constructor() : com.google.protobuf.kotlin.DslProxy()
    /**
     * `repeated .com.buccancs.control.SensorSampleValue values = 2;`
     */
     public val values: com.google.protobuf.kotlin.DslList<com.buccancs.control.SensorSampleValue, ValuesProxy>
      @kotlin.jvm.JvmSynthetic
      get() = com.google.protobuf.kotlin.DslList(
        _builder.getValuesList()
      )
    /**
     * `repeated .com.buccancs.control.SensorSampleValue values = 2;`
     * @param value The values to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addValues")
    public fun com.google.protobuf.kotlin.DslList<com.buccancs.control.SensorSampleValue, ValuesProxy>.add(value: com.buccancs.control.SensorSampleValue) {
      _builder.addValues(value)
    }
    /**
     * `repeated .com.buccancs.control.SensorSampleValue values = 2;`
     * @param value The values to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignValues")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<com.buccancs.control.SensorSampleValue, ValuesProxy>.plusAssign(value: com.buccancs.control.SensorSampleValue) {
      add(value)
    }
    /**
     * `repeated .com.buccancs.control.SensorSampleValue values = 2;`
     * @param values The values to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addAllValues")
    public fun com.google.protobuf.kotlin.DslList<com.buccancs.control.SensorSampleValue, ValuesProxy>.addAll(values: kotlin.collections.Iterable<com.buccancs.control.SensorSampleValue>) {
      _builder.addAllValues(values)
    }
    /**
     * `repeated .com.buccancs.control.SensorSampleValue values = 2;`
     * @param values The values to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignAllValues")
    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun com.google.protobuf.kotlin.DslList<com.buccancs.control.SensorSampleValue, ValuesProxy>.plusAssign(values: kotlin.collections.Iterable<com.buccancs.control.SensorSampleValue>) {
      addAll(values)
    }
    /**
     * `repeated .com.buccancs.control.SensorSampleValue values = 2;`
     * @param index The index to set the value at.
     * @param value The values to set.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("setValues")
    public operator fun com.google.protobuf.kotlin.DslList<com.buccancs.control.SensorSampleValue, ValuesProxy>.set(index: kotlin.Int, value: com.buccancs.control.SensorSampleValue) {
      _builder.setValues(index, value)
    }
    /**
     * `repeated .com.buccancs.control.SensorSampleValue values = 2;`
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("clearValues")
    public fun com.google.protobuf.kotlin.DslList<com.buccancs.control.SensorSampleValue, ValuesProxy>.clear() {
      _builder.clearValues()
    }

  }
}
@kotlin.jvm.JvmSynthetic
public inline fun com.buccancs.control.SensorSample.copy(block: `com.buccancs.control`.SensorSampleKt.Dsl.() -> kotlin.Unit): com.buccancs.control.SensorSample =
  `com.buccancs.control`.SensorSampleKt.Dsl._create(this.toBuilder()).apply { block() }._build()

