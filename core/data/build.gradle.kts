plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.hilt)
}

android.namespace = "com.abkhrr.gazego.core.data"
android.testOptions.unitTests.isIncludeAndroidResources = true
android.testOptions.unitTests.isReturnDefaultValues = true

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.androidx.paging.common)

    testImplementation(libs.junit.junit)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
}