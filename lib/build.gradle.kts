plugins {
  `java-library`
  `maven-publish`
  id("com.diffplug.spotless") version "6.3.0"
}

repositories { mavenCentral() }

dependencies {
  // Use JUnit Jupiter for testing.
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

publishing {
  repositories {
    maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/daislabs/mpl4j")
      credentials {
        username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
        password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
      }
    }
  }
  publications { create<MavenPublication>("lib") { from(components["java"]) } }
}

tasks.named<Test>("test") {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}

spotless {
  java { googleJavaFormat() }
  kotlinGradle { ktfmt() }
}
