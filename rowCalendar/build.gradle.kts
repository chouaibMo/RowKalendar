import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.mavenPublish)
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach {
        it.binaries.framework {
            baseName = "RowCalendar"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.lifecycle.viewmodel.compose)
                implementation(libs.kotlinx.datetime)

            }
        }
    }
}

android {
    namespace = "io.github.chouaibmo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}


mavenPublishing {
    coordinates(
        artifactId = "row-calendar",
        groupId = "io.github.chouaibmo",
        version = "0.0.5"
    )

    pom{
        name.set("RowCalendar")
        description.set("A Compose Multiplatform library to display horizontal calendar")
        url.set("https://github.com/chouaibMo/RowCalendar")
        inceptionYear.set("2024")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("chouaibMo")
                name.set("Chouaib")
                url.set("https://github.com/chouaibMo")
            }
        }

        scm {
            connection.set("https://github.com/chouaibMo/RowCalendar.git")
            developerConnection.set("https://github.com/chouaibMo/RowCalendar.git")
            url.set("https://github.com/chouaibMo/RowCalendar.git")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

}