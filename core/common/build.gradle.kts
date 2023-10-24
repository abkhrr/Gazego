plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.hilt)
}

android.namespace = "com.abkhrr.gazego.core.common"
android.buildFeatures.buildConfig = true

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)

    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
}