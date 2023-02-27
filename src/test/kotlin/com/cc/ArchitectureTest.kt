package com.cc

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures

@AnalyzeClasses(packages = ["com.cc"])
class ArchitectureTest {

    companion object {
        private const val DOMAIN = "domain"
        private const val USERCASE = "usercase"
        private const val INFRASTRUCTURE = "infrastructure"
    }

    @ArchTest
    val layerRules: ArchRule? = Architectures.layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(DOMAIN).definedBy("com.cc.domain..")
            .layer(USERCASE).definedBy("com.cc.usercase..")
            .layer(INFRASTRUCTURE).definedBy("com.cc.infrastructure..")
            .whereLayer(DOMAIN).mayNotAccessAnyLayer()
}

