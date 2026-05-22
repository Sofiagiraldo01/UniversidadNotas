package com.universidad;

import com.universidad.model.RegistroNotas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroNotasTest {

    @Test
    void deberiaRegistrarNotaValida() {

        RegistroNotas sistema = new RegistroNotas();

        assertDoesNotThrow(() ->
                sistema.registrarNota(
                        "Juan",
                        "Matematicas",
                        "2025-1",
                        4.0
                )
        );
    }

    @Test
    void deberiaLanzarErrorSiNotaEsMenorACero() {

        RegistroNotas sistema = new RegistroNotas();

        assertThrows(IllegalArgumentException.class, () ->
                sistema.registrarNota(
                        "Juan",
                        "Matematicas",
                        "2025-1",
                        -1.0
                )
        );
    }

    @Test
    void deberiaLanzarErrorSiNotaEsMayorACinco() {

        RegistroNotas sistema = new RegistroNotas();

        assertThrows(IllegalArgumentException.class, () ->
                sistema.registrarNota(
                        "Juan",
                        "Matematicas",
                        "2025-1",
                        5.5
                )
        );
    }

    @Test
    void deberiaAprobarConNotaTres() {

        RegistroNotas sistema = new RegistroNotas();

        boolean resultado = sistema.aproboMateria(3.0);

        assertTrue(resultado);
    }

    @Test
    void deberiaReprobarConNotaMenorATres() {

        RegistroNotas sistema = new RegistroNotas();

        boolean resultado = sistema.aproboMateria(2.9);

        assertFalse(resultado);
    }
}