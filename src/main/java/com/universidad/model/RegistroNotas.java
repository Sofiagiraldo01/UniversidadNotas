package com.universidad.model;

public class RegistroNotas {

    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 5.0;

    public void registrarNota(
            String estudiante,
            String materia,
            String semestre,
            double nota
    ) {

        validarRangoNota(nota);
    }

    private void validarRangoNota(double nota) {

        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {

            throw new IllegalArgumentException(
                    "La nota debe estar entre 0.0 y 5.0"
            );
        }
    }

    public boolean aproboMateria(double nota) {

        return nota >= 3.0;
    }
}