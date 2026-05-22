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



# Parte 2 — Diseño formal de casos de prueba
Los siguientes casos de prueba fueron diseñados con base en los requerimientos
funcionales del sistema utilizando técnicas de partición de equivalencia,
valores límite y validación de reglas de negocio.

| ID | Requerimiento | Descripción | Precondición | Datos de entrada | Pasos | Resultado esperado | Tipo |
|---|---|---|---|---|---|---|---|


## R1: Registrar nota entre 0.0 y 5.0

| ID | Requerimiento | Descripción | Precondición | Datos de entrada | Pasos | Resultado esperado | Tipo |
| --- | --- | --- | --- | --- | --- | --- | --- |
| CP-01 | R1 | Registrar una nota válida | Sistema disponible | Nota: 4.0 | Registrar nota del estudiante | Registro exitoso | Positivo |
| CP-02 | R1 | Registrar nota menor a 0.0 | Sistema disponible | Nota: -1.0 | Registrar nota del estudiante | Error indicando rango inválido | Negativo |
| CP-03 | R1 | Registrar nota límite superior | Sistema disponible | Nota: 5.0 | Registrar nota del estudiante | Registro exitoso | Borde |


## R2: Aprobar con nota >= 3.0

| ID    | Requerimiento | Descripción                                   | Precondición   | Datos de entrada | Pasos                        | Resultado esperado   | Tipo     |
|-------|----------------|-----------------------------------------------|----------------|------------------|-------------------------------|----------------------|----------|
| CP-04 | R2             | Aprobar estudiante con nota mínima            | Nota registrada | Nota: 3.0        | Verificar estado académico    | Estudiante aprueba   | Borde    |
| CP-05 | R2             | Reprobar estudiante por debajo del límite     | Nota registrada | Nota: 2.9        | Verificar estado académico    | Estudiante reprueba  | Borde    |
| CP-06 | R2             | Aprobar estudiante con nota alta              | Nota registrada | Nota: 4.5        | Verificar estado académico    | Estudiante aprueba   | Positivo |


## R3: Calcular promedio

| ID    | Requerimiento | Descripción                              | Precondición              | Datos de entrada | Pasos               | Resultado esperado      | Tipo     |
|-------|----------------|------------------------------------------|---------------------------|------------------|---------------------|-------------------------|----------|
| CP-07 | R3             | Calcular promedio de varias notas        | Existen notas registradas | 4.0 y 3.0        | Calcular promedio   | Promedio igual a 3.5    | Positivo |
| CP-08 | R3             | Calcular promedio sin notas              | Estudiante sin notas      | Ninguna          | Calcular promedio   | Promedio igual a 0.0    | Negativo |
| CP-09 | R3             | Calcular promedio con una sola nota      | Existe una nota registrada| 5.0              | Calcular promedio   | Promedio igual a 5.0    | Positivo |



## R4: Evitar registro duplicado de materias en el mismo semestre

| ID | Requerimiento | Descripción | Precondición | Datos de entrada | Pasos | Resultado esperado | Tipo |
| --- | --- | --- | --- | --- | --- | --- | --- |
| CP-10 | R4 | Registrar misma materia mismo semestre | Ya existe una nota registrada | Matemáticas - 2025-1 | Registrar nueva nota | Error por nota duplicada | Negativo |
| CP-11 | R4 | Registrar misma materia diferente semestre | Existe nota previa | Matemáticas - 2025-2 | Registrar nueva nota | Registro exitoso | Positivo |
| CP-12 | R4 | Registrar materias diferentes mismo semestre | Existe nota previa | Física - 2025-1 | Registrar nueva nota | Registro exitoso | Positivo |