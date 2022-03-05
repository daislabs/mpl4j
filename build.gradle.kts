import com.diffplug.gradle.spotless.SpotlessExtension

plugins { id("com.diffplug.spotless") version "6.3.0" }

repositories { mavenCentral() }

spotless { kotlinGradle { ktfmt() } }

subprojects {
  apply(plugin = "java")
  apply(plugin = "maven-publish")
  apply(plugin = "com.diffplug.spotless")
  configure<PublishingExtension> {
    repositories {
      maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/daislabs/mpl-java")
        credentials {
          username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
          password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
      }
    }
    publications { create<MavenPublication>("gpr") { from(components["java"]) } }
  }
  configure<SpotlessExtension> {
    java { googleJavaFormat() }
    kotlinGradle { ktfmt() }
  }
}
