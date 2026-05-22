package com.universidad.steps;

import com.universidad.exception.NotaDuplicadaException;
import com.universidad.model.RegistroNotas;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroNotasSteps {

    private RegistroNotas sistema;
    private double nota;
    private boolean resultado;
    private double promedio;
    private Exception exception;

    @Given("el sistema de registro de notas está disponible")
    public void sistemaDisponible() {

        sistema = new RegistroNotas();
    }

    @Given("un estudiante tiene una nota {double}")
    public void estudianteTieneNota(double nota) {

        this.nota = nota;
    }

    @When("el sistema valida la aprobación")
    public void sistemaValidaAprobacion() {

        resultado = sistema.aproboMateria(nota);
    }

    @Then("el resultado debe ser {string}")
    public void validarResultado(String esperado) {

        if (esperado.equals("APRUEBA")) {
            assertTrue(resultado);
        } else {
            assertFalse(resultado);
        }
    }

    @Given("un estudiante tiene las notas 4.0 y 3.0")
    public void estudianteTieneVariasNotas() {

        promedio = sistema.calcularPromedio(4.0, 3.0);
    }

    @When("el sistema calcula el promedio")
    public void sistemaCalculaPromedio() {

    }

    @Then("el promedio debe ser 3.5")
    public void validarPromedio() {

        assertEquals(3.5, promedio);
    }

    @Given("un estudiante no tiene notas registradas")
    public void estudianteSinNotas() {

        promedio = sistema.calcularPromedio();
    }

    @Then("el promedio debe ser 0.0")
    public void validarPromedioCero() {

        assertEquals(0.0, promedio);
    }

    @Given("existe una nota registrada para Matematicas en el semestre 2025-1")
    public void existeNotaRegistrada() {

        sistema.registrarNota(
                "Juan",
                "Matematicas",
                "2025-1",
                4.0
        );
    }

    @When("se intenta registrar otra nota para Matematicas en el semestre 2025-1")
    public void registrarNotaDuplicada() {

        exception = assertThrows(
                NotaDuplicadaException.class,
                () -> sistema.registrarNota(
                        "Juan",
                        "Matematicas",
                        "2025-1",
                        3.0
                )
        );
    }

    @Then("el sistema debe mostrar un error de nota duplicada")
    public void validarErrorDuplicado() {

        assertNotNull(exception);
    }

    @When("se registra una nota para Matematicas en el semestre 2025-2")
    public void registrarSemestreDiferente() {

        assertDoesNotThrow(() ->
                sistema.registrarNota(
                        "Juan",
                        "Matematicas",
                        "2025-2",
                        4.0
                )
        );
    }

    @Then("la nota se registra correctamente")
    public void validarRegistroCorrecto() {

        assertTrue(true);
    }

    @When("se intenta registrar una nota de 6.0")
    public void registrarNotaInvalida() {

        exception = assertThrows(
                IllegalArgumentException.class,
                () -> sistema.registrarNota(
                        "Juan",
                        "Fisica",
                        "2025-1",
                        6.0
                )
        );
    }

    @Then("el sistema debe mostrar un error de rango inválido")
    public void validarErrorRango() {

        assertNotNull(exception);
    }
}