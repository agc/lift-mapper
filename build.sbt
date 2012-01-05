name := "liftinaction"

scalaVersion := "2.9.1"

seq(webSettings :_*)

//scanDirectories := Nil
scanDirectories in Compile := Nil


resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

resolvers += "Ibiblio" at "http://mirrors.ibiblio.org/pub/mirrors/maven2"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies ++= {
  val liftVersion = "2.4-RC1" // Put the current/latest lift version here
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-widgets" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-squeryl-record" % liftVersion % "compile->default")
}

// Customize any further dependencies as desired
libraryDependencies ++= Seq(
  //"org.eclipse.jetty" % "jetty-webapp" % "7.3.0.v20110203" % "jetty", // For Jetty 7
  "org.mortbay.jetty" % "jetty" % "6.1.22" % "container,test", // For Jetty 6, add scope test to make jetty avl. for tests
  "org.scala-tools.testing" % "specs_2.9.0" % "1.6.8" % "test", // For specs.org tests
  "junit" % "junit" % "4.8" % "test->default", // For JUnit 4 testing
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "com.h2database" % "h2" % "1.2.138" % "test->default", // In-process database, useful for development systems
  "c3p0" % "c3p0" % "0.9.1.2" % "compile->default",
  "ch.qos.logback" % "logback-classic" % "0.9.26" % "compile->default", // Logging
  "mysql" %   "mysql-connector-java" % "5.1.14",
  "net.liftweb" %% "lift-textile" % "2.4-RC1"  % "compile->default")

libraryDependencies +=  "commons-io" % "commons-io" % "1.3.2"
