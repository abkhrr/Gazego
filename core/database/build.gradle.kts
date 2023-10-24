plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.hilt)
    alias(libs.plugins.gazego.android.room)
    alias(libs.plugins.kotlinSerialization)
    id("kotlin-parcelize")
}

android.namespace = "com.abkhrr.gazego.core.database"

dependencies {
    api(project(":core:model"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.paging.common)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit.junit)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
}