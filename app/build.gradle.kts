plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp") // TODO("kapt에서 ksp로 바뀐 이유는 무엇인가요?")
}

android {
    namespace = "com.wooyj.datalayer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wooyj.datalayer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://dog.ceo/api/breeds/\"")
        }
        release {
            isMinifyEnabled = false // TODO("release 일때 보통 minifyEnabled true 처리를 하나요? firebase Crashlytics 적용할 때 이것때매 트래킹이 힘들긴 하거든요.")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            buildConfigField("String", "BASE_URL", "\"https://dog.ceo/api/breeds/\"")
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
        compose = true
        buildConfig = true // TODO("buildConfigField를 이제 기본으로 제공하지 않는 이유는 무엇인가요?")
// TODO("아래 warning이 계속 뜨는데 이유는 무엇인가요?")
// The option setting 'android.defaults.buildfeatures.buildconfig=true' is deprecated.
// The current default is 'false'.
// It will be removed in version 9.0 of the Android Gradle plugin.
// You can resolve this warning in Android Studio via `Refactor` > `Migrate BuildConfig to Gradle Build Files`
// Affected Modules: app
    }
    // 2.0.0 migration compose compiler > https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compiler.html
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.1"
//    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    lintChecks("com.slack.lint.compose:compose-lint-checks:1.3.1")

    // OkHttp3
    // - define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    // - define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Retrofit2
    val retrofitVersion = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion") // for moshi converter
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")

    // Room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin Symbol Processing (KSP)
    // - kapt에서 KSP로 마이그레이션 : https://developer.android.com/build/migrate-to-ksp
    ksp("androidx.room:room-compiler:$roomVersion")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")

    // DataStore
    // - Preferences DataStore (SharedPreferences like APIs)
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    // - Typed DataStore (Typed API surface, such as Proto)
    implementation("androidx.datastore:datastore:1.1.1")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")
    // TODO("Log를 쓰지않고 Timber를 쓰는 이유는 무엇인가요?")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    ksp("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
