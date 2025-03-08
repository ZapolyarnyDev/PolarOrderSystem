plugins {
    java
    war
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "io.github.zapolyarnydev"
version = "1.0"

repositories{
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
