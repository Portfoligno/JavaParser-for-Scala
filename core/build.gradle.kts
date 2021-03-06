plugins {
  maven
  scala
  `java-library`
}
val scalaCompilerPlugin: Configuration = configurations.create("scalaCompilerPlugin")

repositories {
  jcenter()
  maven("https://jitpack.io")
}
dependencies {
  scalaCompilerPlugin("org.scalamacros:paradise_2.12.8:2.1.1")
  api("org.scala-lang:scala-library:2.12.8")
  compileOnly("org.scala-lang:scala-reflect:2.12.8")

  api("com.github.javaparser:javaparser-core:3.13.6")
  api("io.github.portfoligno:non-empty-java-collections-for-scala:1.0.6")
}

tasks.withType<ScalaCompile> {
  scalaCompileOptions.additionalParameters = listOf(
          "-Xplugin:" + scalaCompilerPlugin.asPath,
          "-Xfatal-warnings",
          "-Ypartial-unification",
          "-language:higherKinds",
          "-language:implicitConversions")
}
