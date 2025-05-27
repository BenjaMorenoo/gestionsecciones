package com.gestionsecciones.gestionsecciones.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seccion")

public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSeccion;

    @Column(length = 50, nullable = false)
    private String nombreSeccion;

    //Id Curso
    @Column(nullable = false)
    private int idCurso;

    //Nombre Curso
    @Column(nullable = false)
    private String nombreCurso;

    //Id Profesor
    @Column(nullable = true)
    private int idProfesor;

    //Profesor Asignado
    @Column(nullable = true)
    private String profesorAsignado;

    //Correo Profesor Asignado
    @Column(nullable = true)
    private String correoProfesorAsignado;


    // Alumnos Asignados (solo IDs)
    @ElementCollection
    private List<String> alumnos = new ArrayList<>();

    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Material> materiales = new ArrayList<>();

    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Evaluacion> evaluaciones = new ArrayList<>();
}
