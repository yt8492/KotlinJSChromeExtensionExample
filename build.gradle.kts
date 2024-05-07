plugins {
    kotlin("multiplatform") version "1.9.23"
}

group = "org.yt8492"
version = "1.0.0"

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            webpackTask {
                mainOutputFileName.set("popup.js")
            }
        }
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":libs"))
                implementation(kotlin("stdlib-js"))
            }
        }
    }
}

tasks {
    getByPath(":jsBrowserWebpack").apply {
        dependsOn(":content:jsBrowserWebpack")
        doLast {
            copy {
                val sourcePaths = project.subprojects.map {
                    val buildDir = it.layout.buildDirectory.asFile.get()
                    "$buildDir/dist/js/productionExecutable"
                }
                from(*sourcePaths.toTypedArray())
                val buildDir = project.layout.buildDirectory.asFile.get()
                val destPath = "$buildDir/dist/js/productionExecutable"
                into(destPath)
            }
        }
    }
}
