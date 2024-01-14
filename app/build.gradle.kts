@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlin.symbol.processing)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "sco.carlukesoftware.hiltinjectionexample"
    compileSdk = ConfigValues.compileSdk

    defaultConfig {
        applicationId = "sco.carlukesoftware.hiltinjectionexample"
        minSdk = ConfigValues.minSdk
        //noinspection EditedTargetSdkVersion
        targetSdk = ConfigValues.targetSdk
        versionCode = ConfigValues.versionCode
        versionName = ConfigValues.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        buildFeatures {
            compose = true

            // Disable unused AGP features
            viewBinding = false
            dataBinding = false

            // buildConfig false
            buildConfig = false
            resValues = false
            shaders = false
        }

        compileOptions {
            sourceCompatibility = ConfigValues.javaVersion
            targetCompatibility = ConfigValues.javaVersion
        }

        kotlinOptions {
            jvmTarget = ConfigValues.jvmTarget
        }

        kotlin {
            jvmToolchain(ConfigValues.jdkVersion)
        }

        composeOptions {
            kotlinCompilerExtensionVersion = ConfigValues.kotlinComposeCompiler
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        sourceSets["main"].java {
            srcDirs(
                "src/main/kotlin",
                "src/common/kotlin",
                "src/debug/kotlin",
                "src/release/kotlin",
                "src/staging/kotlin",
                "src/preproduction/kotlin"
            )
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))

    implementation(libs.bundles.compose)
    implementation(libs.bundles.material.icons)

    implementation(libs.bundles.constraintLayout)

    implementation(libs.navigation.compose)

    implementation(libs.hilt.android)

    ksp(libs.hilt.compiler)
    ksp(libs.hilt.androidx.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    androidTestImplementation(libs.hilt.android.test)
}