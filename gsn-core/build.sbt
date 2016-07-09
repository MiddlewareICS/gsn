import NativePackagerHelper._

import com.typesafe.sbt.packager.archetypes.ServerLoader

import com.typesafe.sbt.packager.archetypes.TemplateWriter

import com.typesafe.sbt.packager.linux._

name := "gsn-core"

Revolver.settings

libraryDependencies ++= Seq(
  //"com.typesafe" % "config" % "1.2.1",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.2",
  "com.h2database" % "h2" % "1.4.181",
  "mysql" % "mysql-connector-java" % "5.1.29",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
  "commons-dbcp" % "commons-dbcp" % "1.4",
  "org.hibernate" % "hibernate-core" % "3.6.10.Final",
  "org.apache.httpcomponents" % "httpclient" % "4.3.2",
  "org.apache.commons" % "commons-email" % "1.3.2",
  "commons-collections" % "commons-collections" % "3.2.1",
  "commons-io" % "commons-io" % "2.4",
  "org.apache.logging.log4j" % "log4j-api" % "2.3",
  "org.apache.logging.log4j" % "log4j-core" % "2.3",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.3",
  "org.apache.logging.log4j" % "log4j-web" % "2.3",
  "net.sf.opencsv" % "opencsv" % "2.3",
  "com.thoughtworks.xstream" % "xstream" % "1.4.5",
  "org.antlr" % "stringtemplate" % "3.0",
  "commons-lang" % "commons-lang" % "2.6",
  "rome" % "rome" % "1.0",
  "org.jfree" % "jfreechart" % "1.0.19", 
  "org.jfree" % "jcommon" % "1.0.23",
  "org.codehaus.groovy" % "groovy-all" % "2.2.2",
  "net.rforge" % "REngine" % "0.6-8.1",
  "net.rforge" % "Rserve" % "0.6-8.1",
  "org.rxtx" % "rxtx" % "2.1.7",
  "com.esotericsoftware.kryo" % "kryo" % "2.23.0",
  "org.zeromq" % "jeromq" % "0.3.5",
  "junit" % "junit" % "4.11" %  "test",
  "org.easymock" % "easymockclassextension" % "3.2" % "test",
  "org.httpunit" % "httpunit" % "1.7.2" % "test" exclude("xerces","xercesImpl") exclude("xerces","xmlParserAPIs") exclude("javax.servlet","servlet-api")
)


mainClass := Some("gsn.Main")

NativePackagerKeys.packageSummary in com.typesafe.sbt.SbtNativePackager.Linux := "GSN Server"

NativePackagerKeys.packageSummary in com.typesafe.sbt.SbtNativePackager.Windows := "GSN Server"

NativePackagerKeys.packageDescription := "Global Sensor Networks Core"

NativePackagerKeys.maintainer in com.typesafe.sbt.SbtNativePackager.Windows := "LSIR EPFL <gsn@epfl.ch>"

NativePackagerKeys.maintainer in com.typesafe.sbt.SbtNativePackager.Linux := "LSIR EPFL <gsn@epfl.ch>"

debianPackageDependencies in Debian += "java7-runtime"

debianPackageRecommends in Debian ++= Seq("postgresql", "munin-node")

serverLoading in Debian := ServerLoader.Systemd

mappings in Universal <+= (packageBin in Compile, sourceDirectory ) map { (_, src) =>
    (src / "templates" / "gsn-core") -> "bin/gsn-core"
}

mappings in Universal <+= (packageBin in Compile, sourceDirectory ) map { (_, src) =>
    (src / "main" / "resources" / "log4j2.xml") -> "conf/log4j2.xml"
}

linuxPackageMappings in Debian <+= (name in Universal, sourceDirectory) map { (name, src) =>
  (packageMapping(
    (src / "main" / "resources" / "wrappers.properties") -> "/usr/share/gsn-core/conf/wrappers.properties"
  ) withUser "gsn-core" withGroup "root" withPerms "0664")
}

linuxPackageMappings in Debian <+= (name in Universal, baseDirectory) map { (name, base) =>
  (packageMapping(
    (base / ".." / "conf" / "gsn.xml") -> "/usr/share/gsn-core/conf/gsn.xml"
  ) withUser "gsn-core" withGroup "root" withPerms "0664")
}

mappings in Universal <+= (packageBin in Compile, baseDirectory ) map { (_, base) =>
    val conf = base / ".." / "virtual-sensors" / "memoryDataVS.xml"
    conf -> "conf/gsn.d/memoryDataVirtualSensor.xml"
}

mappings in Universal <++= (packageBin in Compile, baseDirectory ) map { (_, base) =>
    val dir = base / ".." / "virtual-sensors" / "samples"
    (dir.***) pair {file => Some("virtual-sensors/" + file.name)}
}

scalacOptions += "-deprecation"

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

mainClass in Revolver.reStart := Some("gsn.Main")

Revolver.reStartArgs := Seq("22232", "../conf", "../virtual-sensors")
