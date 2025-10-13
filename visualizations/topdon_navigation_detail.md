```mermaid
graph TD
    subgraph "Entry"
        AppLaunch[App Launch]
    end

    subgraph "Main Hub"
        MainActivity["/app/main<br>MainActivity"]
        MainFragment["MainFragment<br>(Device Hub)"]
        IRGallery_All["IRGalleryTabFragment<br>(All Media)"]
        MineFragment["MineFragment<br>(User Center)"]
    end

    subgraph "Device Hub Actions"
        DeviceTypeActivity["DeviceTypeActivity<br>(Onboarding)"]
        IRMainActivity["/ir/main<br>IRMainActivity"]
    end

    subgraph "User Center Actions"
        MoreActivity["MoreActivity"]
        ElectronicManualActivity["ElectronicManualActivity"]
        FeedbackActivity["FeedbackActivity"]
    end

    subgraph "Thermal Hub (IRMainActivity)"
        AbilityFragment["AbilityFragment<br>(Shortcuts)"]
        IRGallery_Device["IRGalleryTabFragment<br>(Device Media)"]
        IRThermalFragment["IRThermalFragment<br>(Connection Gate)"]
        PDFListFragment["PDFListFragment<br>(Reports)"]
        MoreFragment["/user/tcMore<br>MoreFragment"]
    end

    subgraph "Thermal Capture"
        IRThermalPlusActivity["/ir/frame/plush<br>IRThermalPlusActivity"]
        IRThermalNightActivity["/ir/frame<br>IRThermalNightActivity"]
        IRThermalLiteActivity["/lite/tcLite<br>IRThermalLiteActivity"]
    end

    subgraph "Media Viewing"
        IRGalleryFragment["IRGalleryFragment"]
        IRGalleryDetail01Activity["/ir/gallery/detail01<br>IRGalleryDetail01Activity"]
        IRVideoGSYActivity["/ir/video/gsy<br>IRVideoGSYActivity"]
    end

    AppLaunch --> MainActivity
    MainActivity --> MainFragment
    MainActivity --> IRGallery_All
    MainActivity --> MineFragment
    MainFragment -- Add Device --> DeviceTypeActivity
    MainFragment -- Select TC001 --> IRMainActivity
    MineFragment --> MoreActivity
    MineFragment --> ElectronicManualActivity
    MineFragment --> FeedbackActivity
    IRMainActivity --> AbilityFragment
    IRMainActivity --> IRGallery_Device
    IRMainActivity --> IRThermalFragment
    IRMainActivity --> PDFListFragment
    IRMainActivity --> MoreFragment
    IRThermalFragment -- TC001 Plus --> IRThermalPlusActivity
    IRThermalFragment -- TC001 Standard --> IRThermalNightActivity
    IRThermalFragment -- TC001 Lite --> IRThermalLiteActivity
    IRGallery_Device --> IRGalleryFragment
    IRGalleryFragment -- Select Image --> IRGalleryDetail01Activity
    IRGalleryFragment -- Select Video --> IRVideoGSYActivity
    click MainActivity "#" "Go to MainActivity"
    click IRMainActivity "#" "Go to IRMainActivity"
```

### Topdon Detailed Navigation Graph

This graph provides a more detailed view of the navigation flow within the Topdon application, including the ARouter
paths for key activities.

- The application starts in `MainActivity`, which serves as the main hub.
- The `MainFragment` within `MainActivity` acts as a device hub, allowing the user to connect to a TC001 device, which
  takes them to the `IRMainActivity`.
- The `IRMainActivity` is the central point for all thermal imaging related tasks. Its `IRThermalFragment` acts as a
  gate to the specific capture activity (`Plus`, `Night`, or `Lite`) based on the connected hardware.
- The diagram also shows the flow for accessing media in the gallery and the various screens available from the user
  center.
