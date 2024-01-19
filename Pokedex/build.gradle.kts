plugins {
    id("androidx.navigation.safeargs") version ("2.7.6") apply false
    id("com.android.application") version ("8.2.1") apply false
    id("com.android.library") version ("8.2.1") apply false
    id("com.google.devtools.ksp") version ("1.9.22-1.0.16") apply false
    kotlin("android") version ("1.9.22") apply false
}
val appCompatVersion by extra { "1.6.1" }
val combineTupleVersion by extra { "2.0.0" }
val constraintLayoutVersion by extra { "2.1.4" }
val coreVersion by extra { "1.12.0" }
val daggerVersion by extra { "2.48.1" }
val glideToVectorYouVersion by extra { "2.0.0" }
val lifecycleVersion by extra { "2.7.0" }
val materialVersion by extra { "1.11.0" }
val navigationVersion by extra { "2.7.6" }
val retrofitVersion by extra { "2.9.0" }
val roomVersion by extra { "2.6.1" }
val swipeRefreshVersion by extra { "1.1.0" }