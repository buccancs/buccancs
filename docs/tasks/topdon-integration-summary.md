# Topdon Integration Summary

**Implementation Date**: 2025-10-20  
**Status**: ✅ Phases 1 & 2 delivered

This note captures the current capabilities of the Topdon TC001 integration.
Refresh it whenever new phases land so the hardware story stays discoverable.

---

## Phase 1 – Live Thermal Preview

**Objective**  
Render real-time thermal frames from the TC001 inside `ThermalPreviewScreen`.

**Key Changes**  
- Added TC001 vendor/product IDs to `app/src/main/res/xml/device_filter.xml`.  
- Switched `DefaultTopdonThermalClient` to `UVCType.USB_TYPE_IR` so the SDK
  streams raw 16-bit thermal data.  
- Registered `CommonParams.DataFlowMode.USB_STREAM_DIRECT` to bypass H.264
  conversion and feed frames straight into the temperature pipeline.

**Result**  
The device enumerates reliably, permissions prompt automatically, and the preview
renders at ~12–15 FPS with correct temperature values.

---

## Phase 2 – Hardware Settings Control

**Objective**  
Expose palette, emissivity, gain mode, distance, and shutter controls that write
directly to the TC001 hardware.

**Key Changes**  
- Added IRCMD helpers in `DefaultTopdonThermalClient.applySettings`
  (`setPalette`, `setEmissivity`, `setDistance`, `setShutterMode`).  
- Extended the domain models (`TopdonPalette.ARCTIC`, `TopdonGainMode`,
  emissivity property) and repository APIs (`setEmissivity`, `setGainMode`).  
- Persisted settings with `DataStoreTopdonSettingsRepository`, mirroring behaviour
  in the in-memory implementation for simulation.  
- Synced UI state through `TopdonViewModel`, so reconnects reapply stored values.

**Result**  
Palette, gain mode, emissivity, and distance adjustments take effect on hardware
in real time and persist across app restarts. Simulation paths remain functional.

---

## Integration Architecture Snapshot

```
Compose UI (ThermalPreviewScreen, TopdonSettingsScreen)
          │
          ▼
TopdonViewModel (state + intents)
          │
          ▼
Repositories & Clients
  • DefaultTopdonThermalClient  ──► IRCMD bridge + USB stream direct
  • DataStoreTopdonSettingsRepository
  • InMemoryTopdonSettingsRepository (simulation)
```

---

## Next Focus Areas (Phase 3 Candidates)

1. HDR/temperature calibration and advanced noise filtering.  
2. Hardware-triggered capture (shutter button, burst capture).  
3. Synchronized RGB/IR recording pipeline with metadata alignment.  
4. Regression harness that replays captured frames for native regressions.  
5. Palette/temperature histogram overlay for in-field diagnostics.  
6. Refined settings UX (contextual tooltips, grouping, quick presets).
