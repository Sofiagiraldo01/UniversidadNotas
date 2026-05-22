# Sistema de Registro de Notas

## Tecnologías utilizadas

- Java Oracle OpenJDK 23.0.2
- Maven
- JUnit 5
- Cucumber
- JaCoCo
- GitHub Actions

## Justificación

Se eligió Java con Maven debido a su robustez para pruebas automatizadas,
integración sencilla con JUnit 5 y soporte completo para TDD, BDD y CI/CD.



# Parte 1 — Análisis previo
## 1.1 Particiones de equivalencia
Las particiones de equivalencia permiten dividir los datos de entrada
en grupos válidos e inválidos para reducir la cantidad de pruebas
sin perder cobertura.

| Partición | Rango | Valor representativo | Resultado esperado |
|---|---|---|---|
| Nota válida baja | 0.0 - 2.9 | 2.5 | Registro exitoso |
| Nota válida aprobación | 3.0 - 5.0 | 4.0 | Registro exitoso |
| Nota inválida negativa | Menor a 0.0 | -1.0 | Error |
| Nota inválida superior | Mayor a 5.0 | 5.5 | Error |


## 1.2 Análisis de valores límite
El análisis de valores límite verifica el comportamiento del sistema
en los extremos del rango permitido, ya que es donde suelen ocurrir errores.

| Valor | Dentro/Fuera | Resultado esperado |
|---|---|---|
| -0.1 | Fuera | Error |
| 0.0 | Dentro | Registro exitoso |
| 0.1 | Dentro | Registro exitoso |
| 2.9 | Dentro | Reprueba |
| 3.0 | Dentro | Aprueba |
| 3.1 | Dentro | Aprueba |
| 4.9 | Dentro | Registro exitoso |
| 5.0 | Dentro | Registro exitoso |
| 5.1 | Fuera | Error |


## 1.3 Preguntas al Product Owner

### Pregunta 1

¿El sistema debe permitir actualizar una nota existente o siempre debe bloquear cualquier duplicado?

#### Justificación

Impacta directamente el diseño de pruebas relacionadas con validación
de duplicados y comportamiento esperado del sistema.


### Pregunta 2

¿Cómo se identifica exactamente un semestre dentro del sistema?

#### Justificación

Afecta la lógica utilizada para detectar notas duplicadas,
ya que el semestre puede representarse de diferentes formas.

