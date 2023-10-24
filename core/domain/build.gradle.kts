plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.hilt)
    alias(libs.plugins.kotlinKapt)
    id("kotlin-parcelize")
}

android.namespace = "com.abkhrr.gazego.core.domain"

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.hilt.android)
    implementation(libs.retrofit.core)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.paging.common)
    api(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit.junit)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
}