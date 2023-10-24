plugins {
    alias(libs.plugins.gazego.android.feature)
    alias(libs.plugins.gazego.android.library.compose)
}

android.namespace = "com.abkhrr.gazego.features.discover"

android.packaging {
    resources {
        excludes += "/META-INF/{AL2.0,LGPL2.1}"
        merges += "META-INF/LICENSE.md"
        merges += "META-INF/LICENSE-notice.md"
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:navigation"))

    implementation(libs.androidx.palette)
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
}