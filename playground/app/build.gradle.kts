plugins {
    java
    checkstyle
}

checkstyle {
    toolVersion = libs.versions.checkstyle.get()
    configFile = rootDir.resolve("rulebook_checks.xml")
}

dependencies {
    checkstyle(libs.checkstyle)
    checkstyle(libs.rulebook.checkstyle)
    implementation(libs.guava)
    implementation(libs.commons.math3)
    testImplementation(libs.truth)
}
