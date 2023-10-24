plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.hilt)
    alias(libs.plugins.kotlinSerialization)
}

android.namespace = "com.abkhrr.gazego.core.network"
android.buildFeatures.buildConfig = true

dependencies {
    implementation(project(":core:common"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.retrofit)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    implementation(libs.androidx.paging.common)
    api(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit.junit)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
}