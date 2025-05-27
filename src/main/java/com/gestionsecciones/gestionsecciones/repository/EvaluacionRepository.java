package com.gestionsecciones.gestionsecciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionsecciones.gestionsecciones.model.Evaluacion;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {



    /*
     * + crearEvaluacion()
     * + editarEvaluacion()
     * + eliminarEvaluacion()
     * + ListarEvaluacion(): Evaluacion
     */

    Evaluacion save(Evaluacion evaluacion);
    Evaluacion findById(int idEvaluacion);
    Evaluacion deleteById(int idEvaluacion);
    List<Evaluacion> findAll();
}
