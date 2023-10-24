plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.hilt)
}

android.namespace = "org.sos.pro.app.core.model"

dependencies {
    implementation(project(":core:common"))
    implementation(libs.kotlinx.datetime)

    api(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit.junit)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
}