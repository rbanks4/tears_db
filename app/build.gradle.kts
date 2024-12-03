//conversion to kts done via (https://dev.to/vtsen/how-to-convert-android-gradle-groovy-to-kts-3pce)
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.realm.kotlin")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.gaming.android.tearsdatabase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gaming.android.tearsdatabase"
        minSdk = 24
        targetSdk = 34
        versionCode = 4
        versionName = "0.05"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro")
        }
        debug {
            isPseudoLocalesEnabled = true
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    val composeUiVersion by extra("1.5.4")
    val fragmentVersion by extra("1.6.2")

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.databinding:databinding-ktx:8.2.1")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("io.realm.kotlin:library-base:1.8.0" )
    implementation ("io.realm.kotlin:library-sync:1.8.0")
    implementation ("androidx.fragment:fragment-ktx:$fragmentVersion")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("com.google.code.gson:gson:2.9.0")

    //jetpack compose
    implementation ("androidx.activity:activity-compose:1.8.2")
    implementation ("androidx.compose.ui:ui:$composeUiVersion")
    implementation ("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation ("androidx.navigation:navigation-compose:2.7.6")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    //material
    implementation ("androidx.compose.material3:material3:1.1.2")

    //moshi
    implementation("com.squareup.moshi:moshi:1.15.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    //dependency injection
    implementation ("com.google.dagger:hilt-android:2.50")
    ksp ("com.google.dagger:hilt-compiler:2.50")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //livedata
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.0-beta03")

    //compose testing
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$composeUiVersion")
    debugImplementation ("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:$composeUiVersion")

    //rx
    implementation ("io.reactivex.rxjava3:rxjava:2.5.0")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")

    testImplementation ("junit:junit:4.13.2")
    testImplementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
}