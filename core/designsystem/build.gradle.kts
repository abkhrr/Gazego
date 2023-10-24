plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.library.compose)
}

android.namespace = "com.abkhrr.gazego.core.designsystem"

android.packaging {
    resources {
        excludes += "/META-INF/{AL2.0,LGPL2.1}"
        merges += "META-INF/LICENSE.md"
        merges += "META-INF/LICENSE-notice.md"
    }
}

dependencies {
    implementation(libs.androidx.compose.material)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    api(libs.bundles.androidx.compose)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    api(libs.coil.kt.compose)

    implementation(libs.androidx.palette)
    api(libs.androidx.activity.compose)
    api(libs.androidx.compose.ui.test)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    testImplementation(libs.junit.junit)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.io.mockk.android)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
}