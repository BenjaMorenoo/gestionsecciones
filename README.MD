### Microservicio Gestión de Secciones

Sección:
http://localhost:8080/api/seccion

{
  "nombreSeccion": "005A",
  "idCurso": 3
}


Asignar Profesor (PUT)
http://localhost:8080/api/seccion/2(id_seccion)/asignarProfesor/3(id_profe)

Asignar Alumnos (POST)
http://localhost:8080/api/seccion/3(id_Seccion)/alumnos

[
  "2",
  "4"
  ]

Crear Evaluación
{
  "titulo": "Parcial 2",
  "contenido": "Ecuaciones Cuadraticas",
  "fecha": "2025-08-10",
  "seccion": {
    "idSeccion": 1
  }
}


Crea Material (('APROBADO', 'PENDIENTE', 'RECHAZADO'))

{
  "titulo": "Guía de estudio Java",
  "contenido": "Incluye teoría de clases y objetos.",
  "estadoMaterial": "APROBADO",
  "seccion": {
    "idSeccion": 1
  }
}


Crear Nota (Post)
http://localhost:8080/api/notas

{
  "nota": 6.1,
  "idAlumno": 2,
  "evaluacion": {
    "idEvaluacion": 2
  }
}

Ver notas de una sección(GET)

http://localhost:8080/api/notas/evaluacion/2(ID_Seccion)

Editar nota de alumno(PUT)
http://localhost:8080/api/notas/2(id_alumno)
{
  "nota": 7.0,
  "idAlumno": 2,
  "evaluacion": {
    "idEvaluacion": 2
  }
}