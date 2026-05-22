package com.universidad.model;

import com.universidad.exception.NotaDuplicadaException;

import java.util.HashSet;
import java.util.Set;

public class RegistroNotas {

    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 5.0;

    private final Set<String> registros = new HashSet<>();

    public void registrarNota(
            String estudiante,
            String materia,
            String semestre,
            double nota
    ) {

        validarRangoNota(nota);

        String clave = estudiante + materia + semestre;

        if (registros.contains(clave)) {

            throw new NotaDuplicadaException(
                    "Ya existe una nota registrada para esta materia y semestre"
            );
        }

        registros.add(clave);
    }

    public boolean aproboMateria(double nota) {

        return nota >= 3.0;
    }

    public double calcularPromedio(double... notas) {

        if (notas.length == 0) {
            return 0.0;
        }

        double suma = 0.0;

        for (double nota : notas) {
            suma += nota;
        }

        return suma / notas.length;
    }

    private void validarRangoNota(double nota) {

        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {

            throw new IllegalArgumentException(
                    "La nota debe estar entre 0.0 y 5.0"
            );
        }
    }
}