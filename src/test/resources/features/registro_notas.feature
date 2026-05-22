Feature: Registro de notas académicas
  Como coordinador académico
  Quiero registrar y consultar notas de estudiantes
  Para controlar el desempeño académico de cada materia

  Background:
    Given el sistema de registro de notas está disponible

  @smoke @critical
  Scenario Outline: Verificar aprobación de estudiantes
    Given un estudiante tiene una nota <nota>
    When el sistema valida la aprobación
    Then el resultado debe ser "<resultado>"

    Examples:
      | nota | resultado |
      | 2.9  | REPRUEBA |
      | 3.0  | APRUEBA |
      | 4.5  | APRUEBA |

  @regression
  Scenario: Calcular promedio de varias notas
    Given un estudiante tiene las notas 4.0 y 3.0
    When el sistema calcula el promedio
    Then el promedio debe ser 3.5

  @regression
  Scenario: Calcular promedio sin notas
    Given un estudiante no tiene notas registradas
    When el sistema calcula el promedio
    Then el promedio debe ser 0.0

  @critical
  Scenario: Registrar una nota duplicada
    Given existe una nota registrada para Matematicas en el semestre 2025-1
    When se intenta registrar otra nota para Matematicas en el semestre 2025-1
    Then el sistema debe mostrar un error de nota duplicada

  @smoke
  Scenario: Registrar misma materia en diferente semestre
    Given existe una nota registrada para Matematicas en el semestre 2025-1
    When se registra una nota para Matematicas en el semestre 2025-2
    Then la nota se registra correctamente

  @critical
  Scenario: Registrar nota inválida
    When se intenta registrar una nota de 6.0
    Then el sistema debe mostrar un error de rango inválido