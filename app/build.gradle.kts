plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.bjw.ComAssistant"
    compileSdk = libs.versions.compileSdk.get().toInt()
    ndkVersion = "28.0.12433566"
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        externalNativeBuild {
            cmake {
                arguments.add("-DANDROID_PLATFORM=android-24")
                arguments.add("-DANDROID_TOOLCHAIN=clang")
            }
        }
        ndk {
            abiFilters += listOf("x86", "x86_64", "armeabi", "armeabi-v7a", "arm64-v8a")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    externalNativeBuild {
        cmake {
            path=file("src/main/jni/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {
    implementation(libs.appcompat)
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.kotlinx.coroutines.core)
//    implementation(libs.kotlinx.coroutines.android)
}
