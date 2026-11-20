# TCL Remote (Android prototype)

Initial Android project for a personalized remote control for TCL Google TV.

## Current state
- Native Compose UI: remote pad, app shortcuts tab, touch tab, settings.
- **Not yet connected to a real television**: protocol discovery, pairing, protobuf/TLS command transport and gesture mapping remain to be implemented.
- A GitHub Actions workflow builds a debug APK from the project.

## Build
Requires Android SDK and Gradle 8.9 / Java 17:
`gradle assembleDebug`

APK output: `app/build/outputs/apk/debug/app-debug.apk`

## GitHub Actions build
Push this project to a GitHub repository and run Actions; the workflow uploads a debug APK artifact.
