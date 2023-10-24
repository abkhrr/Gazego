plugins {
    alias(libs.plugins.gazego.android.application)
    alias(libs.plugins.gazego.android.application.compose)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.gazego.android.hilt)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.abkhrr.gazego"

    defaultConfig {
        applicationId = "com.abkhrr.gazego"
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures.buildConfig = true
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))

    implementation(project(":features:home"))
    implementation(project(":features:details"))
    implementation(project(":features:movie-list"))
    implementation(project(":features:review-list"))
    implementation(project(":features:discover"))
    implementation(project(":features:wishlist"))

    implementation(libs.androidx.profileinstaller)
    implementation(libs.bundles.androidx.compose)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)

    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
    implementation(libs.bundles.retrofit)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.material)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.ext)
}