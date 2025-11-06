plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.quiz"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.quiz"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    packagingOptions {
        resources {
            excludes += "**/README_ADD_IMAGES.txt"
        }
    }
}

// Tarea que borra automáticamente archivos problemáticos dentro de res/drawable
tasks.register<Delete>("removeBadDrawable") {
    delete(
        file("src/main/res/drawable/README_ADD_IMAGES.txt"),
        file("src/main/res/drawable/espana.xml")
    )
}

// Asegurar que la tarea se ejecute antes de compilar
tasks.named("preBuild") {
    dependsOn("removeBadDrawable")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}