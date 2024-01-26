plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ivettevaldez.pokedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ivettevaldez.pokedex"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.androidx.layouts)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.bundles.androidx.navigation)
    implementation(libs.bundles.androidx.room)
    ksp(libs.androidx.room.compiler)
    implementation(libs.converter.gson)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.glidetovectoryou)
    implementation(libs.livedata.combinetuple)
    implementation(libs.material)
    implementation(libs.retrofit)

    testImplementation(libs.androidx.test.arch.core)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.bundles.mockito)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)

    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.test.junit)
}