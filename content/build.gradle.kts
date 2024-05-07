plugins {
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            webpackTask {
                mainOutputFileName.set("content.js")
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
