allprojects {
    group = "com.example"
    version = "1.0"
}

subprojects {
    plugins.withType<JavaPlugin> {
        the<JavaPluginExtension>().toolchain.languageVersion
            .set(JavaLanguageVersion.of(libs.versions.jdk.get()))
    }
}
