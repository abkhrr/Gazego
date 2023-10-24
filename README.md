# Gazego App
==================

# Development Environment

**Gazego** uses the Gradle build logic and can be imported directly into the canary version
of Android Studio (available [here](https://developer.android.com/studio/preview?gclid=CjwKCAjws9ipBhB1EiwAccEi1CO66EDXDODgfYL5miNB8NhXIlxsZup2Babjqjnpj1xdhA9JCrVrJhoCjjwQAvD_BwE&gclsrc=aw.ds)). The `debug`
build can be built and run using the default configuration.

Once you're up and running, you can refer to the learning journeys below to get a better
understanding of which libraries and tools are being used, the reasoning behind the approaches to
UI, testing, architecture and more, and how all of these different pieces of the project fit
together to create a complete app.

# Build

The app contains the usual `debug` and `release` build variants.

In addition, the `benchmark` variant of `app` is used to test startup performance and generate a
baseline profile (see below for more information).

For normal development use the `debug` variant. For UI performance testing use the `release`
variant.

# Architecture

The **Gazego** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture)
and is described in detail in the
[architecture learning journey](docs/ArchitectureLearningJourney.md).

# Modularization

The **Gazego** app has been fully modularized and you can find the detailed guidance and
description of the modularization strategy used in
[modularization learning journey](docs/ModularizationLearningJourney.md).

# UI

UI components are designed according to the custom design system and built entirely
using [Jetpack Compose](https://developer.android.com/jetpack/compose).

The app has one dark theme that uses predefined colors. The app uses adaptive layouts to
[support different screen sizes](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes).

Find out more about the [UI architecture here](docs/ArchitectureLearningJourney.md#ui-layer).

# Testing

Using hilt for DI, this benefits in testing. For example I already provide following test samples:

* I provide one testing sample `GetMovieDetailsUseCaseTest` [`core:domain` module](https://github.com/abkhrr/gazego/tree/main/core/domain)
* I provide one android-testing sample `SearchScreenTest` [`features:home` module](https://github.com/abkhrr/gazego/tree/main/features/home)

