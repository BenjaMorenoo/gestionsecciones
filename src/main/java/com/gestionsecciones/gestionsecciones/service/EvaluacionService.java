package com.gestionsecciones.gestionsecciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionsecciones.gestionsecciones.model.Evaluacion;
import com.gestionsecciones.gestionsecciones.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
    
    /*
     * + crearEvaluacion()
     * + editarEvaluacion()
     * + eliminarEvaluacion()
     * + ListarEvaluacion(): Evaluacion
     */

    @Autowired
    public EvaluacionRepository evaluacionRepository;

    public Evaluacion crearEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion findxIdEvaluacion(int idEvaluacion) {
        return evaluacionRepository.findById(idEvaluacion);
    }
    
    public List<Evaluacion> findAllEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion editEvaluacion(Integer idEvaluacion, Evaluacion evaluacion) {
        Optional<Evaluacion> evaluacionExistente = evaluacionRepository.findById(idEvaluacion);
        if (evaluacionExistente.isPresent()) {
            Evaluacion evaluacionActualizado = evaluacionExistente.get();
            evaluacionActualizado.setTitulo(evaluacion.getTitulo());
            evaluacionActualizado.setContenido(evaluacion.getContenido());
            evaluacionActualizado.setFecha(evaluacion.getFecha());

            return evaluacionRepository.save(evaluacionActualizado);
        }
        return null;
    }

    public Evaluacion eliminarEvaluacion(int idEvaluacion) {
        Evaluacion evaluacion = evaluacionRepository.findById(idEvaluacion);
        if (evaluacion != null) {
            evaluacionRepository.deleteById(idEvaluacion);
            return evaluacion;
        }
        return null;
    }



}
