plugins {
    id("androidx.navigation.safeargs") version ("2.7.6") apply false
    id("com.android.application") version ("8.2.1") apply false
    id("com.android.library") version ("8.2.1") apply false
    id("com.google.devtools.ksp") version ("1.9.22-1.0.16") apply false
    id("org.jetbrains.kotlin.android") version ("1.9.22") apply false
}
val daggerVersion by extra { "2.48.1" }
val lifecycleVersion by extra { "2.7.0" }
val navigationVersion by extra { "2.7.6" }
val retrofitVersion by extra { "2.9.0" }
val roomVersion by extra { "2.6.1" }