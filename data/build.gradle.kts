plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.abdat.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //ktor
    implementation(libs.ktor.client.okhttp) // ktor-engine
    implementation(libs.ktor.client.core) // Ktor-Core
    implementation(libs.kotlinx.serialization.json) // KotlinX Serialization (Convert JSON response to Kotlin Objects)
    implementation(libs.ktor.serialization.kotlinx.json) // Ktor- To work with Serialization
    implementation(libs.ktor.client.logging) // Logging (Optional)
    implementation(libs.ktor.client.content.negotiation) // Serialization
    //implementation(libs.slf4j.simple)
    //koin
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
}