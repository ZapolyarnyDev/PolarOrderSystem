plugins {
    java
    war
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
}
group = "io.github.zapolyarnydev"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

extra["springCloudVersion"] = "2024.0.0"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.jar {
    archiveBaseName.set("discovery-server")
    archiveVersion.set("1.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}