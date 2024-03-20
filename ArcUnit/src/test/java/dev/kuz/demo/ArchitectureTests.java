package dev.kuz.demo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@SpringBootTest
class ArchitectureTests {

    private JavaClasses applicationClasses;

    @BeforeEach
    void init(){
        applicationClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("dev.kuz.demo");
    }

    @Test
    void shouldFollowLayerArchitecture() {
        layeredArchitecture()
                .layer("Controller").definedBy("..controller..")
                .layer("Service").definedBy("..service..")
                .layer("Repository").definedBy("..repository..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
                .check(applicationClasses);
    }

    @Test
    void servicesShouldNotDependOnControllerLevel(){
        noClasses()
                .that().resideInAPackage("..service..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..controller..")
                .because("out of rules")
                .check(applicationClasses);
    }


    //Name tests
    @Test
    void controllerClassesShouldBeNamedXController(){
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .haveSimpleNameEndingWith("Controller")
                .check(applicationClasses);

    }
    @Test
    void serviceClassesShouldBeNamedXService(){
        classes()
                .that().resideInAPackage("..service..")
                .should()
                .haveSimpleNameEndingWith("Service")
                .check(applicationClasses);
    }
    @Test
    void repositoryClassesShouldBeNamedXRepository(){
        classes()
                .that().resideInAPackage("..repository..")
                .should()
                .haveSimpleNameEndingWith("Repository")
                .check(applicationClasses);
    }

    @Test
    void controllerClassesShouldBeAnnotatedWithRestController(){
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .beAnnotatedWith(RestController.class)
                .check(applicationClasses);
    }



    //annotation tests
    @Test
    void controllerClassesShouldBeAnnotatedWithRequestMapping(){
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .beAnnotatedWith(RequestMapping.class)
                .check(applicationClasses);
    }

    @Test
    void serviceClassesShouldBeAnnotatedWithService(){
        classes()
                .that().resideInAPackage("..service..")
                .should()
                .beAnnotatedWith(Service.class)
                .check(applicationClasses);
    }
    @Test
    void repositoryClassesShouldBeAnnotatedWithRepository(){
        classes()
                .that().resideInAPackage("..repository..")
                .should()
                .beAnnotatedWith(Repository.class)
                .check(applicationClasses);
    }

    @Test
    void modelsShouldBeAnnotatedWithDocument(){
        classes()
                .that().resideInAPackage("..model..")
                .should().beAnnotatedWith(Document.class)
                .check(applicationClasses);
    }


    @Test
    void shouldNotUseFieldsAutowired(){
        noFields()
                .should().beAnnotatedWith(Autowired.class)
                .check(applicationClasses);
    }

    @Test
    void IdFieldShouldBeAnnotatedWithId(){
        fields()
                .that().haveName("id")
                .should().beAnnotatedWith(Id.class)
                .check(applicationClasses);
    }



//package tests
    @Test
    void controllerShouldBeInAControllerPackage(){
        classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().resideInAPackage("..controller..")
                .check(applicationClasses);
    }
    @Test
    void serviceShouldBeInAServicePackage(){
        classes()
                .that().haveSimpleNameEndingWith("Service")
                .should().resideInAPackage("..service..")
                .check(applicationClasses);
    }
    @Test
    void repositoryShouldBeInARepositoryPackage(){
        classes()
                .that().haveSimpleNameEndingWith("Repository")
                .should().resideInAPackage("..repository..")
                .check(applicationClasses);
    }

    @Test
    void shouldFieldsInAModelsBePrivate(){
        fields()
                .that().areDeclaredInClassesThat().resideInAPackage("..model..")
                .should().bePrivate()
                .check(applicationClasses);
    }

    @Test
    void shouldFieldsInAControllerBePrivate(){
        fields()
                .that().areDeclaredInClassesThat().resideInAPackage("..controller..")
                .should().bePrivate()
                .check(applicationClasses);
    }

  @Test
    void shouldServiceFieldInAControllerBePrivateAndFinal(){
        fields()
                .that().areDeclaredInClassesThat().resideInAPackage("..controller..")
                .and().haveNameContaining("service")
                .should().bePrivate()
                .andShould().beFinal()
                .check(applicationClasses);
    }

    @Test
    void shouldFieldsInAServiceBePrivate(){
        fields()
                .that().areDeclaredInClassesThat().resideInAPackage("..service..")
                .should().bePrivate()
                .check(applicationClasses);
    }

    @Test
    void shouldRepositoryFieldInAServiceBePrivateAndFinal(){
        fields()
                .that().areDeclaredInClassesThat().resideInAPackage("..service..")
                .and().haveNameContaining("repository")
                .should().bePrivate()
                .andShould().beFinal()
                .check(applicationClasses);
    }
}
