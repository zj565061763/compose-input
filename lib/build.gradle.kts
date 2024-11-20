plugins {
   alias(libs.plugins.android.library)
   alias(libs.plugins.kotlin.android)
   `maven-publish`
}

val libGroupId = "com.sd.lib.android"
val libArtifactId = "compose-input"
val libVersion = "1.3.0-alpha06"

android {
   namespace = "com.sd.lib.compose.input"
   compileSdk = libs.versions.androidCompileSdk.get().toInt()
   defaultConfig {
      minSdk = 21
   }

   compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
   }

   kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs += "-module-name=$libGroupId.$libArtifactId"
   }

   buildFeatures {
      compose = true
   }

   composeOptions {
      kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
   }

   publishing {
      singleVariant("release") {
         withSourcesJar()
      }
   }
}

dependencies {
   implementation(libs.androidx.compose.foundation)
   implementation(libs.androidx.compose.material3)
   implementation(libs.androidx.constraintlayout.compose)
}

publishing {
   publications {
      create<MavenPublication>("release") {
         groupId = libGroupId
         artifactId = libArtifactId
         version = libVersion
         afterEvaluate {
            from(components["release"])
         }
      }
   }
}