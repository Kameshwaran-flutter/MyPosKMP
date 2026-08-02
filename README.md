MyPos — Kotlin Multiplatform POS

A point-of-sale sample application built with Kotlin Multiplatform and Compose Multiplatform, sharing UI and business logic across Android and Desktop (Windows) from a single codebase.

Built as a hands-on exploration of Kotlin Multiplatform: how far a shared Compose UI, shared ViewModels, and shared data models can be pushed across platforms without per-platform rewrites.

Features
Menu catalog — product categories with grouped menu items and prices, driven by a serialized JSON data source.
Cart — add items to an order and track the running selection.
Shared Compose UI — a single Compose Multiplatform interface (navigation drawer, ElevatedCard-based item layout, responsive Row/Column composition) rendered natively on Android and Desktop.
Cross-platform builds — one codebase producing an Android APK and a Windows MSI installer.
Architecture
composeApp/
└── src/
    ├── commonMain/     # Shared across all targets:
    │                   #   - Compose UI (screens, navigation, components)
    │                   #   - ViewModels (menu categories, cart)
    │                   #   - Serializable data models (parsed from JSON)
    ├── androidMain/    # Android-specific entry point
    └── desktopMain/    # Desktop (Windows) entry point

Design decisions:

Shared business logic in commonMain — ViewModels for the menu catalog and cart live once and are consumed by both platforms; no duplicated state logic per target.
JSON → typed models — the menu data is deserialized into Kotlin data classes with kotlinx.serialization, keeping the UI layer working against typed models rather than raw JSON.
Compose Multiplatform UI — the entire interface is written once in Compose and rendered natively on each target.
Tech stack

Kotlin Multiplatform · Compose Multiplatform · kotlinx.serialization · MVVM (shared ViewModels)

Running the app
Android (APK)
Build the APK from the composeApp Android target (or download it from Releases).
Copy the APK to an Android device.
Open the file, allow installation from this source when prompted, and install.
Desktop (Windows MSI)
Download the MSI installer.
Run it and follow the installation steps.
Launch MyPos from the Start menu once installed.
Screenshots

<img width="2424" height="1080" alt="MyposKMP" src="https://github.com/user-attachments/assets/a8120e6c-b02f-4144-ad30-0f4568a8b8d6" />

Notes

This is a learning project focused on Kotlin Multiplatform structure and Compose Multiplatform UI sharing. It complements my primary production experience in Flutter/Dart (BLE-connected IoT apps, 1.4M+ downloads) — here I wanted to work through the KMP equivalent of the shared-logic patterns I use daily.

Roadmap

 Cart quantity controls and order total
 
 Persistence layer (SQLDelight) for orders
 
 Unit tests for the cart and catalog ViewModels
 
 iOS target


