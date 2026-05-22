package com.universidad.model;

public class RegistroNotas {

    public void registrarNota(
            String estudiante,
            String materia,
            String semestre,
            double nota
    ) {

        if (nota < 0.0 || nota > 5.0) {
            throw new IllegalArgumentException(
                    "La nota debe estar entre 0.0 y 5.0"
            );
        }
    }
}