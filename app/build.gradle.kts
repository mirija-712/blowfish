plugins {
    id("application")
    id("org.sonarqube") version "5.0.0.4638"
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass.set("org.example.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

sonar {
    properties {
        property("sonar.projectKey", "mirija-712_blowfish")
        property("sonar.organization", "mirija-712")
        property("sonar.host.url", "https://sonarcloud.io")

        // IMPORTANT : définir clairement les dossiers
        property("sonar.sources", "src/main/java")
        property("sonar.tests", "src/test/java")

        // exclure les tests des sources (évite le double index)
        property("sonar.exclusions", "**/src/test/java/**")

        // classes compilées
        property("sonar.java.binaries", "build/classes/java/main")

        // couverture Jacoco
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "build/reports/jacoco/test/jacocoTestReport.xml"
        )
    }
}