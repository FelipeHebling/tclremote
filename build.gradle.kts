plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }
android { namespace = "com.example.tclremote"; compileSdk = 35
    defaultConfig { applicationId = "com.example.tclremote"; minSdk = 26; targetSdk = 35; versionCode = 1; versionName = "0.1-alpha" }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.15" }
}
dependencies {
 implementation("androidx.core:core-ktx:1.15.0")
 implementation("androidx.activity:activity-compose:1.10.0")
 implementation(platform("androidx.compose:compose-bom:2024.12.01"))
 implementation("androidx.compose.ui:ui")
 implementation("androidx.compose.ui:ui-tooling-preview")
 implementation("androidx.compose.material3:material3")
 implementation("androidx.compose.material:material-icons-extended")
 implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
}
