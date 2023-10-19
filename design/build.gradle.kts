plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.library")

}

dependencies {
    implementation(project(":common"))
    implementation(project(":core"))
    implementation(project(":service"))

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    implementation(libs.kotlin.coroutine)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.coordinator)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.viewpager)
    implementation(libs.google.material)
    implementation(libs.kotlin.serialization.json)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

}
