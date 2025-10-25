# Topdon TC001 Feature Gaps

This document captures the gaps between the current BuccanCS Topdon TC001
integration and the ground-truth feature set delivered by Topdon's official
IRCamera application (`external/IRCamera`). Items are grouped by functional
area, with a focus on capabilities that are missing or only partially
implemented in BuccanCS.

## 1. Settings & Device Controls

| Capability | BuccanCS Status | IRCamera Baseline | Notes |
|------------|-----------------|-------------------|-------|
| Distance compensation | Not exposed in `TopdonSettings`; connector pushes `distanceMeters = null`. | Distance slider and persistence via `ConfigRepository` & reporting forms. | Align storage schema and UI so hardware receives calibrated distance values. |
| Emissivity tuning | Repository/API support exists, but UI lacks controls and view model setters. | Dedicated dialog with presets, tips, and saved defaults. | Expose emissivity controls and plumb through to `TopdonThermalConnector.applyHardwareSettings`. |
| Gain mode & HDR toggles | No Compose affordances; defaults remain AUTO. | High/low gain switches, auto gain, HDR toggles surfaced in quick settings. | Wire `TopdonSettingsRepository.setGainMode` and dynamic-range selectors into the UI. |
| Auto shutter control | Hardware client honors the flag, but there is no user-facing toggle. | Toggled in quick settings; defaults reset during profile changes. | Add toggle alongside preview controls. |
| Environmental metadata (ambient temp, humidity) | Not modelled in domain or UI. | Captured in report configuration with exported manifests. | Required for compliant measurement reports. |
| Manual calibration trigger (NUC) | View model exposes `triggerManualCalibration()` but no UI entry point. | Explicit calibration buttons with progress feedback. | Surface calibration controls and status so operators can force a shutter cycle. |
| Temperature unit selection | Settings screen shows static “Celsius (°C)” copy. | Unit toggles and persistence via `SaveSettingUtil`. | Extend settings repository and UI to support Fahrenheit/other targets. |

## 2. Imaging & UX Tooling

| Capability | BuccanCS Status | IRCamera Baseline | Notes |
|------------|-----------------|-------------------|-------|
| Auto hot/cold markers & alarms | Manual measurement targets only; no automatic extrema or alarm UI. | Automatic high/low markers, alarm thresholds, and notifications. | Implement measurement automation and alarms to match vendor ergonomics. |
| Compass, mirror, rotation, watermark controls | Absent from quick settings/control panel. | Toggleable via `SettingType` actions with saved defaults. | Extend quick settings to include these hardware presentation options. |
| Pseudo-color bar & palette diagnostics | Palette selection available; no palette bar or diagnostic overlays. | Palette bar toggle with real-time feedback. | Provide overlay toggles to assist operators. |
| Dual-light fusion / RGB overlay | Only thermal preview rendered. | Supports RGB/IR fusion modes, picture-in-picture, zoom widgets. | Required for dual-channel parity and planned Phase 3 work. |
| AI target tracking modes | Manual measurement targets only. | AI trace modes with selectable profiles. | Needed for automated target workflows. |
| Overlay styling (temperature text, annotations, crosshair themes) | Static styling; no customization UI. | Text color/size pickers, annotation styling preserved via settings. | Add controls for annotation presentation to match operator expectations. |

## 3. Capture & Recording Workflows

| Capability | BuccanCS Status | IRCamera Baseline | Notes |
|------------|-----------------|-------------------|-------|
| Photo format configurability | UI claims PNG with thermal data; implementation always writes JPEG + JSON. | Vendor exports radiometric data with consistent metadata options. | Align UI messaging and format support. |
| Video encoding | Saves raw frame dumps and JSON sidecars. | Hardware-accelerated MP4 encoder with overlay compositing and audio toggles. | Implement encoded video pipeline and session configuration (audio, watermarking). |
| Delayed shutter, burst, and audio capture | Not implemented. | Available via camera item controls. | Add capture-mode configuration hooks in the control panel. |
| Storage location management | Settings UI shows placeholders; functionality not implemented. | User-selectable storage directories. | Back settings with `RecordingStorage` and expose actual location selection. |

## 4. Reporting & Compliance

| Capability | BuccanCS Status | IRCamera Baseline | Notes |
|------------|-----------------|-------------------|-------|
| Measurement reports | No UI or data model for report generation. | Guided report wizard capturing environment, materials, annotations. | Needed for regulated use cases and parity with official tooling. |
| Session metadata (material presets, operator notes) | Not captured. | Stored via `SaveSettingUtil` and surfaced in reports. | Incorporate into domain models for downstream audit trails. |

## 5. Automation & Diagnostics

| Capability | BuccanCS Status | IRCamera Baseline | Notes |
|------------|-----------------|-------------------|-------|
| Palette diagnostics & stress tests | Planned in Phase 3 but not implemented. | Built-in diagnostics and testing modes. | Track as part of Phase 3 execution. |
| Regression harness replay | Mentioned as planned; no automation available. | Vendor app includes scripted replays and calibration routines. | Required to keep integration validated across releases. |

## 6. Next Steps

1. Update `TopdonSettings` and associated repositories to include distance,
   emissivity, gain mode, auto shutter, and environment metadata; surface
   matching UI controls.
2. Extend the Compose preview and settings surfaces with the hardware UX
   affordances present in IRCamera (auto markers, alarms, compass, mirror,
   palette diagnostics).
3. Align capture pipelines with vendor functionality (radiometric exports,
   encoded video, delayed shutter, storage selection), ensuring UI messaging
   reflects actual outputs.
4. Introduce reporting workflows and metadata storage to support compliance
   scenarios.
5. Plan and execute Phase 3 deliverables (HDR, RGB/IR sync, regression harness)
   to close the remaining gaps.
