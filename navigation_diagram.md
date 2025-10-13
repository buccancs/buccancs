```mermaid
graph TD
    subgraph "Main App"
        Launch[App Launch] --> MainActivity
        MainActivity[MainActivity /app/main]
        MainActivity -- Tab 0 --> IRGalleryTabFragment_All[IRGalleryTabFragment - All Media]
        MainActivity -- Tab 1 --> MainFragment[MainFragment - Device Hub]
        MainActivity -- Tab 2 --> MineFragment[MineFragment - User Center]
    end

    subgraph "Device Interaction"
        MainFragment -- Wired TC001 --> IRMainActivity
        MainFragment -- TS004 --> IRMonocularActivity
        MainFragment -- TC007 --> IRMainActivity_TC007[IRMainActivity with IS_TC007 flag]
        MainFragment -- Add Device --> DeviceTypeActivity
    end

    subgraph "User Center"
        MineFragment -- Settings --> MoreActivity
        MineFragment -- Manuals/FAQ --> ElectronicManualActivity
        MineFragment -- Feedback --> FeedbackActivity
    end

    subgraph "Thermal Imaging (IR)"
        IRMainActivity[IRMainActivity /ir/main]
        IRMainActivity -- Tab 0 --> AbilityFragment
        IRMainActivity -- Tab 1 --> IRGalleryTabFragment_Device[IRGalleryTabFragment - Device Media]
        IRMainActivity -- Tab 2 --> IRThermalFragment[IRThermalFragment - Connection Gate]
        IRMainActivity -- Tab 3 --> PDFListFragment
        IRMainActivity -- Tab 4 --> MoreFragment
        AbilityFragment --> MonitoryHomeActivity
        AbilityFragment --> HouseHomeActivity
        AbilityFragment --> CarDetectActivity
        IRThermalFragment -- TC001 Plus --> IRThermalPlusActivity
        IRThermalFragment -- TC001 Standard --> IRThermalNightActivity
        IRThermalFragment -- TC001 Lite --> IRThermalLiteActivity
    end

    subgraph "Media & Reports"
        IRGalleryTabFragment_Device --> IRGalleryFragment
        IRGalleryFragment -- View Image --> IRGalleryDetail01Activity
        IRGalleryFragment -- View Video --> IRVideoGSYActivity
        IRGalleryDetail01Activity -- Edit --> IRGalleryEditActivity
        PDFListFragment --> ReportPreviewActivity
    end

    subgraph "Settings & Support (IR)"
        MoreFragment --> QuestionActivity
        MoreFragment --> DeviceDetailsActivity
        IRThermalPlusActivity -- Menu --> IRConfigActivity
        IRThermalNightActivity -- Menu --> IRConfigActivity
    end
```

**Diagram Description:**

This diagram visualizes the navigation flow within the Topdon application.

1. **Main App:** The app launches into `MainActivity`, which has three main tabs: a gallery, a device hub, and a user
   center.
2. **Device Interaction:** From the `MainFragment` (device hub), the user can connect to different devices, which will
   navigate them to the corresponding activity (e.g., `IRMainActivity` for the TC001).
3. **Thermal Imaging (IR):** The `IRMainActivity` is the central hub for thermal imaging tasks. It's a tabbed activity
   that provides access to:
    * **AbilityFragment:** Shortcuts for specific tasks.
    * **IRGalleryTabFragment:** Media gallery for the connected device.
    * **IRThermalFragment:** The connection gate, which directs the user to the correct thermal camera activity based on
      the connected hardware (Plus, Standard, or Lite).
    * **PDFListFragment:** For viewing reports.
    * **MoreFragment:** For device utilities and help.
4. **Media & Reports:** This section shows the flow for viewing and editing images and videos, as well as for previewing
   reports.
5. **Settings & Support:** The user can access various settings and support screens from both the `MineFragment` and the
   `IRMainActivity`.
