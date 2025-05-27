package com.gestionsecciones.gestionsecciones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "material")

public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMaterial;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(length = 50, nullable = false)
    private String contenido;

    @Enumerated(EnumType.STRING)
    private EstadoMaterial estadoMaterial;

    //SeccionId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seccion")
    @JsonBackReference
    private Seccion seccion;
}
