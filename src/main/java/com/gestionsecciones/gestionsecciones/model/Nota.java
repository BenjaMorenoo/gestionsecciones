package com.gestionsecciones.gestionsecciones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNota;

    @Column(nullable = false)
    private Double nota;

    @Column(name = "id_alumno", nullable = false)
    private int idAlumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Evaluacion evaluacion;
}
