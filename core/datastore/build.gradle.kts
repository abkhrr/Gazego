plugins {
    alias(libs.plugins.gazego.android.library)
    alias(libs.plugins.gazego.android.hilt)
}

android.namespace = "com.abkhrr.gazego.core.datastore"

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))

    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.coroutines.android)

    api(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit.junit)
    testImplementation(libs.io.mockk)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
}