plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.library.compose)
}

android.namespace = "com.abkhrr.gazego.core.ui"

android.packaging {
    resources {
        excludes += "/META-INF/{AL2.0,LGPL2.1}"
        merges += "META-INF/LICENSE.md"
        merges += "META-INF/LICENSE-notice.md"
    }
}

dependencies {
    api(project(":core:common"))
    api(project(":core:model"))
    api(project(":core:designsystem"))
    //api(project(":core:navigation"))

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.livedata)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.metrics)
    api(libs.androidx.tracing.ktx)

    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)

    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.android.youtube.player)

    debugApi(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.datetime)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)

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