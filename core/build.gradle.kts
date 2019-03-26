plugins {
  maven
  scala
  `java-library`
}
val scalaCompilerPlugin: Configuration = configurations.create("scalaCompilerPlugin")

repositories {
  jcenter()
}
dependencies {
  scalaCompilerPlugin("org.scalamacros:paradise_2.12.8:2.1.1")
  implementation("org.scala-lang:scala-library:2.12.8")

  implementation("com.github.javaparser:javaparser-core:3.13.5")
}

tasks.withType<ScalaCompile> {
  scalaCompileOptions.additionalParameters = listOf(
          "-Xplugin:" + scalaCompilerPlugin.asPath,
          "-Ypartial-unification",
          "-language:higherKinds")
}
