pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GazeGo"
include(":app")
include(":core:common")
include(":core:domain")
include(":core:network")
include(":core:data")
include(":core:database")
include(":core:datastore")
include(":core:model")
include(":features:details")
include(":features:home")
include(":features:discover")
include(":features:wishlist")
include(":core:ui")
include(":core:designsystem")
include(":core:navigation")
include(":features:movie-list")
include(":features:review-list")
