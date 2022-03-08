plugins { id("com.diffplug.spotless") version "6.3.0" }

repositories { mavenCentral() }

spotless { kotlinGradle { ktfmt() } }
