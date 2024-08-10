import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    alias(libs.plugins.android.application) apply true
    alias(libs.plugins.jetbrains.kotlin.android) apply true

}

android {
    namespace = "com.example.suitmediaandroidtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.suitmediaandroidtest"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.recyclerview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.picasso)
    implementation(libs.swiperefreshlayout)
    implementation(libs.databindingRuntime)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}