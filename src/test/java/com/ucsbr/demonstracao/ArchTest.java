package com.ucsbr.demonstracao;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ucsbr.demonstracao");

        noClasses()
            .that()
            .resideInAnyPackage("com.ucsbr.demonstracao.service..")
            .or()
            .resideInAnyPackage("com.ucsbr.demonstracao.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ucsbr.demonstracao.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
